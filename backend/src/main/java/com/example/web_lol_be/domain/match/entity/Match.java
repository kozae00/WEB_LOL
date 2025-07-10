package com.example.web_lol_be.domain.match.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Match {
    @Id
    private String matchId;
    private LocalDateTime gameStartTime;
    private String gameMode;
    private int duration;
}
