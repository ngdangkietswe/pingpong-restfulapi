package dev.ngdangkiet.pingpongrestfulapi.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author ngdangkiet
 * @since 9/21/2023
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisUtil {
    private final ObjectMapper mapper;
    private final RedisTemplate<String, String> redisTemplate;

    public <D> void put(String key, D data) {
        if (Objects.isNull(data)) {
            log.warn("Data is null!");
            return;
        }

        try {
            redisTemplate.opsForValue().set(key, mapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            log.error("Failed to cache data for key {}. Error: {}", key, e.getMessage());
        }
    }

    public <D> void putList(String key, List<D> data, Duration duration) {
        if (CollectionUtils.isEmpty(data)) {
            log.warn("Data is null!");
            return;
        }

        try {
            redisTemplate.opsForValue().set(key, mapper.writeValueAsString(data), duration);
        } catch (JsonProcessingException e) {
            log.error("Failed to cache data for key {}. Error: {}", key, e.getMessage());
        }
    }

    public <D> D get(String key, Class<D> clazz) {
        Object data = redisTemplate.opsForValue().get(key);
        if (Objects.isNull(data)) {
            log.debug("Cache miss for key {}", key);
            return null;
        }

        return mapper.convertValue(data, clazz);
    }

    public <D> List<D> getList(String key, Class<D> clazz) {
        Object data = redisTemplate.opsForValue().get(key);
        if (Objects.isNull(data)) {
            log.debug("Cache miss for key {}", key);
            return Collections.emptyList();
        }

        try {
            return mapper.readValue(data.toString(), new TypeReference<List<D>>() {
                    })
                    .stream()
                    .map(i -> mapper.convertValue(i, clazz))
                    .toList();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
