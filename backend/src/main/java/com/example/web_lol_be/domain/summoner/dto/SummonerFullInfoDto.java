package com.example.web_lol_be.domain.summoner.dto;

import com.example.web_lol_be.global.riot.dto.LeagueEntryDto;
import com.example.web_lol_be.global.riot.dto.RiotAccountDto;
import com.example.web_lol_be.global.riot.dto.SummonerDetailDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SummonerFullInfoDto {

    private String gameName;         // Hide on bush
    private String tagLine;          // KR1
    private String name;             // 인게임 닉네임 (예: Hide on bush)
    private long summonerLevel;
    private int profileIconId;

    private List<LeagueEntryDto> leagueEntries;

    public static SummonerFullInfoDto of(RiotAccountDto account, SummonerDetailDto detail, List<LeagueEntryDto> leagues) {
        return SummonerFullInfoDto.builder()
                .gameName(account.getGameName())
                .tagLine(account.getTagLine())
                .name(detail.getName())
                .summonerLevel(detail.getSummonerLevel())
                .profileIconId(detail.getProfileIconId())
                .leagueEntries(leagues)
                .build();
    }
}
