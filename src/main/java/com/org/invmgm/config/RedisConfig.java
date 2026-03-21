package com.org.invmgm.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * RedisConfig — activate with spring.profiles.active=redis
 *
 * Industry best practices:
 *  1. JSON serialization (not Java default) for debuggability
 *  2. Per-cache TTL via entryTtl overrides
 *  3. Null values allowed to cache "not found" results and prevent cache stampede
 *  4. String key serializer for human-readable Redis keys
 */
@Configuration
@Profile("redis")
public class RedisConfig {

    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory factory) {

        // Default: JSON-serialized values + 10min TTL + nullable
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
            .disableCachingNullValues();          // flip to allowCacheNullValues() if needed

        // Per-cache TTL overrides
        Map<String, RedisCacheConfiguration> configs = new HashMap<>();
        configs.put("users", defaultConfig.entryTtl(Duration.ofMinutes(30)));

        return RedisCacheManager.builder(factory)
            .cacheDefaults(defaultConfig)
            .withInitialCacheConfigurations(configs)
            .build();
    }
}
