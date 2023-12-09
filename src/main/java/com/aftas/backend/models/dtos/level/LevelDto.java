package com.aftas.backend.models.dtos.level;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LevelDto {

    private Integer code;
    private String description;
    private Integer points;

}
