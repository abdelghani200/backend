package com.aftas.backend.models.dtos.fish;

import com.aftas.backend.models.dtos.level.LevelDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishDtoRes {

    @NotBlank(message = "Fish name is required")
    private String name;
    @NotNull
    @Positive(message = "Fish average weight should be a positive value")
    private Double averageWeight;

    private LevelDto level;

}
