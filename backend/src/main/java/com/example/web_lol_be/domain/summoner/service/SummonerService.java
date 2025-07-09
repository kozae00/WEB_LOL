package com.example.web_lol_be.domain.summoner.service;

import com.example.web_lol_be.domain.summoner.entity.Summoner;
import com.example.web_lol_be.domain.summoner.repository.SummonerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummonerService {

    private final SummonerRepository summonerRepository;

    public Summoner getByName(String name) {
        return summonerRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("소환사를 찾을 수 없습니다."));
    }

    public Summoner save(Summoner summoner) {
        return summonerRepository.save(summoner);
    }
}
