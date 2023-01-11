package org.kucher.hashtranslatorservice.dao.api;

import org.kucher.hashtranslatorservice.dao.entity.AppHash;
import org.kucher.hashtranslatorservice.dao.entity.enums.EAppStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAppHashDao extends JpaRepository<AppHash, UUID> {

    Optional<List<AppHash>> findByStatus(EAppStatus status);
}
