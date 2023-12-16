package com.aftas.backend.repository;

import com.aftas.backend.models.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface fishRepository extends JpaRepository<Fish, Long> {

    @Query("SELECT f FROM Fish f WHERE LOWER(f.name) = LOWER(:name)")
    Optional<Fish> findByName(String name);

}
