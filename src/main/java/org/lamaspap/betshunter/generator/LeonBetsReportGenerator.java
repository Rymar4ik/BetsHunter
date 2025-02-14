package org.lamaspap.betshunter.generator;

import org.lamaspap.betshunter.model.LeagueSummary;
import org.lamaspap.betshunter.model.MarketSummary;
import org.lamaspap.betshunter.model.MatchSummary;
import org.lamaspap.betshunter.model.OutcomeSummary;
import org.lamaspap.betshunter.model.Report;
import org.lamaspap.betshunter.model.leonsbet.Event;
import org.lamaspap.betshunter.model.leonsbet.Market;
import org.lamaspap.betshunter.model.leonsbet.Runner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;


/**
 * The {@code LeonBetsReportGenerator} is responsible for creating reports summarizing sports events,
 * leagues, markets, and outcomes for the LeonBets platform. This implementation of {@link ReportGenerator}
 * processes event data into structured summaries designed for console output and other destinations.
 */
@Component
public class LeonBetsReportGenerator implements ReportGenerator {

    /**
     * Creates a report summarizing events for a specific sport and league.
     *
     * @param sportName  the name of the sport
     * @param leagueName the name of the league
     * @param fullEvents the list of full event data to be summarized
     * @return a {@link Report} containing the sport and league summaries
     */
    @Override
    public Report createReport(String sportName, String leagueName, List<Event> fullEvents) {
        var leagueSummary = new LeagueSummary(
                leagueName,
                fullEvents.stream()
                        .map(this::createMatchSummary)
                        .toList()
        );
        return new Report(sportName, leagueSummary);
    }

    /**
     * Creates a summary of a match for a given event, including its kickoff time and markets.
     *
     * @param event the event data to be summarized
     * @return a {@link MatchSummary} containing the details of the match
     */
    private MatchSummary createMatchSummary(Event event) {
        var kickoffTime = Instant.ofEpochMilli(event.kickoff());
        var markets = event.markets().stream()
                .map(this::createMarketSummary)
                .toList();
        return new MatchSummary(
                event.id(),
                event.name(),
                kickoffTime,
                markets
        );
    }

    /**
     * Creates a summary of a market, including its name and associated outcomes.
     *
     * @param market the market data to be summarized
     * @return a {@link MarketSummary} containing the market details
     */
    private MarketSummary createMarketSummary(Market market) {
        var outcomes = market.runners().stream()
                .map(this::createOutcomeSummary)
                .toList();
        return new MarketSummary(market.name(), outcomes);
    }

    /**
     * Creates a summary of an outcome for a given runner, including its ID, name, and price.
     *
     * @param runner the runner data to be summarized
     * @return an {@link OutcomeSummary} containing the outcome details
     */
    private OutcomeSummary createOutcomeSummary(Runner runner) {
        return new OutcomeSummary(
                runner.id(),
                runner.name(),
                runner.price()
        );
    }
}
