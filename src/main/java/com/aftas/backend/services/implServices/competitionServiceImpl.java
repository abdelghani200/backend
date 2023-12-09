package com.aftas.backend.services.implServices;

import com.aftas.backend.exception.NotFoundException;
import com.aftas.backend.models.dtos.competition.CompetitionDto;
import com.aftas.backend.models.dtos.ranking.RegistrationRequestDto;
import com.aftas.backend.models.entities.Competition;
import com.aftas.backend.models.entities.Member;
import com.aftas.backend.repository.competitionRepository;
import com.aftas.backend.repository.memberRepository;
import com.aftas.backend.services.interfaceServices.competitionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class competitionServiceImpl implements competitionService {

    @Autowired
    private competitionRepository Repo_competition;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private memberRepository Repo_member;

    @Override
    public CompetitionDto saveCompetition(CompetitionDto competitionDto) {
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        Repo_competition.validateCode(competition);
        return modelMapper.map(Repo_competition.save(competition), CompetitionDto.class);
    }

    @Override
    public CompetitionDto updateCompetition(CompetitionDto competitionDto, String code) {
        Competition existingCompetition = Repo_competition.findById(code)
                .orElseThrow(() -> new NotFoundException("Competition not found with code" + code));

        existingCompetition.setAmount(competitionDto.getAmount());
        existingCompetition.setStartTime(competitionDto.getStartTime());
        existingCompetition.setEndTime(competitionDto.getEndTime());
        existingCompetition.setNumberOfParticipants(competitionDto.getNumberOfParticipants());

        Competition updateCompetition = Repo_competition.save(existingCompetition);

        return modelMapper.map(updateCompetition, CompetitionDto.class);
    }

    @Override
    public void deleteCompetition(String code) {
        Competition competition = Repo_competition.findById(code)
                .orElseThrow(()->new NotFoundException("Competition not found with" + code));

        Repo_competition.delete(competition);
    }

}
