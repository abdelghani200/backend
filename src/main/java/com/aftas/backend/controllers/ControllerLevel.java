package com.aftas.backend.controllers;

import com.aftas.backend.models.dtos.level.LevelDto;
import com.aftas.backend.services.interfaceServices.levelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/levels")
public class ControllerLevel {

    @Autowired
    private levelService  ServiceLevel;

    @PostMapping
    public LevelDto saveLevel(@Valid @RequestBody LevelDto levelDto) {
        return ServiceLevel.saveLevel(levelDto);
    }

}
