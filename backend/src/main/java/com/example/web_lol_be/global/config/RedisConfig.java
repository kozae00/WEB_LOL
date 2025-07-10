package com.example.web_lol_be.global.config;

import com.example.web_lol_be.domain.summoner.entity.Summoner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/*
    RedisTemplate 설정 클래스입니다.
    String 키와 Java 객체 값 간의 직렬화를 처리합니다.
*/
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Summoner> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Summoner> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 문자열 key 직렬화
        template.setKeySerializer(new StringRedisSerializer());

        // 객체 value 직렬화
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}