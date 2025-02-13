package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;

public record Region(String id,
                     String name,
                     String nameDefault,
                     String family,
                     String url,
                     List<League> leagues) {
}