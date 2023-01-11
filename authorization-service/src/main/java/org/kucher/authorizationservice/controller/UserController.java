package org.kucher.authorizationservice.controller;

import org.kucher.authorizationservice.service.UserManager;
import org.kucher.authorizationservice.service.dto.UserDTO;
import org.kucher.authorizationservice.service.dto.UserActionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserManager manager;

    public UserController(UserManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<UserDTO> doPost(@RequestBody UserActionDTO dto) {
        UserDTO user = new UserDTO();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        UserDTO created = this.manager.create(user);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<UserDTO> doPut(@PathVariable("uuid") UUID uuid,
                                         @PathVariable("dt_update") String dt_update,
                                         @RequestBody UserActionDTO dto) {

        LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(dt_update)), ZoneId.of("UTC"));
        UserDTO user = new UserDTO();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        UserDTO updated = this.manager.update(uuid, dtUpdate, user);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


}
