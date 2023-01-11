package org.kucher.hashtranslatorservice.service.dto;

import org.kucher.hashtranslatorservice.dao.entity.User;
import org.kucher.hashtranslatorservice.dao.entity.enums.EAppStatus;

import java.util.UUID;

public class AppHashDTO {

    private UUID uuid;
    private String hash;
    private String result;
    private EAppStatus status;
    private User user;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public EAppStatus getStatus() {
        return status;
    }

    public void setStatus(EAppStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
