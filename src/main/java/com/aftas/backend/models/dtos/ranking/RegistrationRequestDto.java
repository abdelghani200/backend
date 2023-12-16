package com.aftas.backend.models.dtos.ranking;

import com.aftas.backend.enums.IdentityDocumentType;
import com.aftas.backend.models.entities.RankingEmbedded;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationRequestDto {

    private RankingEmbedded id;
    private Long memberNum;
    private String competitionCode;
    private Integer rank = 0;
    private Integer score = 0;

}
