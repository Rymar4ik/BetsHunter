package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;

/**
 * Represents a betting market, which contains a list of possible outcomes.
 *
 * @param name    the name of the market
 * @param runners the list of runners, which represent the outcomes for this betting event
 */
public record Market(String name,
                     List<Runner> runners) {
}