package com.aftas.backend.services.implServices;

import com.aftas.backend.exception.NotFoundException;
import com.aftas.backend.exception.RegistrationException;
import com.aftas.backend.models.dtos.ranking.RegistrationRequestDto;
import com.aftas.backend.models.entities.Competition;
import com.aftas.backend.models.entities.Member;
import com.aftas.backend.models.entities.Ranking;
import com.aftas.backend.models.entities.RankingEmbedded;
import com.aftas.backend.repository.competitionRepository;
import com.aftas.backend.repository.memberRepository;
import com.aftas.backend.repository.rankingRepository;
import com.aftas.backend.services.interfaceServices.rankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class rankingServiceImpl implements rankingService {

    @Autowired
    private rankingRepository Repo_ranking;
    @Autowired
    private competitionRepository Repo_competition;
    @Autowired
    private memberRepository Repo_member;

    @Override
    public RegistrationRequestDto registerMemberToCompetition(RegistrationRequestDto requestDto) {
        Competition competition = Repo_competition.findById(requestDto.getCompetitionCode())
                .orElseThrow(() -> new NotFoundException("Competition not found with code" + requestDto.getCompetitionCode()));

        Member member = Repo_member.findById(requestDto.getMemberNumero())
                .orElseThrow(() ->new NotFoundException("Member not found with numero" + requestDto.getMemberNumero()));

        if (Repo_ranking.isMemberAlreadyRegistered(requestDto.getMemberNumero(), requestDto.getCompetitionCode())) {
            throw new RegistrationException("Member is already registered for the competition.");
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
}
