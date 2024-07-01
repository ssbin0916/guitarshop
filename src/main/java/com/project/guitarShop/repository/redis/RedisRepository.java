package com.project.guitarShop.repository.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class RedisRepository {

    private RedisTemplate<String, String> redisTemplate;

    public RedisRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean lock(String key) {
        return redisTemplate
                .opsForValue()
                .setIfAbsent(generateKey(key), "lock", Duration.ofMillis(3000));
    }

    public Boolean unlock(String key) {
        return redisTemplate.delete(generateKey(key));
    }

    private String generateKey(String key) {
        return key.toString();
    }

    public long increasesViewCount(Long key) {
        String redisKey = String.valueOf(key);
        return redisTemplate.opsForValue().increment(redisKey);
    }
}
