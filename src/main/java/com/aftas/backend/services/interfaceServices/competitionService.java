package com.aftas.backend.services.interfaceServices;

import com.aftas.backend.models.dtos.competition.CompetitionDto;
import org.springframework.stereotype.Service;

@Service
public interface competitionService {

    CompetitionDto saveCompetition(CompetitionDto competitionDto);
    CompetitionDto updateCompetition(CompetitionDto competitionDto, String code);

}
