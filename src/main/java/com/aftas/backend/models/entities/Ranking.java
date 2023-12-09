package com.aftas.backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "ranking")
public class Ranking implements Serializable {

    @EmbeddedId
    private RankingEmbedded id;

    @MapsId("competitionCode")
    @ManyToOne
    @JoinColumn(name = "competition_code")
    private Competition competition;

    @MapsId("memberNum")
    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member member;

    @NotNull(message = "rank is required")
    @Min(value = 0)
    private Integer rank;

    @NotNull(message = "score is required")
    @Min(value = 0)
    private Integer score;
}
