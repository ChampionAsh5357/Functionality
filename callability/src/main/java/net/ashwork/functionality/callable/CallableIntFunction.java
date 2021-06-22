/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.callable;

import java.util.function.BiFunction;
import java.util.function.IntFunction;

/**
 * Represents a function that accepts an int-valued argument, produces a
 * result, and may throw an exception. This is the {@code int}-consuming
 * primitive specialization for {@link CallableFunction}.
 *
 * <p>This is a <a href="../../../../java/util/function/package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(int)}.
 *
 * @param <R> The type of the result of the callable function
 *
 * @see CallableFunction
 * @since 1.0.0
 */
@FunctionalInterface
public interface CallableIntFunction<R> {

    /**
     * Applies this callable function to the given argument,
     * or throws an exception if unable to do so.
     *
     * @param value The callable function argument
     * @return The callable function result
     * @throws Exception If unable to compute a result
     */
    R apply(int value) throws Exception;

    /**
     * Returns an int function that handles the exception thrown if this callable
     * function was unable to compute a result.
     *
     * @param onException The function to apply if an exception was thrown which
     *                    returns a result.
     * @return An int function which handles any exception thrown by this callable
     *         function
     * @see IntFunction
     */
    default IntFunction<R> handle(final BiFunction<Integer, Exception, R> onException) {
        return (final int value) -> {
            try {
                return this.apply(value);
            } catch (final Exception e) {
                return onException.apply(value, e);
            }
        };
    }

    /**
     * Returns an int function that swallows the exception thrown if this callable
     * function was unable to compute a result by returning null.
     *
     * @return An int function which swallows any exception thrown by this callable
     *         function by returning null
     * @see IntFunction
     */
    default IntFunction<R> swallow() { return this.handle((a, b) -> null); }
}
