package org.kucher.authorizationservice.service.api;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IService <DTO, ENTITY> {

    DTO create(DTO dto);
    DTO read(UUID uuid);
    DTO update(UUID uuid, LocalDateTime dtUpdate, DTO dto);
    void delete(UUID uuid, LocalDateTime dtUpdate);
    boolean validate(DTO dto);
    DTO mapToDTO(ENTITY entity);
    ENTITY mapToEntity(DTO dto);
}
