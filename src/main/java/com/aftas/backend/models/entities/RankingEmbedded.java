package com.aftas.backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class RankingEmbedded implements Serializable {

    @Column(name = "competition_code")
    private String competitionCode;

    @Column(name = "member_num")
    private Long memberNum;

}
