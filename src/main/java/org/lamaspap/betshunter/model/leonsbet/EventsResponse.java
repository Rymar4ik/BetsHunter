package org.lamaspap.betshunter.model.leonsbet;

import java.util.List;


/**
 * Represents a response containing a list of events.
 * This record is used to encapsulate the events returned by the API
 * or other service calls in a structured format.
 *
 * @param events the list of {@link Event} objects present in the response
 */
public record EventsResponse(List<Event> events) {
}