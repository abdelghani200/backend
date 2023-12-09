package com.aftas.backend.repository;

import com.aftas.backend.models.entities.Ranking;
import com.aftas.backend.models.entities.RankingEmbedded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface rankingRepository extends JpaRepository<Ranking,RankingEmbedded> {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Ranking r WHERE r.id.competitionCode = :competitionCode AND r.id.memberNum = :memberNum")
    boolean isMemberAlreadyRegistered(@Param("memberNum") Long memberNum, @Param("competitionCode") String competitionCode);


}
