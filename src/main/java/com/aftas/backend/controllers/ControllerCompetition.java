package com.aftas.backend.controllers;

import com.aftas.backend.models.dtos.competition.CompetitionDto;
import com.aftas.backend.services.interfaceServices.competitionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/competitions")
public class ControllerCompetition {

    @Autowired
    private competitionService ServiceCompetition;

    @PostMapping
    public CompetitionDto saveCompetition(@Valid @RequestBody CompetitionDto competitionDto) {
        return ServiceCompetition.saveCompetition(competitionDto);
    }

    @PutMapping("/{code}")
    public CompetitionDto updateCompetition(@Valid @RequestBody CompetitionDto competitionDto, @PathVariable String code) {
        return ServiceCompetition.updateCompetition(competitionDto, code);
    }

    @DeleteMapping("/{code}")
    public void deleteCompetition(@PathVariable String code) {
        ServiceCompetition.deleteCompetition(code);
    }

}
