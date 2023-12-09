package com.aftas.backend.services.implServices;

import com.aftas.backend.models.dtos.competition.CompetitionDto;
import com.aftas.backend.models.entities.Competition;
import com.aftas.backend.repository.competitionRepository;
import com.aftas.backend.services.interfaceServices.competitionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class competitionServiceImpl implements competitionService {

    @Autowired
    private competitionRepository Repo_competition;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CompetitionDto saveCompetition(CompetitionDto competitionDto) {
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        Repo_competition.validateCode(competition);
        return modelMapper.map(Repo_competition.save(competition), CompetitionDto.class);
    }
}
