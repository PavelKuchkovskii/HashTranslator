package org.kucher.authorizationservice.controller;

import org.kucher.authorizationservice.config.exceptions.api.WrongPasswordException;
import org.kucher.authorizationservice.config.security.utils.JwtTokenUtil;
import org.kucher.authorizationservice.service.UserManager;
import org.kucher.authorizationservice.service.dto.UserDTO;
import org.kucher.authorizationservice.service.dto.UserLoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserManager manager;
    private final PasswordEncoder encoder;

    public AuthController(UserManager manager, PasswordEncoder encoder) {
        this.manager = manager;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> doPost(@RequestBody UserLoginDTO dto) {
        UserDTO user = manager.findByEmail(dto.getEmail());

        if(!encoder.matches(dto.getPassword(), user.getPassword())){
            throw new WrongPasswordException();
        }

        return new ResponseEntity<>(JwtTokenUtil.generateAccessToken(user), HttpStatus.OK);
    }
}
