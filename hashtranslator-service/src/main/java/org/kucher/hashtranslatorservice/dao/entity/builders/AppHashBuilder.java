package org.kucher.hashtranslatorservice.dao.entity.builders;

import org.kucher.hashtranslatorservice.dao.entity.AppHash;
import org.kucher.hashtranslatorservice.dao.entity.User;
import org.kucher.hashtranslatorservice.dao.entity.enums.EAppStatus;

import java.util.UUID;

public class AppHashBuilder {

    private UUID uuid;
    private String hash;
    private String result;
    private EAppStatus status;

    private User user;

    private AppHashBuilder() {

    }

    public static AppHashBuilder create() {
        return new AppHashBuilder();
    }

    public AppHashBuilder setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public AppHashBuilder setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public AppHashBuilder setResult(String result) {
        this.result = result;
        return this;
    }

    public AppHashBuilder setStatus(EAppStatus status) {
        this.status = status;
        return this;
    }

    public AppHashBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public AppHash build() {
        return new AppHash(uuid, hash, result, status, user);
    }
}
