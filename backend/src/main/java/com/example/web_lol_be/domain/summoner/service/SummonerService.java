package com.example.web_lol_be.domain.summoner.service;

import com.example.web_lol_be.global.riot.RiotApiClient;
import com.example.web_lol_be.global.riot.dto.RiotAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummonerService {

    private final RiotApiClient riotApiClient;

    public RiotAccountDto getAccountByRiotId(String gameName, String tagLine) {
        return riotApiClient.getAccountByRiotId(gameName, tagLine);
    }
}