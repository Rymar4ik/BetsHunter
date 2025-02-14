package org.lamaspap.betshunter.parser;

import org.lamaspap.betshunter.config.properties.LeonBetsParserProperties;
import org.lamaspap.betshunter.generator.LeonBetsReportGenerator;
import org.lamaspap.betshunter.generator.ReportGenerator;
import org.lamaspap.betshunter.model.Report;
import org.lamaspap.betshunter.model.leonsbet.League;
import org.lamaspap.betshunter.model.leonsbet.Event;
import org.lamaspap.betshunter.model.leonsbet.Sport;
import org.lamaspap.betshunter.service.LeonBetsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


/**
 * The {@code LeonBetsMatchParser} class is responsible for parsing and generating reports
 * from sports and events obtained from the LeonBets platform. It implements the
 * {@link MatchParserService} interface and processes data asynchronously to improve performance.
 */
@Component
public class LeonBetsMatchParser implements MatchParserService {
    private static final Logger LOG = LoggerFactory.getLogger(LeonBetsMatchParser.class);

    private final LeonBetsService leonBetsService;
    private final ReportGenerator reportGenerator;
    private final List<String> sports;
    private final int eventsLimitThreshold;

    /**
     * Constructs a new instance of {@code LeonBetsMatchParser}.
     *
     * @param leonBetsService the service for interacting with the LeonBets API
     * @param properties      the configuration properties for LeonBets parsing, including sports and event thresholds
     * @param reportGenerator the generator for creating reports based on parsed data
     */
    public LeonBetsMatchParser(LeonBetsService leonBetsService,
                               LeonBetsParserProperties properties,
                               LeonBetsReportGenerator reportGenerator) {

        this.leonBetsService = leonBetsService;
        this.reportGenerator = reportGenerator;

        this.sports = properties.sports();
        this.eventsLimitThreshold = properties.eventsLimitThreshold();
    }

    /**
     * Parses sports and events data from the LeonBets platform and generates reports for top leagues
     * and their corresponding events. The method processes the data asynchronously to improve efficiency.
     *
     * @return a list of {@link Report} objects summarizing the data for specified sports and leagues
     */
    @Override
    public List<Report> parse() {
        var topLeaguesBySport = getTopLeaguesBySport();
        var reports = Collections.synchronizedList(new ArrayList<Report>());

        try (ExecutorService executor = Executors.newFixedThreadPool(3, Thread.ofVirtual().factory())) {

            topLeaguesBySport.forEach((key, value) -> {
                List<CompletableFuture<Void>> futures = value.stream()
                        .map(league -> CompletableFuture.runAsync(() -> {
                            List<Event> leagueEvents = getLeagueEventsLimited(league.id());

                            List<Event> fullEvents = leagueEvents.stream()
                                    .map(event -> getFullEventData(event.id()))
                                    .toList();

                            reports.add(reportGenerator.createReport(key, league.name(), fullEvents));

                        }, executor))
                        .toList();

                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            });
        }

        return reports;
    }

    /**
     * Retrieves and organizes the top leagues by sport based on predefined criteria.
     *
     * @return a {@link Map} where the keys are sport names and the values are lists of top {@link League} objects
     */
    private Map<String, List<League>> getTopLeaguesBySport() {
        List<Sport> sports = leonBetsService.getAllSports();

        return sports.stream()
                .filter(sport -> this.sports.contains(sport.name()))
                .collect(Collectors.toMap(
                        Sport::name,
                        sport -> sport.regions().stream()
                                .flatMap(region -> region.leagues().stream())
                                .filter(League::top)
                                .toList()
                ));
    }

    /**
     * Retrieves the events for the specified league, limited by the predefined threshold.
     *
     * @param leagueId the unique identifier of the league
     * @return a list of {@link Event} objects for the specified league
     */
    private List<Event> getLeagueEventsLimited(String leagueId) {
        return leonBetsService.getLeagueEvents(leagueId, eventsLimitThreshold);
    }

    /**
     * Retrieves detailed data for a specific event.
     *
     * @param eventId the unique identifier of the event
     * @return the {@link Event} object containing full details about the event
     */
    private Event getFullEventData(String eventId) {
        return leonBetsService.getFullEventData(eventId);
    }
}
