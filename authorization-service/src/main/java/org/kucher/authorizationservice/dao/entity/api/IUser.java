package org.kucher.authorizationservice.dao.entity.api;

import org.kucher.authorizationservice.dao.entity.enums.EUserRole;

public interface IUser extends IEssence {

    String getEmail();
    String getPassword();
    EUserRole getRole();
}
