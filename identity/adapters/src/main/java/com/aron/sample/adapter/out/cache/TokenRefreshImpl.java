package com.aron.sample.adapter.out.cache;

import com.aron.sample.infrastructre.cache.CacheName;
import com.aron.sample.port.dto.AccountMetaDTO;
import com.aron.sample.port.out.TokenRefreshPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
class TokenRefreshImpl implements TokenRefreshPort {

  private Cache cache;

  public TokenRefreshImpl(CacheManager cacheManager) {
    this.cache = cacheManager.getCache(CacheName.TOKEN.name());
  }

  @Override
  public void refresh(String namespace, String tokenId) {
    String tokenKey = toTokenKey(namespace, tokenId);
    cache.put(tokenKey, cache.get(tokenKey, AccountMetaDTO.class));
  }

  private String toTokenKey(String namespace, String tokenId) {
    return String.format("%s:%s", namespace, tokenId);
  }

}
