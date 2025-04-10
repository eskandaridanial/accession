package com.accession.io.infrastructure.providers.implementations;

import com.accession.io.infrastructure.providers.CacheProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-11 13:27:00
 */
@Component
@RequiredArgsConstructor
public class CacheProviderImpl implements CacheProvider {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Boolean put(String key, Object value, Date expiresAt) {
        long remainingTTL = expiresAt.getTime() - System.currentTimeMillis();
        return redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofMillis(remainingTTL));
    }

    @Override
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }
}
