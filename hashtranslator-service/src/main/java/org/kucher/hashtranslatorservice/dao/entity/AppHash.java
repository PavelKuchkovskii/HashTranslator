package org.kucher.hashtranslatorservice.dao.entity;

import org.kucher.hashtranslatorservice.dao.entity.api.IAppHash;
import org.kucher.hashtranslatorservice.dao.entity.enums.EAppStatus;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class AppHash implements IAppHash {
    @Id
    private UUID uuid;
    @Column(name = "hash")
    private String hash;
    @Column(name = "result")
    private String result;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EAppStatus status;
    @Embedded
    private User user;

    public AppHash() {
    }

    public AppHash(UUID uuid, String hash, String result, EAppStatus status, User user) {
        this.uuid = uuid;
        this.hash = hash;
        this.result = result;
        this.status = status;
        this.user = user;
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public String getHash() {
        return this.hash;
    }

    @Override
    public String getResult() {
        return this.result;
    }

    @Override
    public EAppStatus getStatus() {
        return this.status;
    }

    @Override
    public User getUser() {
        return this.user;
    }
}
