package org.lamaspap.betshunter.model;


/**
 * Represents a report containing information about sports and their leagues.
 *
 * @param sportName     the name of the sport
 * @param leagueSummary a summary of the league containing matches and their details
 */
public record Report(String sportName,
                     LeagueSummary leagueSummary) {

}
