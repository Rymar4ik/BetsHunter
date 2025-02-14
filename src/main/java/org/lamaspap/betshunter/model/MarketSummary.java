package org.lamaspap.betshunter.model;

import java.util.List;

/**
 * Represents a summary of a market, including its name and the list of outcomes associated with it.
 *
 * @param name     the name of the market
 * @param outcomes the list of outcomes available in the market
 */
public record MarketSummary(String name,
                            List<OutcomeSummary> outcomes) {
}
