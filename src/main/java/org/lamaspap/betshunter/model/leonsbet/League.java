package org.lamaspap.betshunter.model.leonsbet;

public record League(String id,
                     String name,
                     String nameDefault,
                     String url,
                     int weight,
                     int prematch,
                     int inplay,
                     int outright,
                     boolean top,
                     int topOrder,
                     boolean hasZeroMarginEvents,
                     String logoUrl,
                     String logoSource,
                     Region region,
                     Sport sport) {
}