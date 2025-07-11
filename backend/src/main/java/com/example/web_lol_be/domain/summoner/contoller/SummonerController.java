package com.example.web_lol_be.domain.summoner.contoller;

import com.example.web_lol_be.domain.summoner.dto.SummonerFullInfoDto;
import com.example.web_lol_be.domain.summoner.entity.Summoner;
import com.example.web_lol_be.domain.summoner.service.SummonerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/*
    클라이언트에서 /api/v1/summoner/by-riot-id?gameName=X&tagLine=Y
    형태로 요청 시 DB에 저장된 Summoner 정보를 반환합니다.
*/
@RestController
@RequestMapping("/api/v1/summoner")
@RequiredArgsConstructor
public class SummonerController {

    private final SummonerService summonerService;

    @GetMapping("/by-riot-id")
    public Summoner getOrCreateSummoner(@RequestParam String gameName,
                                        @RequestParam String tagLine) {
        return summonerService.getOrCreateSummoner(gameName, tagLine);
    }

    @GetMapping("/full-info")
    public SummonerFullInfoDto getFullSummonerInfo(@RequestParam String gameName, @RequestParam String tagLine) {
        return summonerService.getFullInfo(gameName, tagLine);
    }
}
