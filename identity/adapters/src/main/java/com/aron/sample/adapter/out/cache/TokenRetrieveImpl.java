package com.aron.sample.adapter.out.cache;

import com.aron.sample.infrastructre.cache.CacheName;
import com.aron.sample.port.dto.AccountMetaDTO;
import com.aron.sample.port.out.TokenRetrievePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
class TokenRetrieveImpl implements TokenRetrievePort {

  private Cache cache;

  public TokenRetrieveImpl(CacheManager cacheManager) {
    this.cache = cacheManager.getCache(CacheName.TOKEN.name());
  }

  @Override
  public AccountMetaDTO getBy(String namespace, String tokenId) {
    return cache.get(toTokenKey(namespace, tokenId), AccountMetaDTO.class);
  }

  private String toTokenKey(String namespace, String tokenId) {
    return String.format("%s:%s", namespace, tokenId);
  }

}
