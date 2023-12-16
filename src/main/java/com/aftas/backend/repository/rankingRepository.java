package com.aftas.backend.repository;

import com.aftas.backend.models.entities.Competition;
import com.aftas.backend.models.entities.Ranking;
import com.aftas.backend.models.entities.RankingEmbedded;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface rankingRepository extends JpaRepository<Ranking,RankingEmbedded> {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Ranking r WHERE r.id.competitionCode = :competitionCode AND r.id.memberNum = :memberNum")
    boolean isMemberAlreadyRegistered(@Param("memberNum") Long memberNum, @Param("competitionCode") String competitionCode);

    @Query("SELECT COUNT(r) FROM Ranking r WHERE r.id.competitionCode = :competitionCode")
    long countByCompetitionCode(@Param("competitionCode") String competitionCode);

    @Query("SELECT r FROM Ranking r WHERE r.id.competitionCode = :competitionCode AND r.id.memberNum = :memberNum")
    Optional<Ranking> findByCompetitionAndMember(@Param("competitionCode") String competitionCode, @Param("memberNum") Long memberNum);

    List<Ranking> findByCompetition(Competition competition);

    List<Ranking> findByCompetitionOrderByScoreDesc(Competition competition);

}
