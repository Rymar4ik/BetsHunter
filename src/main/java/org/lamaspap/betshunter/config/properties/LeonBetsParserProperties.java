package org.lamaspap.betshunter.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "leonbets.parser")
public record LeonBetsParserProperties(List<String> sports,
                                       int eventsLimitThreshold) {
}
