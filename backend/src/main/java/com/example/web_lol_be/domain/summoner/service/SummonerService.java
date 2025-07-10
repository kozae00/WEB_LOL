package com.example.web_lol_be.domain.summoner.service;

import com.example.web_lol_be.domain.summoner.entity.Summoner;
import com.example.web_lol_be.domain.summoner.repository.SummonerRepository;
import com.example.web_lol_be.global.riot.RiotApiClient;
import com.example.web_lol_be.global.riot.dto.RiotAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/*
    아래 서비스는 Redis 캐시는 생략하고,
    DB → 없으면 Riot API 호출 → DB 저장하는 기본 구조입니다.
*/
@Service
@RequiredArgsConstructor
public class SummonerService {

    private final RiotApiClient riotApiClient;
    private final SummonerRepository summonerRepository;

    /*
        Riot ID (gameName + tagLine)를 기반으로 소환사 정보를 조회합니다.
        DB에 이미 존재하면 반환하고, 없으면 Riot API를 통해 받아온 후 저장합니다.
     */
    public Summoner getOrCreateSummoner(String gameName, String tagLine) {
        return summonerRepository.findByGameNameAndTagLine(gameName, tagLine)
                .orElseGet(() -> {
                    RiotAccountDto dto = riotApiClient.getAccountByRiotId(gameName, tagLine);
                    Summoner newSummoner = Summoner.builder()
                            .puuid(dto.getPuuid())
                            .gameName(dto.getGameName())
                            .tagLine(dto.getTagLine())
                            .build();
                    return summonerRepository.save(newSummoner);
                });
    }
}