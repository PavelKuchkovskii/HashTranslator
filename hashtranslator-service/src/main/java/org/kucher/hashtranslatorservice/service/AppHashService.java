package org.kucher.hashtranslatorservice.service;

import org.kucher.hashtranslatorservice.config.exceptions.api.AppHashNotFoundException;
import org.kucher.hashtranslatorservice.config.security.entity.UserToJwt;
import org.kucher.hashtranslatorservice.dao.api.IAppHashDao;
import org.kucher.hashtranslatorservice.dao.entity.AppHash;
import org.kucher.hashtranslatorservice.dao.entity.User;
import org.kucher.hashtranslatorservice.dao.entity.builders.AppHashBuilder;
import org.kucher.hashtranslatorservice.dao.entity.enums.EAppStatus;
import org.kucher.hashtranslatorservice.service.api.IAppHashService;
import org.kucher.hashtranslatorservice.service.dto.AppHashDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppHashService implements IAppHashService {

    private final IAppHashDao dao;

    public AppHashService(IAppHashDao dao) {
        this.dao = dao;
    }

    @Override
    public AppHashDTO create(AppHashDTO appDTO) {
        //Get user from Security Context
        UserToJwt userToJwt = (UserToJwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User(userToJwt.getUuid());

        appDTO.setUuid(UUID.randomUUID());
        appDTO.setStatus(EAppStatus.LOADED);
        appDTO.setUser(user);

        if(validate(appDTO)) {
            AppHash appHash = mapToEntity(appDTO);

            dao.save(appHash);
        }

        return read(appDTO.getUuid());
    }

    @Override
    public AppHashDTO read(UUID uuid) {
        Optional<AppHash> appHash = dao.findById(uuid);
        if(appHash.isPresent()) {
            return this.mapToDTO(appHash.get());
        }
        else {
            throw new AppHashNotFoundException();
        }
    }

    @Override
    public void delete(UUID uuid) {
        AppHashDTO read = read(uuid);
        dao.delete(mapToEntity(read));
    }

    @Override
    public void update(UUID uuid, AppHashDTO dto) {
        AppHashDTO read = read(uuid);
        read.setResult(dto.getResult());
        read.setStatus(dto.getStatus());

        if(validate(read)) {
            dao.save(mapToEntity(read));
        }

    }

    @Override
    public boolean validate(AppHashDTO appDTO) {
        return true;
    }

    @Override
    public AppHashDTO mapToDTO(AppHash app) {
        AppHashDTO appHashDTO = new AppHashDTO();
        appHashDTO.setUuid(app.getUuid());
        appHashDTO.setHash(app.getHash());
        appHashDTO.setResult(app.getResult());
        appHashDTO.setStatus(app.getStatus());
        appHashDTO.setUser(app.getUser());
        return appHashDTO;
    }

    @Override
    public AppHash mapToEntity(AppHashDTO appDTO) {
        return AppHashBuilder.create()
                .setUuid(appDTO.getUuid())
                .setHash(appDTO.getHash())
                .setResult(appDTO.getResult())
                .setStatus(appDTO.getStatus())
                .setUser(appDTO.getUser())
                .build();
    }
}
