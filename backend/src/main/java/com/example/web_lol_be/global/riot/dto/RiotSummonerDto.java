package com.example.web_lol_be.global.riot.dto;

import lombok.Data;

@Data
public class RiotSummonerDto {
    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private int profileIconId;
    private int summonerLevel;
}
