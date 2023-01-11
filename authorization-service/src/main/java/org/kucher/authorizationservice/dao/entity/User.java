package org.kucher.authorizationservice.dao.entity;

import org.kucher.authorizationservice.dao.entity.api.IUser;
import org.kucher.authorizationservice.dao.entity.enums.EUserRole;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class User implements IUser {

    @Id
    private UUID uuid;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EUserRole role;

    public User() {
    }

    public User(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String email, String password, EUserRole role) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public LocalDateTime getDtCreate() {
        return this.dtCreate;
    }

    @Override
    public LocalDateTime getDtUpdate() {
        return this.dtUpdate;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public EUserRole getRole() {
        return this.role;
    }
}
