package org.lamaspap.betshunter.generator;

import org.lamaspap.betshunter.model.Report;
import org.lamaspap.betshunter.model.leonsbet.Event;

import java.util.List;

/**
 * An interface for generating reports based on sports, leagues, and associated events.
 * Implementations of this interface produce structured {@link Report} objects.
 */
public interface ReportGenerator {

    /**
     * Generates a {@link Report} for the specified sport and league based on the provided events.
     *
     * @param sportName  the name of the sport the report is being created for
     * @param leagueName the name of the league the report is being created for
     * @param fullEvents the list of events related to the league
     * @return a {@link Report} containing details of the sport, league, and its associated events
     */
    Report createReport(String sportName, String leagueName, List<Event> fullEvents);
}