package org.kucher.hashtranslatorservice.controller;

import org.kucher.hashtranslatorservice.config.exceptions.api.AppHashIsLoadingException;
import org.kucher.hashtranslatorservice.dao.entity.enums.EAppStatus;
import org.kucher.hashtranslatorservice.service.api.IAppHashService;
import org.kucher.hashtranslatorservice.service.dto.AppHashCreateDTO;
import org.kucher.hashtranslatorservice.service.dto.AppHashDTO;
import org.kucher.hashtranslatorservice.service.dto.AppHashResult;
import org.kucher.hashtranslatorservice.service.utils.HashConverterUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/applications")
public class AppController {

    private final IAppHashService service;

    public AppController(IAppHashService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppHashResult> doPut(@PathVariable("id") UUID id) {

        AppHashDTO appHashDTO = service.read(id);
        if(appHashDTO.getStatus().equals(EAppStatus.DECODED)) {
            AppHashResult result = new AppHashResult();

            result.setHashes(HashConverterUtils.convertString(appHashDTO.getResult()));

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            throw new AppHashIsLoadingException();
        }
    }

    @PostMapping()
    public ResponseEntity<String> doPost(@RequestBody AppHashCreateDTO dto) {
        //Create empty dto
        AppHashDTO appHashDTO = new AppHashDTO();
        //convert list hashes to String format
        appHashDTO.setHash(HashConverterUtils.convertList(dto.getHashes()));

        //Create application and return there uuid
        return new ResponseEntity<>(service.create(appHashDTO).getUuid().toString(), HttpStatus.ACCEPTED);
    }
}
