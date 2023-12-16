package com.aftas.backend.repository;

import com.aftas.backend.exception.CompetitionValidationException;
import com.aftas.backend.models.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface competitionRepository extends JpaRepository<Competition, String> {

    @Query("SELECT c FROM Competition c WHERE c.code = :code")
    Optional<Competition> findByCode (@Param("code") String code);

    @Transactional
    default void validateCode(Competition competition) throws CompetitionValidationException {
        validateCompetitionCode(competition.getCode());
        save(competition);
    }

    boolean existsByCode(String code);

    private void validateCompetitionCode(String code){
        if (!isValidCompetitionCodeFormat(code)){
            throw new CompetitionValidationException("Le format du code de la compétition est invalide.");
        }

        if (existsByCode(code)) {
            throw new CompetitionValidationException("Un code de compétition similaire existe déjà dans la base de données.");
        }
    }

    private boolean isValidCompetitionCodeFormat(String code){
        String pattern = "^[a-zA-Z]+-\\d{2}-\\d{2}-\\d{2}$";
        return code.matches(pattern);
    }

}
