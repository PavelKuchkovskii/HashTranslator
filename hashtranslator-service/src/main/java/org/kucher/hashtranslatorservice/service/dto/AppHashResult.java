package org.kucher.hashtranslatorservice.service.dto;

import org.kucher.hashtranslatorservice.dao.entity.ResultHash;

import java.util.List;

public class AppHashResult {

    private List<ResultHash> hashes;

    public List<ResultHash> getHashes() {
        return hashes;
    }

    public void setHashes(List<ResultHash> hashes) {
        this.hashes = hashes;
    }
}
