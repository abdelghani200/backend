package com.aftas.backend.repository;

import com.aftas.backend.enums.IdentityDocumentType;
import com.aftas.backend.models.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface memberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m FROM Member m WHERE LOWER(m.name) = LOWER(:searchTerm) OR CAST(m.num AS string) = :searchTerm OR LOWER(m.familyName) = LOWER(:searchTerm)")
    List<Member> searchMembers(@Param("searchTerm") String searchTerm);
}
