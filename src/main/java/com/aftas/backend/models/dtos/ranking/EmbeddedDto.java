package com.aftas.backend.models.dtos.ranking;

import com.aftas.backend.models.dtos.competition.CompetitionDto;
import com.aftas.backend.models.dtos.member.MemberDtoRes;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmbeddedDto {

    private CompetitionDto competitionCode;
    private MemberDtoRes member;

}
