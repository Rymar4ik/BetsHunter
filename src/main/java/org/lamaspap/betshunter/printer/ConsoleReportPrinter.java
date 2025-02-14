package org.lamaspap.betshunter.printer;

import org.lamaspap.betshunter.model.LeagueSummary;
import org.lamaspap.betshunter.model.MarketSummary;
import org.lamaspap.betshunter.model.MatchSummary;
import org.lamaspap.betshunter.model.OutcomeSummary;
import org.lamaspap.betshunter.model.Report;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * A {@code ConsoleReportPrinter} is an implementation of the {@link ReportPrinter} interface
 * that outputs reports to the console. It processes the details of the provided {@link Report}
 * and prints them in a hierarchical structure (sport, league, matches, markets, and outcomes).
 */
@Component
public class ConsoleReportPrinter implements ReportPrinter {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'")
            .withZone(ZoneOffset.UTC);

    /**
     * Prints the provided {@link Report} to the console in a structured format.
     *
     * @param report the report object containing sport, league, match, market, and outcome details
     */
    @Override
    public void print(Report report) {
        StringBuilder summaryBuilder = new StringBuilder();

        LeagueSummary league = report.leagueSummary();

        summaryBuilder.append(report.sportName())
                .append(", ").append(league.name())
                .append("\n");

        for (MatchSummary match : league.matches()) {
            summaryBuilder.append("\t")
                    .append(match.name())
                    .append(", ").append(DATE_FORMATTER.format(match.startDateTime()))
                    .append(", ").append(match.id())
                    .append("\n");

            for (MarketSummary market : match.markets()) {
                summaryBuilder.append("\t\t")
                        .append(market.name())
                        .append("\n");

                for (OutcomeSummary outcome : market.outcomes()) {
                    summaryBuilder.append("\t\t\t")
                            .append(outcome.name())
                            .append(", ").append(String.format("%.2f", outcome.coefficient()))
                            .append(", ").append(outcome.id())
                            .append("\n");
                }
            }
        }

        System.out.println(summaryBuilder);
    }
}
