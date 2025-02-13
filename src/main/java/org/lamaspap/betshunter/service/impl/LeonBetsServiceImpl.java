package org.lamaspap.betshunter.service.impl;

import org.lamaspap.betshunter.client.LeonBetsClient;
import org.lamaspap.betshunter.model.leonsbet.Event;
import org.lamaspap.betshunter.model.leonsbet.Sport;
import org.lamaspap.betshunter.service.LeonBetsService;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.lamaspap.betshunter.util.HttpUtil.executeCallAndUnwrapResponse;

@Component
public class LeonBetsServiceImpl implements LeonBetsService {
    private final String CTAG = "en-US";
    private final String VTAG = "9c2cd386-31e1-4ce9-a140-28e9b63a9300";

    private final LeonBetsClient leonBetsClient;

    public LeonBetsServiceImpl(LeonBetsClient leonBetsClient) {
        this.leonBetsClient = leonBetsClient;
    }

    @Override
    public List<Sport> getAllSports() {
        return executeCallAndUnwrapResponse(leonBetsClient.getAllSports(CTAG, "urlv2"));
    }

    public List<Event> getLeagueEvents(String leagueId, int limit) {
        return executeCallAndUnwrapResponse(leonBetsClient.getAllEventsForLeague(leagueId, CTAG, VTAG, true, "eg,urlv2,mm2,rrc,nodup"))
                .events()
                .stream()
                .limit(limit)
                .toList();
    }

    public Event getFullEventData(String eventId) {
        return executeCallAndUnwrapResponse(leonBetsClient.geFullEventData(eventId, CTAG, VTAG, "reg,urlv2,rrc,nodup,smgv2,outv2"));
    }
}
