package org.lamaspap.betshunter.service;

import org.lamaspap.betshunter.model.leonsbet.Event;
import org.lamaspap.betshunter.model.leonsbet.Sport;

import java.util.List;

/**
 * Provides an abstraction for interacting with the LeonBets site to retrieve data about sports,
 * leagues, and events. This service offers functionalities to fetch available sports, league events,
 * and detailed information about specific events.
 */
public interface LeonBetsService {

    /**
     * Retrieves all available sports from the LeonBets site.
     *
     * @return a list of {@link Sport} objects representing all supported sports
     */
    List<Sport> getAllSports();

    /**
     * Retrieves a limited number of events for a specified league.
     *
     * @param leagueId the unique identifier of the league
     * @param limit    the maximum number of events to retrieve
     * @return a list of {@link Event} objects for the specified league, limited by the given number
     */
    List<Event> getLeagueEvents(String leagueId, int limit);

    /**
     * Fetches full details of a specific event by its unique identifier.
     *
     * @param eventId the unique identifier of the event
     * @return an {@link Event} object containing detailed data about the specified event
     */
    Event getFullEventData(String eventId);
}

