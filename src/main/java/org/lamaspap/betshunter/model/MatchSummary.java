package org.lamaspap.betshunter.model;

import java.time.Instant;
import java.util.List;

/**
 * Represents a summary of a match, including its ID, name, start date/time, and associated markets.
 *
 * @param id            the unique identifier of the match
 * @param name          the name of the match
 * @param startDateTime the start date and time of the match
 * @param markets       the list of markets associated with the match
 */
public record MatchSummary(String id,
                           String name,
                           Instant startDateTime,
                           List<MarketSummary> markets) {

}
