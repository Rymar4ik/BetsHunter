package org.lamaspap.betshunter.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "leonbets.client")
public record LeonBetsClientProperties(String baseUrl,
                                       int connectTimeout,
                                       int readTimeout,
                                       int writeTimeout) {
}