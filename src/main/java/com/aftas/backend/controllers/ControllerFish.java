package com.aftas.backend.controllers;

import com.aftas.backend.models.dtos.fish.FishDto;
import com.aftas.backend.services.interfaceServices.fishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fish")
public class ControllerFish {

    @Autowired
    private fishService ServiceFish;

    @PostMapping
    public FishDto saveFish(@Valid @RequestBody FishDto fishDto){
        return ServiceFish.saveFish(fishDto);
    }

}
