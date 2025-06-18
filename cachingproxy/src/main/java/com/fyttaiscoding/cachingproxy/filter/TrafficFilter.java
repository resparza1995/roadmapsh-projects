package com.fyttaiscoding.cachingproxy.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fyttaiscoding.cachingproxy.config.ProxyFilterConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TrafficFilter extends OncePerRequestFilter {

    private final ProxyFilterConfig config;

    public TrafficFilter(ProxyFilterConfig config) {
        this.config = config;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        String path = request.getRequestURI().toLowerCase();
        String userAgent = Optional.ofNullable(request.getHeader("User-Agent")).orElse("").toLowerCase();

        if (config.getBlockedPaths().stream().anyMatch(path::contains)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acceso bloqueado por patrón de URL.");
            return;
        }

        if (config.getBlockedUserAgents().stream().anyMatch(userAgent::contains)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acceso denegado: User-Agent bloqueado.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}

