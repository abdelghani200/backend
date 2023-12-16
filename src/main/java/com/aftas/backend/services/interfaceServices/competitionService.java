package com.aftas.backend.services.interfaceServices;

import com.aftas.backend.models.dtos.competition.CompetitionDto;
import com.aftas.backend.models.dtos.competition.CompetitionDtoRes;
import com.aftas.backend.models.dtos.ranking.RegistrationRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface competitionService {

    CompetitionDto saveCompetition(CompetitionDto competitionDto);
    CompetitionDto updateCompetition(CompetitionDto competitionDto, String code);
    void deleteCompetition(String code);
    List<CompetitionDtoRes> getAllCompetition();
    Optional<CompetitionDtoRes> getCompetitionByCode(String code);

    Page<CompetitionDtoRes> getAllCompetitions(Pageable pageable);

}
