package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;

public record Sport(String id,
                    String name,
                    int weight,
                    String family,
                    List<Region> regions) {
}
