package com.fyttaiscoding.cachingproxy.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "proxy.filter")
public class ProxyFilterConfig {

    private List<String> blockedPaths;
    private List<String> blockedUserAgents;

    public List<String> getBlockedPaths() {
        return blockedPaths;
    }

    public void setBlockedPaths(List<String> blockedPaths) {
        this.blockedPaths = blockedPaths;
    }

    public List<String> getBlockedUserAgents() {
        return blockedUserAgents;
    }

    public void setBlockedUserAgents(List<String> blockedUserAgents) {
        this.blockedUserAgents = blockedUserAgents;
    }
}

