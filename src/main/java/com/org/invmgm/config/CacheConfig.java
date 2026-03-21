package com.org.invmgm.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import java.util.concurrent.TimeUnit;

/**
 * CacheConfig — Caffeine (local/dev) vs Redis (prod) split by profile.
 *
 * Industry best practices applied here:
 *  1. Named cache regions with different TTLs per business domain
 *  2. recordStats() for observability (hit rate, evictions)
 *  3. maximumSize to prevent unbounded memory growth
 *  4. expireAfterWrite (not Access) — safer for frequently-read stale data
 */
@Configuration
public class CacheConfig {

    // ── Active on 'default' and 'dev' profiles ──
    @Bean
    @Primary
    @Profile("!redis")
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager();

        // Different TTL per cache region — industry standard
        manager.registerCustomCache("users",
            Caffeine.newBuilder()
                .maximumSize(200)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .recordStats()
                .build());

        // Fallback default for any unregistered cache name
        manager.setCaffeine(Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .recordStats());

        return manager;
    }
}
