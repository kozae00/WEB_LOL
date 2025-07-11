package com.example.web_lol_be.global.riot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true) // 응답에 정의되지 않은 필드는 무시
public class SummonerDetailDto {
    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private int profileIconId;
    private long summonerLevel;
}

