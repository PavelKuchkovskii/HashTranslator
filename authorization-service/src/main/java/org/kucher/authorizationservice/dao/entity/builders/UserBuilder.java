package org.kucher.authorizationservice.dao.entity.builders;

import org.kucher.authorizationservice.dao.entity.User;
import org.kucher.authorizationservice.dao.entity.enums.EUserRole;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserBuilder {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String email;
    private String password;
    private EUserRole role;

    private UserBuilder() {

    }

    public static UserBuilder create() {
        return new UserBuilder();
    }

    public UserBuilder setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public UserBuilder setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public UserBuilder setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setRole(EUserRole role) {
        this.role = role;
        return this;
    }

    public User build() {
        return new User(uuid, dtCreate, dtUpdate, email, password, role);
    }
}
