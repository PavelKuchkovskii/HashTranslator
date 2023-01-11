package org.kucher.authorizationservice.service.api;

import org.kucher.authorizationservice.dao.entity.User;
import org.kucher.authorizationservice.service.dto.UserDTO;

public interface IUserService extends IService<UserDTO, User> {

   UserDTO findByEmail(String email);
}
