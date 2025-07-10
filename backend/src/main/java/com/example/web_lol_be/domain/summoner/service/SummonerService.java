package com.example.web_lol_be.domain.summoner.service;

import com.example.web_lol_be.domain.summoner.entity.Summoner;
import com.example.web_lol_be.domain.summoner.repository.SummonerRepository;
import com.example.web_lol_be.global.riot.RiotApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SummonerService {

    private final SummonerRepository summonerRepository;
    private final RiotApiClient riotApiClient;

    public Summoner getSummonerByName(String name) {
        return summonerRepository.findByName(name)
                .orElseGet(() -> {
                    Summoner summoner = riotApiClient.fetchSummonerByName(name);

                    Summoner entity = Summoner.builder()
                            .id(summoner.getId())
                            .puuid(summoner.getPuuid())
                            .accountId(summoner.getAccountId())
                            .name(summoner.getName())
                            .profileIconId(summoner.getProfileIconId())
                            .summonerLevel(summoner.getSummonerLevel())
                            .lastUpdated(LocalDateTime.now()) // 이 방식으로 setter 제거
                            .build();

                    return summonerRepository.save(entity);
                });
    }

}