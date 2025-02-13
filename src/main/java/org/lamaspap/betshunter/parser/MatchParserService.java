package org.lamaspap.betshunter.parser;

import org.lamaspap.betshunter.model.Report;

import java.util.List;

public interface MatchParserService {
    List<Report> parse();
}
