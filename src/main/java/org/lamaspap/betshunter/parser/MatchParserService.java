package org.lamaspap.betshunter.parser;

import org.lamaspap.betshunter.model.Report;

import java.util.List;

/**
 * Defines a service for parsing sports match data and generating reports.
 * Implementations of this interface are responsible for retrieving and processing
 * data related to sports matches and converting it into structured {@link Report} objects.
 */
public interface MatchParserService {

    /**
     * Parses match data to generate a list of reports.
     *
     * @return a list of {@link Report} objects containing parsed match details
     */
    List<Report> parse();
}
