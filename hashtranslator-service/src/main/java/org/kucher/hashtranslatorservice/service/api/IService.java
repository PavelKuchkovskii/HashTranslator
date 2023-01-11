package org.kucher.hashtranslatorservice.service.api;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IService <DTO, ENTITY> {

    DTO create(DTO dto);
    DTO read(UUID uuid);
    void delete(UUID uuid);
    void update(UUID uuid, DTO dto);
    boolean validate(DTO dto);
    DTO mapToDTO(ENTITY entity);
    ENTITY mapToEntity(DTO dto);
}
