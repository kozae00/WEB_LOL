package com.example.web_lol_be.domain.summoner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Summoner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String summonerId;
    private String puuid;
    private String tier;
    private String rank;
    private int wins;
    private int losses;
}
