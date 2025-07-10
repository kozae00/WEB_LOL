package com.example.web_lol_be.global.riot;

import com.example.web_lol_be.global.riot.dto.RiotAccountDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class RiotApiClient {

    private final WebClient webClient;
    private final WebClient asiaWebClient;

    public RiotApiClient(@Value("${riot.api-key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://kr.api.riotgames.com")
                .defaultHeader("X-Riot-Token", apiKey)
                .build();

        this.asiaWebClient = WebClient.builder()
                .baseUrl("https://asia.api.riotgames.com")
                .defaultHeader("X-Riot-Token", apiKey)
                .build();
    }

    public RiotAccountDto getAccountByRiotId(String gameName, String tagLine) {
        return asiaWebClient.get()
                .uri("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}", gameName, tagLine)
                .retrieve()
                .bodyToMono(RiotAccountDto.class)
                .block();
    }
}