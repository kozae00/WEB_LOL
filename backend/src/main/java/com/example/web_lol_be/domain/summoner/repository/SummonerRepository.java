package com.example.web_lol_be.domain.summoner.repository;

import com.example.web_lol_be.domain.summoner.entity.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
    Summoner 엔티티에 대한 DB 접근을 위한 JPA Repository입니다.
    puuid 또는 Riot ID 기준으로 조회할 수 있도록 메서드를 정의합니다.
*/
@Repository
public interface SummonerRepository extends JpaRepository<Summoner, Long> {
    Optional<Summoner> findByPuuid(String puuid);
    Optional<Summoner> findByGameNameAndTagLine(String gameName, String tagLine);
}
