package org.lamaspap.betshunter.generator;

import org.lamaspap.betshunter.model.Report;
import org.lamaspap.betshunter.model.leonsbet.Event;

import java.util.List;


public interface ReportGenerator {
    Report createReport(String sportName, String leagueName, List<Event> fullEvents);
}