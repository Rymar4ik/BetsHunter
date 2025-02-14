package org.lamaspap.betshunter.model.leonsbet;

/**
 * Represents an outcome for a market in a sports event.
 *
 * @param id    the unique identifier of the runner(outcome)
 * @param name  the name of the runner(outcome)
 * @param price the coefficient associated with the market outcome
 */
public record Runner(String id,
                     String name,
                     double price) {
}