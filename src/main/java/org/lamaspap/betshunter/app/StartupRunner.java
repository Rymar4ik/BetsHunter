package org.lamaspap.betshunter.app;

import org.lamaspap.betshunter.model.Report;
import org.lamaspap.betshunter.printer.ReportPrinter;
import org.lamaspap.betshunter.parser.LeonBetsMatchParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupRunner implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(StartupRunner.class);

    private final LeonBetsMatchParser matchParserService;
    private final ReportPrinter reportPrinter;

    public StartupRunner(LeonBetsMatchParser matchParserService,
                         ReportPrinter reportPrinter) {

        this.matchParserService = matchParserService;
        this.reportPrinter = reportPrinter;
    }

    @Override
    public void run(ApplicationArguments args) {
        LOG.info("Application Started!");

        List<Report> reports = matchParserService.parse();

        LOG.info("{} reports created!", reports.size());

        reports.forEach(reportPrinter::print);
    }
}