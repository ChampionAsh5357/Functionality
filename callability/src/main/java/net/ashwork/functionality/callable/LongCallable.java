/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.callable;

import java.util.concurrent.Callable;
import java.util.function.LongSupplier;

/**
 * Represents task that returns {@code long}-valued results and may
 * throw an exception. This is the {@code long}-producing primitive
 * specialization of {@link Callable}.
 *
 * <p>This is a functional interface whose functional method is {@link #callAsLong()}.
 *
 * @see Callable
 * @since 2.1.0
 */
@FunctionalInterface
public interface LongCallable {

    /**
     * Creates a callable instance from a non-callable type.
     *
     * @param supplier The non-callable type
     * @return A callable instance of the original type
     */
    static LongCallable from(final LongSupplier supplier) { return supplier::getAsLong; }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return The computed result
     * @throws Exception If unable to compute a result
     */
    long callAsLong() throws Exception;

    /**
     * Returns a supplier that handles the exception thrown if this
     * callable was unable to compute a result.
     *
     * @param onException The function to apply if an exception was
     *                    thrown which returns a result
     * @return A supplier which handles any exception thrown by this
     *         callable
     * @see LongSupplier
     */
    default LongSupplier handle(final ExceptionHandler onException) {
        return () -> {
            try {
                return this.callAsLong();
            } catch (final Exception e) {
                return onException.handle(e);
            }
        };
    }

    /**
     * Returns a supplier that swallows the exception thrown if this
     * callable was unable to compute a result by returning {@code 0L}.
     *
     * @return A supplier which swallows any exception thrown by this
     *         callable by returning {@code 0L}
     * @see LongSupplier
     */
    default LongSupplier swallow() {
        return this.handle(e -> 0L);
    }

    /**
     * Represents a handler that takes in the outer callable's parameters and
     * the thrown exception and returns a result safely.
     */
    @FunctionalInterface
    interface ExceptionHandler {

        /**
         * Handles an exception thrown by the outer callable and returns safely.
         * This should never throw an exception.
         *
         * @param e The thrown exception
         * @return The handled callable result
         */
        long handle(final Exception e);
    }
}