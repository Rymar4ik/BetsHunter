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

@Component
public class LeonBetsReportGenerator implements ReportGenerator {

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

    private MarketSummary createMarketSummary(Market market) {
        var outcomes = market.runners().stream()
                .map(this::createOutcomeSummary)
                .toList();
        return new MarketSummary(market.name(), outcomes);
    }

    private OutcomeSummary createOutcomeSummary(Runner runner) {
        return new OutcomeSummary(
                runner.id(),
                runner.name(),
                runner.price()
        );
    }
}
