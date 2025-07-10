package com.example.web_lol_be.domain.summoner.contoller;

import com.example.web_lol_be.domain.summoner.service.SummonerService;
import com.example.web_lol_be.global.riot.dto.RiotAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/summoner")
@RequiredArgsConstructor
public class SummonerController {

    private final SummonerService summonerService;

    @GetMapping("/by-riot-id")
    public RiotAccountDto getAccountByRiotId(@RequestParam String gameName,
                                             @RequestParam String tagLine) {
        return summonerService.getAccountByRiotId(gameName, tagLine);
    }
}
