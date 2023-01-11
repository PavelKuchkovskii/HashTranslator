package org.kucher.authorizationservice.dao.api;

import org.kucher.authorizationservice.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserDao extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
