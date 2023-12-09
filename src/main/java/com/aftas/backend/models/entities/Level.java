package com.aftas.backend.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "levels")
public class Level {

    @Id
    @Column(unique = true)
    private Integer code;

    @NotNull
    private String description;

    @Min(value = 0)
    private Integer points;

    @OneToMany(mappedBy = "level")
    private List<Fish> fishList;

}
