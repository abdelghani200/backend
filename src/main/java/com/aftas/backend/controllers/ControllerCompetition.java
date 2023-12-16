package com.aftas.backend.controllers;

import com.aftas.backend.models.dtos.competition.CompetitionDto;
import com.aftas.backend.models.dtos.competition.CompetitionDtoRes;
import com.aftas.backend.services.interfaceServices.competitionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/competitions")
@CrossOrigin
public class ControllerCompetition {

    @Autowired
    private competitionService ServiceCompetition;

    @GetMapping("/sansPagination")
    public List<CompetitionDtoRes> getAllCompetitions() {
        return ServiceCompetition.getAllCompetition();
    }

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

    @GetMapping("/{code}")
    public Optional<CompetitionDtoRes> getByCode(@PathVariable String code) {
        return ServiceCompetition.getCompetitionByCode(code);
    }

    @GetMapping
    public ResponseEntity<Page<CompetitionDtoRes>> getAllCompetitions(Pageable pageable){
        Page<CompetitionDtoRes> competitions = ServiceCompetition.getAllCompetitions(pageable);

        return ResponseEntity.ok(competitions);
    }

}
