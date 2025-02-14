package org.lamaspap.betshunter.model;

/**
 * Represents an outcome summary, which includes an ID, name, and coefficient.
 *
 * @param id          the unique identifier of the outcome
 * @param name        the name of the outcome
 * @param coefficient the coefficient associated with the outcome
 */
public record OutcomeSummary(String id,
                             String name,
                             double coefficient) {
}
