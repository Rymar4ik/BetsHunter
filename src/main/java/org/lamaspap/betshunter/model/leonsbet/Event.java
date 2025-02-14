package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;

public record Event(String id,
                    String name,
                    long kickoff,
                    List<Market> markets) {
}