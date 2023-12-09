package com.aftas.backend.repository;

import com.aftas.backend.models.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface memberRepository extends JpaRepository<Member,Long> {


}
