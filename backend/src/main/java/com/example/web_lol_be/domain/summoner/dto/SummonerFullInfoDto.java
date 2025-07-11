package com.example.web_lol_be.domain.summoner.dto;

import com.example.web_lol_be.global.riot.dto.LeagueEntryDto;
import com.example.web_lol_be.global.riot.dto.RiotAccountDto;
import com.example.web_lol_be.global.riot.dto.SummonerDetailDto;
import lombok.Getter;

import java.util.List;

@Getter
public class SummonerFullInfoDto {
    private String gameName;
    private String tagLine;
    private long summonerLevel;
    private List<LeagueEntryDto> leagues;

    public static SummonerFullInfoDto of(RiotAccountDto account, SummonerDetailDto detail, List<LeagueEntryDto> leagues) {
        SummonerFullInfoDto dto = new SummonerFullInfoDto();
        dto.gameName = account.getGameName();
        dto.tagLine = account.getTagLine();
        dto.summonerLevel = detail.getSummonerLevel();
        dto.leagues = leagues;
        return dto;
    }
}
