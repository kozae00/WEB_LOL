package com.example.web_lol_be.global.riot.dto;

import lombok.Getter;

@Getter
public class LeagueEntryDto {
    private String leagueId;
    private String queueType;       // RANKED_SOLO_5x5, RANKED_FLEX_SR 등
    private String tier;            // GOLD, PLATINUM 등
    private String rank;            // I, II, III, IV
    private int leaguePoints;
    private int wins;
    private int losses;
}