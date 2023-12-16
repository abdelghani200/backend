package com.aftas.backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "competitions")
public class Competition implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    private String code;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Number of participants is required")
    private Integer numberOfParticipants;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    private Double amount;

    @OneToMany(mappedBy = "competition", fetch = FetchType.EAGER)
    private List<Ranking> rankingList;

    @OneToMany(mappedBy = "competition",  fetch = FetchType.EAGER)
    private List<Hunting> huntings;

}
