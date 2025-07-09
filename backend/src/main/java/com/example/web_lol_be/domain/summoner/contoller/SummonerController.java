package com.example.web_lol_be.domain.summoner.contoller;

import com.example.web_lol_be.domain.summoner.entity.Summoner;
import com.example.web_lol_be.domain.summoner.service.SummonerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/summoner")
@RequiredArgsConstructor
public class SummonerController {

    private final SummonerService summonerService;

    @GetMapping("/{name}")
    public ResponseEntity<Summoner> getSummoner(@PathVariable String name) {
        Summoner summoner = summonerService.getByName(name);
        return ResponseEntity.ok(summoner);
    }
}
