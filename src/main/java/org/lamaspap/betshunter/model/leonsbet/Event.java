package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;

/**
 * Represents an event in a sports context, including its identifier, name,
 * kickoff time, and associated markets.
 *
 * @param id      the unique identifier of the event
 * @param name    the name of the event
 * @param kickoff the start time of the event, represented as a Unix timestamp in milliseconds
 * @param markets the list of markets associated with the event
 */
public record Event(String id,
                    String name,
                    long kickoff,
                    List<Market> markets) {
}