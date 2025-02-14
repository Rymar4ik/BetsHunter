package org.lamaspap.betshunter.service.impl;

import org.lamaspap.betshunter.client.LeonBetsClient;
import org.lamaspap.betshunter.model.leonsbet.Event;
import org.lamaspap.betshunter.model.leonsbet.Sport;
import org.lamaspap.betshunter.service.LeonBetsService;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.lamaspap.betshunter.util.HttpUtil.executeCallAndUnwrapResponse;


/**
 * Implementation of the {@link LeonBetsService} interface for interacting with the LeonBets client.
 * Provides methods to retrieve sports, league events, and detailed event data.
 */
@Component
public class LeonBetsServiceImpl implements LeonBetsService {
    private final String CTAG = "en-US";
    private final String VTAG = "9c2cd386-31e1-4ce9-a140-28e9b63a9300";

    private final LeonBetsClient leonBetsClient;

    /**
     * Constructs a new instance of {@code LeonBetsServiceImpl}.
     *
     * @param leonBetsClient the client used to interact with the LeonBets API
     */
    public LeonBetsServiceImpl(LeonBetsClient leonBetsClient) {
        this.leonBetsClient = leonBetsClient;
    }

    /**
     * Retrieves a list of all available sports from the LeonBets platform.
     *
     * @return a {@link List} of {@link Sport} objects representing all available sports
     */
    @Override
    public List<Sport> getAllSports() {
        return executeCallAndUnwrapResponse(leonBetsClient.getAllSports(CTAG, "urlv2"));
    }

    /**
     * Retrieves a limited number of events for a specific league.
     *
     * @param leagueId the ID of the league
     * @param limit    the maximum number of events to retrieve
     * @return a {@link List} of {@link Event} objects representing the league's events
     */
    public List<Event> getLeagueEvents(String leagueId, int limit) {
        return executeCallAndUnwrapResponse(leonBetsClient.getAllEventsForLeague(leagueId, CTAG, VTAG, true, "eg,urlv2,mm2,rrc,nodup"))
                .events()
                .stream()
                .limit(limit)
                .toList();
    }

    /**
     * Retrieves detailed data for a specific event.
     *
     * @param eventId the ID of the event
     * @return an {@link Event} object containing detailed data for the specified event
     */
    public Event getFullEventData(String eventId) {
        return executeCallAndUnwrapResponse(leonBetsClient.geFullEventData(eventId, CTAG, VTAG, "reg,urlv2,rrc,nodup,smgv2,outv2"));
    }
}
