package com.fyttaiscoding.cachingproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import com.fyttaiscoding.cachingproxy.config.ProxyProperties;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(ProxyProperties.class)
public class CachingproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingproxyApplication.class, args);
	}

}
