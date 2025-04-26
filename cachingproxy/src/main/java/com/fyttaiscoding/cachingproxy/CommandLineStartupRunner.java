package com.fyttaiscoding.cachingproxy;

import com.fyttaiscoding.cachingproxy.config.ProxyProperties;
import com.fyttaiscoding.cachingproxy.services.ProxyService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineStartupRunner implements CommandLineRunner {

  private final ProxyService proxyService;
  private final ProxyProperties proxyProperties;

  public CommandLineStartupRunner(ProxyService proxyService, ProxyProperties proxyProperties) {
    this.proxyService = proxyService;
    this.proxyProperties = proxyProperties;
  }

  @Override
  public void run(String... args) {
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];

      if ("--port".equals(arg)) {
        if (i + 1 < args.length) {
          String port = args[i + 1];
          System.setProperty("server.port", port);
          i++;
        } else {
          System.err.println("Error port: example --port 3000.");
          System.exit(1);
        }
      } else if ("--origin".equals(arg)) {
        if (i + 1 < args.length) {
          String originUrl = args[i + 1];
          proxyProperties.setOriginUrl(originUrl);
          i++;
        } else {
          System.err.println("Error origin: example --origin http://dummyjson.com).");
          System.exit(1);
        }
      } else if ("--clear-cache".equals(arg)) {
        proxyService.clearCache();
        System.out.println("Cache cleared.");
        System.exit(0);
      }
    }
  }
}
