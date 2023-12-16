package com.aftas.backend.controllers;

import com.aftas.backend.models.dtos.ranking.RegistrationRequestDto;
import com.aftas.backend.models.dtos.ranking.RegistrationResponseDto;
import com.aftas.backend.services.interfaceServices.rankingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ranking")
@CrossOrigin
public class ControllerRanking {

    @Autowired
    private rankingService ServiceRanking;

    @PostMapping
    public RegistrationRequestDto registerMemberToCompetition(@Valid @RequestBody RegistrationRequestDto requestDto){
        return ServiceRanking.registerMemberToCompetition(requestDto);
    }

    @GetMapping
    public Map<String, List<RegistrationResponseDto>> getRanks(){
        return ServiceRanking.getCompetitionsWithMembers();
    }

    @GetMapping("/{code}")
    public List<RegistrationResponseDto> getRankByCompetition(@PathVariable String code){
        return ServiceRanking.getRanksByCompetition(code);
    }

}
