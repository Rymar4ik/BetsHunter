package org.lamaspap.betshunter.model.leonsbet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public record EventsResponse(boolean enabled,
                             String betline,
                             int totalCount,
                             String vtag,
                             List<Event> events) {
}