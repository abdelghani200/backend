package com.aftas.backend.models.dtos.ranking;

import com.aftas.backend.models.dtos.competition.CompetitionDto;
import com.aftas.backend.models.dtos.member.MemberDto;
import com.aftas.backend.models.dtos.member.MemberDtoRes;
import com.aftas.backend.models.entities.RankingEmbedded;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationResponseDto {

    /*
    private RankingEmbedded id;
    private MemberDto member;
    private CompetitionDto competition;
    private Integer rank = 0;
    private Integer score = 0;

     */

    private RankingEmbedded id;
    private MemberDto member;
    private String competitionCode;
    private Integer rank = 0;
    private Integer score = 0;

}
