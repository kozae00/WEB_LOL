package com.example.web_lol_be.domain.summoner.entity;

import jakarta.persistence.*;
import lombok.*;


/*
    소환사 정보를 저장하는 JPA Entity입니다.
    puuid는 Riot에서 발급되는 고유한 ID이며, 유일해야 하므로 unique 제약이 있습니다.
*/
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Summoner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String puuid;

    @Column(nullable = false)
    private String gameName;

    @Column(nullable = false)
    private String tagLine;
}
