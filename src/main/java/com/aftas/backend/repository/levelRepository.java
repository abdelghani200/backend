package com.aftas.backend.repository;

import com.aftas.backend.models.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface levelRepository extends JpaRepository<Level, Integer> {

    @Query("SELECT l.points FROM Level l ORDER BY l.code DESC")
    Integer findPointsOfLatestLevel();

}
