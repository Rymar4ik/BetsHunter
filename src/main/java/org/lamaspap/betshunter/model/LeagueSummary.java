package org.lamaspap.betshunter.model;

import java.util.List;

/**
 * Represents a summary of a league, including its name and the matches played within the league.
 *
 * @param name    the name of the league
 * @param matches the list of matches associated with the league
 */
public record LeagueSummary(String name,
                            List<MatchSummary> matches) {
}
