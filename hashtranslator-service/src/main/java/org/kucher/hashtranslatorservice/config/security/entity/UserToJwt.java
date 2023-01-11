package org.kucher.hashtranslatorservice.config.security.entity;


import java.time.LocalDateTime;
import java.util.UUID;

public class UserToJwt {
    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String email;
    private EUserRole role;

    public UserToJwt() {
    }

    public UserToJwt(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String email, EUserRole role) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.email = email;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String nick) {
        this.email = nick;
    }

    public EUserRole getRole() {
        return role;
    }

    public void setRole(EUserRole role) {
        this.role = role;
    }
}
