package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;

/**
 * Represents a sport with its name and associated regions, containing leagues and events.
 *
 * @param name    the name of the sport
 * @param regions the list of regions associated with the sport
 */
public record Sport(String name,
                    List<Region> regions) {
}
