package com.example.web_lol_be.global.riot;

import com.example.web_lol_be.global.riot.dto.LeagueEntryDto;
import com.example.web_lol_be.global.riot.dto.RiotAccountDto;
import com.example.web_lol_be.global.riot.dto.SummonerDetailDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class RiotApiClient {

    private final WebClient krWebClient;     // KR 서버용
    private final WebClient asiaWebClient;   // ASIA 서버용 (account API)

    public RiotApiClient(@Value("${riot.api-key}") String apiKey) {
        this.krWebClient = WebClient.builder()
                .baseUrl("https://kr.api.riotgames.com")
                .defaultHeader("X-Riot-Token", apiKey)
                .build();

        this.asiaWebClient = WebClient.builder()
                .baseUrl("https://asia.api.riotgames.com")
                .defaultHeader("X-Riot-Token", apiKey)
                .build();
    }

    /*
     * Riot ID (gameName + tagLine) 기반으로 PUUID 조회
     * base URL: https://asia.api.riotgames.com
     */
    public RiotAccountDto getAccountByRiotId(String gameName, String tagLine) {
        return asiaWebClient.get()
                .uri("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}", gameName, tagLine)
                .retrieve()
                .bodyToMono(RiotAccountDto.class)
                .block();
    }

    /*
     * puuid 기반으로 소환사 상세 정보 조회 (level, name, summonerId 등)
     * base URL: https://kr.api.riotgames.com
     */
    public SummonerDetailDto getSummonerDetailByPuuid(String puuid) {
        String rawJson = krWebClient.get()
                .uri("/lol/summoner/v4/summoners/by-puuid/{puuid}", puuid)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("Raw response = " + rawJson);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(rawJson, SummonerDetailDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * summonerId 기반으로 랭크 정보(솔랭/자유랭크 등) 조회
     * base URL: https://kr.api.riotgames.com
     */
    public List<LeagueEntryDto> getLeagueEntriesBySummonerId(String summonerId) {
        return krWebClient.get()
                .uri("/lol/league/v4/entries/by-summoner/{summonerId}", summonerId)
                .retrieve()
                .bodyToFlux(LeagueEntryDto.class)
                .collectList()
                .block();
    }
}