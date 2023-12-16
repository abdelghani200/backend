package com.aftas.backend.models.dtos.hunting;

import com.aftas.backend.models.dtos.competition.CompetitionDto;
import com.aftas.backend.models.dtos.competition.CompetitionDtoRes;
import com.aftas.backend.models.dtos.fish.FishDto;
import com.aftas.backend.models.dtos.fish.FishDtoRes;
import com.aftas.backend.models.dtos.member.MemberDto;
import com.aftas.backend.models.dtos.member.MemberDtoRes;
import com.aftas.backend.models.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HuntingDtoRes implements Serializable {

    /*
    private Integer id;
    private Integer numberOfFish;
    private MemberDto member;
    private FishDtoRes fish;
    private CompetitionDto competition;
    private String competition_code;
    */

    private Integer id;
    private Integer numberOfFish;
    private MemberDto member;
    private FishDtoRes fish;
    private String competition_code;


}
