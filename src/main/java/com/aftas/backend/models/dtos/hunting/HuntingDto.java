package com.aftas.backend.models.dtos.hunting;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HuntingDto {

    private Integer id;
    private Integer numberOfFish;
    private Long member_num;
    private String fish_name;
    private String competition_code;

}
