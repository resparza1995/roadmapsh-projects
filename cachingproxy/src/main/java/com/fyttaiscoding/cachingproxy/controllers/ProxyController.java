package com.fyttaiscoding.cachingproxy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fyttaiscoding.cachingproxy.services.ProxyService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class ProxyController {

  private final ProxyService proxyService;

  public ProxyController(ProxyService proxyService) {
    this.proxyService = proxyService;
  }

  /**
   * Handles GET requests to the proxy endpoint.
   * 
   * @param request The HttpServletRequest object containing the request details.
   * @return A ResponseEntity containing the response from the proxied request.
   * @throws Exception If an error occurs while handling the request.
   */
  @GetMapping("/**")
  public ResponseEntity<String> proxyRequest(HttpServletRequest request, @RequestHeader(value = "X-Clear-Cache", required = false) String clearCacheHeader) {
    String requestPath = request.getRequestURI().substring(request.getContextPath().length());

    if (clearCacheHeader != null && clearCacheHeader.equalsIgnoreCase("true")) {
      proxyService.clearCache();
      return ResponseEntity.ok("Cache cleared");
    }

    return proxyService.forwardRequest(requestPath);
  }

  @PostMapping("/clear-cache")
  public ResponseEntity<String> clearCache() {
      proxyService.clearCache();
      return ResponseEntity.ok("Cache cleared");
  }
  

}
