package org.lamaspap.betshunter.printer;

import org.lamaspap.betshunter.model.Report;

/**
 * A contract for implementing various report printing mechanisms.
 * Implementations of this interface define how the {@link Report} is printed.
 */
public interface ReportPrinter {

    /**
     * Prints the provided {@link Report} object using the implementation's defined mechanism.
     *
     * @param events the {@link Report} object containing data to be printed
     */
    void print(Report events);
}