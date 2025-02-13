package org.lamaspap.betshunter.model;

import java.time.Instant;
import java.util.List;

public record MatchSummary(String id,
                           String name,
                           Instant startDateTime,
                           List<MarketSummary> markets) {

}
