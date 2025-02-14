package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;

public record Market(String name,
                     List<Runner> runners) {
}