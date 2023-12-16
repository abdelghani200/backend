package com.aftas.backend.services.implServices;

import com.aftas.backend.exception.NotFoundException;
import com.aftas.backend.models.dtos.competition.CompetitionDto;
import com.aftas.backend.models.dtos.competition.CompetitionDtoRes;
import com.aftas.backend.models.entities.Competition;
import com.aftas.backend.repository.competitionRepository;
import com.aftas.backend.repository.memberRepository;
import com.aftas.backend.services.interfaceServices.competitionService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public List<CompetitionDtoRes> getAllCompetition() {
        return Repo_competition.findAll().stream().map(competition -> modelMapper.map(competition, CompetitionDtoRes.class)).collect(Collectors.toList());

    }

    @Override
    public Optional<CompetitionDtoRes> getCompetitionByCode(String code) {
        Optional<Competition> optionalCompetition = Repo_competition.findByCode(code);

        if (optionalCompetition.isPresent()) {
            Competition competition = optionalCompetition.get();

            // Initialize the collections within an active session
            Hibernate.initialize(competition.getHuntings());
            Hibernate.initialize(competition.getRankingList());

            CompetitionDtoRes competitionDto = modelMapper.map(competition, CompetitionDtoRes.class);
            return Optional.of(competitionDto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Page<CompetitionDtoRes> getAllCompetitions(Pageable pageable) {
        return Repo_competition.findAll(pageable).map(competition -> modelMapper.map(competition, CompetitionDtoRes.class));
    }
}
