package com.example.web_lol_be.domain.summoner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Summoner {

    @Id
    private String id;

    private String puuid;
    private String accountId;
    private String name;
    private int profileIconId;
    private int summonerLevel;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;
    private LocalDateTime lastUpdated;
}
