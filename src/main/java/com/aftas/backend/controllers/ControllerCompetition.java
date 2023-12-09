package com.aftas.backend.controllers;

import com.aftas.backend.models.dtos.competition.CompetitionDto;
import com.aftas.backend.services.interfaceServices.competitionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/competitions")
public class ControllerCompetition {

    @Autowired
    private competitionService ServiceCompetition;

    @PostMapping
    public CompetitionDto saveCompetition(@Valid @RequestBody CompetitionDto competitionDto) {
        return ServiceCompetition.saveCompetition(competitionDto);
    }

}
