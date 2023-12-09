package com.aftas.backend.repository;

import com.aftas.backend.models.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface fishRepository extends JpaRepository<Fish, Long> {
}
