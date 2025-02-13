package org.lamaspap.betshunter.model;

import java.util.List;

public record LeagueSummary(String name,
                            List<MatchSummary> matches) {
}
