package com.aftas.backend.models.dtos.competition;

import com.aftas.backend.models.entities.Hunting;
import com.aftas.backend.models.entities.Ranking;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CompetitionDto {

    private String code;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer numberOfParticipants;

    private String location;

    private Double amount;

}
