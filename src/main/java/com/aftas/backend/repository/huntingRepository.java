package com.aftas.backend.repository;

import com.aftas.backend.models.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface huntingRepository extends JpaRepository<Hunting, Integer> {

    @Query("SELECT h FROM Hunting h " +
            "WHERE h.member.num = :memberId " +
            "AND h.fish.name = :fishName " +
            "AND h.competition.code = :competitionCode")
    Hunting findByMemberAndFishAndCompetition(
            @Param("memberId") Long memberId,
            @Param("fishName") String fishName,
            @Param("competitionCode") String competitionCode
    );



}
