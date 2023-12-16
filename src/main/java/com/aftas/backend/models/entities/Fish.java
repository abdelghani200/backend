package com.aftas.backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "fish")
public class Fish implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    @NotNull
    private String name;
    @NotNull
    @Min(value = 0)
    private Double averageWeight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_code")
    private Level level;

    @OneToMany(mappedBy = "fish", fetch = FetchType.LAZY)
    private List<Hunting> huntings;


}
