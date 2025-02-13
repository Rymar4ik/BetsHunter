package org.lamaspap.betshunter.service;

import org.lamaspap.betshunter.model.leonsbet.Event;
import org.lamaspap.betshunter.model.leonsbet.Sport;

import java.util.List;

public interface LeonBetsService {

    List<Sport> getAllSports();

    List<Event> getLeagueEvents(String leagueId, int limit);

    Event getFullEventData(String eventId);
}

