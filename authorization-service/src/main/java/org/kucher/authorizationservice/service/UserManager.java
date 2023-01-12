package org.kucher.authorizationservice.service;

import org.kucher.authorizationservice.config.exceptions.api.UserAlreadyChangedException;
import org.kucher.authorizationservice.config.exceptions.api.UserNotFoundException;
import org.kucher.authorizationservice.dao.api.IUserDao;
import org.kucher.authorizationservice.dao.entity.User;
import org.kucher.authorizationservice.dao.entity.builders.UserBuilder;
import org.kucher.authorizationservice.service.api.IUserService;
import org.kucher.authorizationservice.service.dto.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class UserManager implements IUserService {
    private final IUserDao dao;
    private final PasswordEncoder encoder;


    public UserManager(IUserDao dao, PasswordEncoder encoder) {
        this.dao = dao;
        this.encoder = encoder;
    }


    @Override
    public UserDTO create(UserDTO userDTO) {
        userDTO.setUuid(UUID.randomUUID());
        userDTO.setDtCreate(LocalDateTime.now());
        userDTO.setDtUpdate(userDTO.getDtCreate());
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));

        if(validate(userDTO)) {
            User user = this.mapToEntity(userDTO);

            dao.save(user);
        }

        return read(userDTO.getUuid());
    }

    @Override
    public UserDTO read(UUID uuid) {
        Optional<User> user = dao.findById(uuid);
        if(user.isPresent()) {
            return this.mapToDTO(user.get());
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public UserDTO update(UUID uuid, LocalDateTime dtUpdate, UserDTO userDTO) {
        UserDTO read = read(uuid);
        if(dtUpdate.isEqual(read.getDtUpdate())) {
            read.setDtUpdate(LocalDateTime.now());
            read.setEmail(userDTO.getEmail());
            read.setPassword(encoder.encode(userDTO.getPassword()));
            read.setRole(userDTO.getRole());

            if(validate(read)) {
                User user = this.mapToEntity(read);

                dao.save(user);
            }
        }
        else {
            throw new UserAlreadyChangedException();
        }

        return userDTO;
    }

    @Override
    public void delete(UUID uuid, LocalDateTime dtUpdate) {
        UserDTO read = read(uuid);
        if(dtUpdate.isEqual(read.getDtUpdate())) {
            dao.delete(mapToEntity(read));
        }
        else {
            throw new UserAlreadyChangedException();
        }
    }

    @Override
    public boolean validate(UserDTO userDTO) {
        return true;
    }

    @Override
    public UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(user.getUuid());
        userDTO.setDtCreate(user.getDtCreate());
        userDTO.setDtUpdate(user.getDtUpdate());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    @Override
    public User mapToEntity(UserDTO userDTO) {

        return UserBuilder.create()
                .setUuid(userDTO.getUuid())
                .setDtCreate(userDTO.getDtCreate())
                .setDtUpdate(userDTO.getDtUpdate())
                .setEmail(userDTO.getEmail())
                .setPassword(userDTO.getPassword())
                .setRole(userDTO.getRole())
                .build();
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> user = dao.findByEmail(email);
        if(user.isPresent()) {
            return this.mapToDTO(user.get());
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
