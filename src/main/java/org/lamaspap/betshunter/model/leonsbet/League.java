package org.lamaspap.betshunter.model.leonsbet;

/**
 * Represents a league with an identifier, its top status, and name.
 *
 * @param id   the unique identifier of the league
 * @param top  a flag indicating if the league is marked as a top league
 * @param name the name of the league
 */
public record League(String id,
                     boolean top,
                     String name) {
}