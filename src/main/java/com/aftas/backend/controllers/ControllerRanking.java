package com.aftas.backend.controllers;

import com.aftas.backend.models.dtos.ranking.RegistrationRequestDto;
import com.aftas.backend.services.interfaceServices.rankingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ranking")
public class ControllerRanking {

    @Autowired
    private rankingService ServiceRanking;

    @PostMapping
    public RegistrationRequestDto registerMemberToCompetition(@Valid @RequestBody RegistrationRequestDto requestDto){
        return ServiceRanking.registerMemberToCompetition(requestDto);
    }

}
