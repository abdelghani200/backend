package com.aftas.backend.services.implServices;

import com.aftas.backend.exception.NotFoundException;
import com.aftas.backend.exception.RegistrationException;
import com.aftas.backend.models.dtos.hunting.HuntingDtoRes;
import com.aftas.backend.models.dtos.ranking.RegistrationRequestDto;
import com.aftas.backend.models.dtos.ranking.RegistrationResponseDto;
import com.aftas.backend.models.entities.Competition;
import com.aftas.backend.models.entities.Member;
import com.aftas.backend.models.entities.Ranking;
import com.aftas.backend.models.entities.RankingEmbedded;
import com.aftas.backend.repository.competitionRepository;
import com.aftas.backend.repository.memberRepository;
import com.aftas.backend.repository.rankingRepository;
import com.aftas.backend.services.interfaceServices.rankingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class rankingServiceImpl implements rankingService {

    @Autowired
    private rankingRepository Repo_ranking;
    @Autowired
    private competitionRepository Repo_competition;
    @Autowired
    private memberRepository Repo_member;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RegistrationRequestDto registerMemberToCompetition(RegistrationRequestDto requestDto) {

        System.out.println("Competition Code: " + requestDto.getCompetitionCode());
        System.out.println("Member Number: " + requestDto.getMemberNum());

        if (requestDto.getCompetitionCode() == null || requestDto.getMemberNum() == null) {
            throw new IllegalArgumentException("Competition code and member number must not be null.");
        }


        Competition competition = Repo_competition.findByCode(requestDto.getCompetitionCode())
                .orElseThrow(() -> new NotFoundException("Competition not found with code" + requestDto.getCompetitionCode()));

        Member member = Repo_member.findById(requestDto.getMemberNum())
                .orElseThrow(() ->new NotFoundException("Member not found with numero" + requestDto.getMemberNum()));

        if (Repo_ranking.isMemberAlreadyRegistered(requestDto.getMemberNum(), requestDto.getCompetitionCode())) {
            throw new RegistrationException("Member is already registered for the competition.");
        }

        //to

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime competitionStartDateTime = LocalDateTime.of(
                competition.getDate(),
                competition.getStartTime()
        );

        LocalDateTime registrationDeadline = competitionStartDateTime.minusDays(1);

        if (now.isAfter(registrationDeadline) || now.plusHours(24).isAfter(competitionStartDateTime)) {
            throw new RegistrationException("Registration is closed. The competition has already started or is less than 24 hours away.");
        }

        //to

        if (competition.getNumberOfParticipants() != null &&
                Repo_ranking.countByCompetitionCode(requestDto.getCompetitionCode()) >= competition.getNumberOfParticipants()) {
            throw new RegistrationException("Competition is already full. Cannot register more members.");
        }

        RankingEmbedded rankingId = new RankingEmbedded(requestDto.getCompetitionCode(), member.getNum());

        Ranking ranking = new Ranking();
        ranking.setId(rankingId);
        ranking.setCompetition(competition);
        ranking.setMember(member);
        ranking.setRank(requestDto.getRank());
        ranking.setScore(requestDto.getScore());

        Repo_ranking.save(ranking);

        return requestDto;
    }

    @Override
    public Map<String, List<RegistrationResponseDto>> getCompetitionsWithMembers() {
        List<RegistrationResponseDto> rankings = Repo_ranking.findAll().stream()
                .map(ranking -> modelMapper.map(ranking, RegistrationResponseDto.class))
                .toList();

        // Group the rankings by competition code
        Map<String, List<RegistrationResponseDto>> competitions = rankings.stream()
                .collect(Collectors.groupingBy(RegistrationResponseDto::getCompetitionCode));

        competitions.forEach((competitionCode, competitionRankings) -> {
            int rank = 1;
            for (RegistrationResponseDto ranking : competitionRankings) {
                ranking.setRank(rank++);
                ranking.setScore(ranking.getScore());
            }
        });

        return competitions;
    }



    @Override
    public List<RegistrationResponseDto> getRanksByCompetition(String competitionCode) {
        Competition competition = Repo_competition.findByCode(competitionCode)
                .orElseThrow(() -> new NotFoundException("Competition not found with code" + competitionCode));

        List<Ranking> rankings = Repo_ranking.findByCompetition(competition);

        List<RegistrationResponseDto> registrationResponseDtos = rankings.stream()
                .map(ranking -> modelMapper.map(ranking, RegistrationResponseDto.class))
                .collect(Collectors.toList());

        registrationResponseDtos.sort(Comparator.comparingInt(RegistrationResponseDto::getRank));

        return registrationResponseDtos;
    }



}
