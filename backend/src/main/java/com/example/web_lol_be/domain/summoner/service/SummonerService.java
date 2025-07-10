package com.example.web_lol_be.domain.summoner.service;

import com.example.web_lol_be.domain.summoner.entity.Summoner;
import com.example.web_lol_be.domain.summoner.repository.SummonerRepository;
import com.example.web_lol_be.global.riot.RiotApiClient;
import com.example.web_lol_be.global.riot.dto.RiotAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;


/*
    Redis → DB → Riot API 순으로 소환사 정보를 조회합니다.
    조회 시 캐시에 저장하여 반복 요청 시 빠르게 응답할 수 있습니다.
*/
@Service
@RequiredArgsConstructor
public class SummonerService {

    private final RiotApiClient riotApiClient;
    private final SummonerRepository summonerRepository;
    private final RedisTemplate<String, Summoner> redisTemplate;

    public Summoner getOrCreateSummoner(String gameName, String tagLine) {
        /*
            1. Redis 캐시 조회
            key 형식: Hide on bush#KR1
        */
        String cacheKey = gameName + "#" + tagLine;
        Summoner cached = redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) return cached;

        /*
            2. DB에서 조회
         */
        Optional<Summoner> found = summonerRepository.findByGameNameAndTagLine(gameName, tagLine);
        if (found.isPresent()) {
            redisTemplate.opsForValue().set(cacheKey, found.get(), Duration.ofMinutes(30));
            return found.get();
        }

        /*
            3. Riot API 호출 및 저장
         */
        RiotAccountDto dto = riotApiClient.getAccountByRiotId(gameName, tagLine);
        Summoner newSummoner = summonerRepository.save(Summoner.builder()
                .puuid(dto.getPuuid())
                .gameName(dto.getGameName())
                .tagLine(dto.getTagLine())
                .build());

        // 캐시에 저장 (30분 유효)
        redisTemplate.opsForValue().set(cacheKey, newSummoner, Duration.ofMinutes(30));

        return newSummoner;
    }
}