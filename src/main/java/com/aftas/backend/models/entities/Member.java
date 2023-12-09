package com.aftas.backend.models.entities;

import com.aftas.backend.enums.IdentityDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "members")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Family is required")
    private String familyName;

    @NotNull(message = "Accession is required")
    private LocalDate accessionDate;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;

    @NotBlank(message = "Identify number is required")
    @Pattern(regexp = "[0-9]{10}", message = "Identity number must be a 10-digit number")
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    private List<Hunting> huntings;

}
