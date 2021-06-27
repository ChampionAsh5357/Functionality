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
import java.util.function.Supplier;

/**
 * An extension interface to provide library features for {@link Callable}.
 *
 * <p>This is a functional interface whose functional method is {@link #call()}.
 *
 * @param <V> The type of the result of the callable
 *
 * @see Callable
 * @since 2.1.0
 */
@FunctionalInterface
public interface CallabilityCallable<V> extends Callable<V> {

    /**
     * Creates a callable instance from a non-callable type.
     *
     * @param supplier The non-callable type
     * @param <V> The type of the result
     * @return A callable instance of the original type
     */
    static <V> CallabilityCallable<V> from(final Supplier<V> supplier) { return supplier::get; }

    /**
     * Wraps a callable instance to provide library features for {@link Callable}.
     *
     * @param callable The callable instance to be wrapped
     * @param <V> The type of the result of the callable
     * @return The wrapped callable as {@link CallabilityCallable}
     */
    static <V> CallabilityCallable<V> wrap(final Callable<V> callable) {
        return callable::call;
    }

    /**
     * Returns a supplier that handles the exception thrown if this
     * callable was unable to compute a result.
     *
     * @param onException The function to apply if an exception was
     *                    thrown which returns a result
     * @return A supplier which handles any exception thrown by this
     *         callable
     * @see Supplier
     */
    default Supplier<V> handle(final ExceptionHandler<? extends V> onException) {
        return () -> {
            try {
                return this.call();
            } catch (final Exception e) {
                return onException.handle(e);
            }
        };
    }

    /**
     * Returns a supplier that swallows the exception thrown if this
     * callable was unable to compute a result by returning null.
     *
     * @return A supplier which swallows any exception thrown by this
     *         callable by returning null
     * @see Supplier
     */
    default Supplier<V> swallow() { return this.handle(e -> null); }

    /**
     * Represents a handler that takes in the outer callable's parameters and
     * the thrown exception and returns a result safely.
     *
     * @param <V> The type of the result of the callable
     */
    @FunctionalInterface
    interface ExceptionHandler<V> {

        /**
         * Handles an exception thrown by the outer callable and returns safely.
         * This should never throw an exception.
         *
         * @param e The thrown exception
         * @return The handled callable result
         */
        V handle(final Exception e);
    }
}
