package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;
import java.util.Map;

public record Market(String id,
                     String typeTag,
                     String name,
                     long marketTypeId,
                     boolean open,
                     boolean hasZeroMargin,
                     boolean primary,
                     Integer cols,
                     List<Runner> runners,
                     Map<String, String> specifiers,
                     List<String> selectionTypes) {
}