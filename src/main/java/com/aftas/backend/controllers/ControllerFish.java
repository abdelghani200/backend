package com.aftas.backend.controllers;

import com.aftas.backend.models.dtos.fish.FishDto;
import com.aftas.backend.models.dtos.fish.FishDtoRes;
import com.aftas.backend.services.interfaceServices.fishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fish")
@CrossOrigin
public class ControllerFish {

    @Autowired
    private fishService ServiceFish;

    @PostMapping
    public FishDto saveFish(@Valid @RequestBody FishDto fishDto){
        return ServiceFish.saveFish(fishDto);
    }

    @GetMapping
    public List<FishDtoRes> getFish(){
        return ServiceFish.getFish();
    }

}
