package com.example.web_lol_be.global.riot;

import com.example.web_lol_be.domain.summoner.entity.Summoner;
import com.example.web_lol_be.global.riot.dto.RiotSummonerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class RiotApiClient {

    private final WebClient webClient;

    public RiotApiClient(@Value("${riot.api-key}") String apiKey) {
        this.webClient = WebClient.builder()
                .defaultHeader("X-Riot-Token", apiKey)
                .baseUrl("https://kr.api.riotgames.com")
                .build();
    }

    public Summoner fetchSummonerByName(String name) {
        // Riot API의 응답을 Summoner 엔티티로 직접 매핑하려면 DTO를 거쳐 변환 필요
        RiotSummonerDto dto = webClient.get()
                .uri("/lol/summoner/v4/summoners/by-name/{name}", name)
                .retrieve()
                .bodyToMono(RiotSummonerDto.class)
                .block();

        return Summoner.builder()
                .id(dto.getId())
                .puuid(dto.getPuuid())
                .accountId(dto.getAccountId())
                .name(dto.getName())
                .profileIconId(dto.getProfileIconId())
                .summonerLevel(dto.getSummonerLevel())
                .build();
    }
}