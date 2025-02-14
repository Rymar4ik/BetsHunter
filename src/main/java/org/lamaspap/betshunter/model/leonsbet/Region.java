package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;

/**
 * Represents a region that consists of a list of leagues.
 * A region groups related leagues in a logical manner within the sports domain.
 *
 * @param leagues the list of leagues associated with the region
 */
public record Region(List<League> leagues) {
}