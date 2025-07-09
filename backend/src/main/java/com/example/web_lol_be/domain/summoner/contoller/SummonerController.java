package com.example.web_lol_be.domain.summoner.contoller;

import com.example.web_lol_be.domain.summoner.entity.Summoner;
import com.example.web_lol_be.domain.summoner.service.SummonerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/summoner")
@RequiredArgsConstructor
public class SummonerController {

    private final SummonerService summonerService;

    // POST: 소환사 저장 테스트
    @PostMapping
    public ResponseEntity<Summoner> create(@RequestBody Summoner summoner) {
        Summoner saved = summonerService.save(summoner);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Summoner> getSummoner(@PathVariable String name) {
        Summoner summoner = summonerService.getByName(name);
        return ResponseEntity.ok(summoner);
    }
}
