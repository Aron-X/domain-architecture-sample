package com.aron.sample.infrastructre.cache;

import com.aron.sample.infrastructre.support.SuppressObjectMapper;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.concurrent.Callable;

public class StringRedisCache extends RedisCache implements Cache {

  private RedisCache cache;
  private SuppressObjectMapper objectMapper;

  public StringRedisCache(RedisCache cache, SuppressObjectMapper objectMapper,
      RedisConnectionFactory connectionFactory) {
    super(cache.getName(), RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory),
        RedisCacheConfiguration.defaultCacheConfig());
    this.cache = cache;
    this.objectMapper = objectMapper;
  }

  @Override
  public String getName() {
    return cache.getName();
  }

  @Override
  public RedisCacheWriter getNativeCache() {
    return cache.getNativeCache();
  }

  @Override
  public ValueWrapper get(Object key) {
    return cache.get(key);
  }

  @Override
  public <T> T get(Object key, Class<T> type) {
    ValueWrapper valueWrapper = cache.get(key);
    if (valueWrapper == null) {
      return null;
    }
    return objectMapper.readValue((String) valueWrapper.get(), type);
  }

  @Override
  public <T> T get(Object key, Callable<T> valueLoader) {
    return cache.get(key, valueLoader);
  }

  @Override
  public void put(Object key, Object value) {
    cache.put(key, objectMapper.toJsonString(value));
  }

  @Override
  public ValueWrapper putIfAbsent(Object key, Object value) {
    return cache.putIfAbsent(key, objectMapper.toJsonString(value));
  }

  @Override
  public void evict(Object key) {
    cache.evict(key);
  }

  @Override
  public void clear() {
    cache.clear();
  }
}
