package org.lamaspap.betshunter.generator;

import org.lamaspap.betshunter.model.Report;
import org.lamaspap.betshunter.model.leonsbet.Event;

public interface ReportGenerator {
    Report createReport(Event fullEventData);
}