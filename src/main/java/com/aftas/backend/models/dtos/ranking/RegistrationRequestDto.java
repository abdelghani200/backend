package com.aftas.backend.models.dtos.ranking;

import com.aftas.backend.enums.IdentityDocumentType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationRequestDto {

    private Long memberNumero;
    private String competitionCode;
    private Integer rank = 0;
    private Integer score = 0;

}
