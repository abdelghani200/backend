package com.aftas.backend.controllers;

import com.aftas.backend.models.dtos.hunting.HuntingDto;
import com.aftas.backend.models.dtos.hunting.HuntingDtoRes;
import com.aftas.backend.services.interfaceServices.huntingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hunting")
@CrossOrigin
public class ControllerHunting {

    @Autowired
    private huntingService ServiceHunting;

    @PostMapping
    public HuntingDto saveHunting(@Valid @RequestBody HuntingDto huntingDto){
        return ServiceHunting.save(huntingDto);
    }

    @GetMapping
    public List<HuntingDtoRes> getAllHunting(){
        return ServiceHunting.getAllHunting();
    }

}
