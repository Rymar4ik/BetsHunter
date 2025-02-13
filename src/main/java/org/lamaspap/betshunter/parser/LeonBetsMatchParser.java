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

@Component
public class LeonBetsMatchParser implements MatchParserService {
    private static final Logger LOG = LoggerFactory.getLogger(LeonBetsMatchParser.class);

    private final LeonBetsService leonBetsService;
    private final ReportGenerator reportGenerator;
    private final List<String> sports;
    private final int eventsLimitThreshold;

    public LeonBetsMatchParser(LeonBetsService leonBetsService,
                               LeonBetsParserProperties properties,
                               LeonBetsReportGenerator reportGenerator) {

        this.leonBetsService = leonBetsService;
        this.reportGenerator = reportGenerator;

        this.sports = properties.sports();
        this.eventsLimitThreshold = properties.eventsLimitThreshold();
    }

    @Override
    public List<Report> parse() {
        var topLeaguesBySport = getTopLeaguesBySport();
        var reports = Collections.synchronizedList(new ArrayList<Report>());

        try (ExecutorService executor = Executors.newFixedThreadPool(3, Thread.ofVirtual().factory())) {

            topLeaguesBySport.forEach((key, value) -> {
                List<CompletableFuture<Void>> futures = value.stream()
                        .map(league -> CompletableFuture.runAsync(() -> {
                            List<Event> leagueEvents = getLeagueEventsLimited(league.id());

                            leagueEvents.forEach(event -> {
                                Event fullEventData = getFullEventData(event.id());
                                reports.add(reportGenerator.createReport(fullEventData));
                            });

                        }, executor))
                        .toList();

                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            });
        }

        return reports;
    }

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

    private List<Event> getLeagueEventsLimited(String leagueId) {
        return leonBetsService.getLeagueEvents(leagueId, eventsLimitThreshold);
    }

    private Event getFullEventData(String eventId) {
        return leonBetsService.getFullEventData(eventId);
    }
}
