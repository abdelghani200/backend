package com.aftas.backend.repository;

import com.aftas.backend.models.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface levelRepository extends JpaRepository<Level, Integer> {

}
