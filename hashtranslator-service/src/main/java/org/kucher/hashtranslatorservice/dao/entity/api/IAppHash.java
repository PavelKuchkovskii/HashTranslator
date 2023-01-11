package org.kucher.hashtranslatorservice.dao.entity.api;

import org.kucher.hashtranslatorservice.dao.entity.User;
import org.kucher.hashtranslatorservice.dao.entity.enums.EAppStatus;


public interface IAppHash extends IEssence {

    String getHash();
    String getResult();
    EAppStatus getStatus();
    User getUser();
}
