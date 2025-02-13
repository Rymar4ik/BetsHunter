package org.lamaspap.betshunter.model;

import java.util.List;

public record MarketSummary(String name,
                            List<OutcomeSummary> outcomes) {
}
