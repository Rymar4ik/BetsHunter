package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;

public record Runner(String id,
                     String name,
                     boolean open,
                     int r,
                     int c,
                     List<String> tags,
                     double price,
                     String priceStr,
                     String handicap) {
}