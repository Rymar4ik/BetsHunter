package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;

public record Event(String id,
                    String name,
                    String nameDefault,
                    List<Competitor> competitors,
                    long kickoff,
                    long lastUpdated,
                    League league,
                    String betline,
                    boolean open,
                    String status,
                    boolean nativeMatch,
                    String widgetType,
                    boolean widgetVirtual,
                    String url,
                    String matchPhase,
                    boolean hasMarketWithZeroMargin,
                    List<Market> markets,
                    int marketsCount) {
}