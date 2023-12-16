package com.aftas.backend.models.dtos.ranking;

import com.aftas.backend.models.dtos.competition.CompetitionDtoRes;
import com.aftas.backend.models.dtos.member.MemberDtoRes;
import com.aftas.backend.models.entities.RankingEmbedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingDtoRes {

    private RankingEmbedded id;
    private Integer rank;
    private Integer score;
    private MemberDtoRes member;
    private CompetitionDtoRes competition;

}
