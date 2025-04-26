package com.fyttaiscoding.cachingproxy.services;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fyttaiscoding.cachingproxy.config.ProxyProperties;


@Service
public class ProxyService {

  private final RestTemplate restTemplate;
  private final Cache cache;
  private final String originUrl;

  public ProxyService(RestTemplate restTemplate, 
                      CacheManager cacheManager,
                      ProxyProperties proxyProperties) {
    this.restTemplate = restTemplate;
    this.cache = cacheManager.getCache("proxyCache");
    this.originUrl = proxyProperties.getOriginUrl();
  }

  public ResponseEntity<String> forwardRequest(String path) {
    String url = originUrl + path;
    // Check if the response is already cached
    Cache.ValueWrapper cached = cache.get(url);
    if (cached != null) {
      return ResponseEntity.ok()
        .header("X-Cache", "HIT")
        .body((String) cached.get());
    }

    // If not cached, make the request
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    if (response.getStatusCode().is2xxSuccessful()) {
      cache.put(url, response.getBody());
    }

    return ResponseEntity.ok()
      .header("X-Cache", "MISS")
      .body(response.getBody());
  }

  public void clearCache() {
    cache.clear();
  }

}
