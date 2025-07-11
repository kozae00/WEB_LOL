package com.example.web_lol_be.global.riot.dto;

import lombok.Getter;

@Getter
public class LeagueEntryDto {
    private String queueType;      // RANKED_SOLO_5x5, RANKED_FLEX_SR
    private String tier;           // GOLD, PLATINUM, etc
    private String rank;           // I, II, III
    private int leaguePoints;
    private int wins;
    private int losses;
}