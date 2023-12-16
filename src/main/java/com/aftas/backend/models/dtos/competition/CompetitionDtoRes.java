package com.aftas.backend.models.dtos.competition;

import com.aftas.backend.models.dtos.hunting.HuntingDto;
import com.aftas.backend.models.dtos.hunting.HuntingDtoRes;
import com.aftas.backend.models.dtos.ranking.RegistrationRequestDto;
import com.aftas.backend.models.dtos.ranking.RegistrationResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CompetitionDtoRes {

    private String code;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer numberOfParticipants;

    private String location;

    private Double amount;

    private List<HuntingDtoRes> huntings;

    private List<RegistrationResponseDto> rankingList;

}
