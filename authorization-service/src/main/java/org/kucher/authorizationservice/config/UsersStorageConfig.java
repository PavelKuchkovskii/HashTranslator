package org.kucher.authorizationservice.config;

import org.kucher.authorizationservice.dao.api.IUserDao;
import org.kucher.authorizationservice.dao.entity.enums.EUserRole;
import org.kucher.authorizationservice.service.UserManager;
import org.kucher.authorizationservice.service.dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsersStorageConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserManager userManager(IUserDao dao, PasswordEncoder encoder) {
        UserManager manager = new UserManager(dao, encoder);

        UserDTO admin = new UserDTO();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");
        admin.setRole(EUserRole.ADMIN);

        manager.create(admin);

        return manager;
    }
}
