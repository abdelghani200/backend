package com.aftas.backend.services.implServices;

import com.aftas.backend.exception.NotFoundException;
import com.aftas.backend.models.dtos.fish.FishDtoRes;
import com.aftas.backend.models.dtos.hunting.HuntingDto;
import com.aftas.backend.models.dtos.hunting.HuntingDtoRes;
import com.aftas.backend.models.entities.*;
import com.aftas.backend.repository.*;
import com.aftas.backend.services.interfaceServices.huntingService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class huntingServiceImpl implements huntingService {

    private final huntingRepository repoHunting;
    private final ModelMapper modelMapper;
    private final competitionRepository repoCompetition;
    private final memberRepository repoMember;
    private final fishRepository repoFish;
    private final rankingRepository repoRanking;

    public huntingServiceImpl(
            huntingRepository repoHunting,
            ModelMapper modelMapper,
            competitionRepository repoCompetition,
            memberRepository repoMember,
            fishRepository repoFish,
            rankingRepository repoRanking) {
        this.repoHunting = repoHunting;
        this.modelMapper = modelMapper;
        this.repoCompetition = repoCompetition;
        this.repoMember = repoMember;
        this.repoFish = repoFish;
        this.repoRanking = repoRanking;
    }

    @Override
    public HuntingDto save(HuntingDto huntingDto) {
        Hunting existingHunting = repoHunting.findByMemberAndFishAndCompetition(
                huntingDto.getMember_num(),
                huntingDto.getFish_name(),
                huntingDto.getCompetition_code()
        );

        if (existingHunting != null) {
            existingHunting.setNumberOfFish(existingHunting.getNumberOfFish() + huntingDto.getNumberOfFish());
            updateRankingScore(existingHunting);
            repoHunting.save(existingHunting);

            return modelMapper.map(existingHunting, HuntingDto.class);
        } else {
            Hunting hunting = modelMapper.map(huntingDto, Hunting.class);
            mapFishCompetitionMember(huntingDto, hunting);

            Hunting savedHunting = repoHunting.save(hunting);
            updateRankingScore(savedHunting);

            return modelMapper.map(savedHunting, HuntingDto.class);
        }
    }

    @Transactional
    private void updateRankingScore(Hunting hunting) {
        Member member = hunting.getMember();
        Fish fish = hunting.getFish();
        Competition competition = hunting.getCompetition();

        if (member != null && fish != null && competition != null) {
            Optional<Ranking> optionalRanking = repoRanking.findByCompetitionAndMember(competition.getCode(), member.getNum());

            Ranking ranking = optionalRanking.orElseGet(() -> {
                Ranking newRanking = new Ranking();
                newRanking.setId(new RankingEmbedded(competition.getCode(), member.getNum()));
                return newRanking;
            });

            int fishScore = fish.getLevel().getPoints();
            ranking.setScore(ranking.getScore() + (hunting.getNumberOfFish() * fishScore));

            repoRanking.save(ranking);
            recalculateRanksForCompetition(competition);
        }
    }

    private void recalculateRanksForCompetition(Competition competition) {
        List<Ranking> competitionRankings = repoRanking.findByCompetitionOrderByScoreDesc(competition);

        IntStream.range(0, competitionRankings.size())
                .forEach(i -> {
                    Ranking ranking = competitionRankings.get(i);
                    ranking.setRank(i + 1);
                    repoRanking.save(ranking);
                });
    }

    private void mapFishCompetitionMember(HuntingDto huntingDto, Hunting hunting) {
        if (huntingDto.getFish_name() != null) {
            Fish fish = repoFish.findByName(huntingDto.getFish_name())
                    .orElseThrow(() -> new NotFoundException("The fish with name " + huntingDto.getFish_name() + " is not found"));
            hunting.setFish(fish);
        }

        if (huntingDto.getCompetition_code() != null) {
            Competition competition = repoCompetition.findById(huntingDto.getCompetition_code())
                    .orElseThrow(() -> new NotFoundException("The competition with code " + huntingDto.getCompetition_code() + " is not found"));
            hunting.setCompetition(competition);
        }

        if (huntingDto.getMember_num() != null) {
            Member member = repoMember.findById(huntingDto.getMember_num())
                    .orElseThrow(() -> new NotFoundException("The member with number " + huntingDto.getMember_num() + " is not found"));
            hunting.setMember(member);
        }
    }


    @Override
    public List<HuntingDtoRes> getAllHunting() {
        return repoHunting.findAll().stream().map(hunting -> modelMapper.map(hunting, HuntingDtoRes.class)).collect(Collectors.toList());
    }
}
