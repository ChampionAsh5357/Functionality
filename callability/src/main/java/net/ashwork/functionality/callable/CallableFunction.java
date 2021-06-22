/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.callable;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Represents a function that accepts one argument, produces a result,
 * and may throw an exception.
 *
 * <p>This is a <a href="../../../../java/util/function/package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object)}.
 *
 * @param <T> The type of the input to the callable function
 * @param <R> The type of the result of the callable function
 *
 * @since 1.0.0
 */
@FunctionalInterface
public interface CallableFunction<T, R> {

    /**
     * Applies this callable function to the given argument,
     * or throws an exception if unable to do so.
     *
     * @param t The callable function argument
     * @return The callable function result
     * @throws Exception If unable to compute a result
     */
    R apply(final T t) throws Exception;

    /**
     * Returns a function that handles the exception thrown if this callable
     * function was unable to compute a result.
     *
     * @param onException The function to apply if an exception was thrown which
     *                    returns a result.
     * @return A function which handles any exception thrown by this callable
     *         function
     * @see Function
     */
    default Function<T, R> handle(final BiFunction<T, Exception, R> onException) {
        return (final T t) -> {
            try {
                return this.apply(t);
            } catch (final Exception e) {
                return onException.apply(t, e);
            }
        };
    }

    /**
     * Returns a function that swallows the exception thrown if this callable
     * function was unable to compute a result by returning null.
     *
     * @return A function which swallows any exception thrown by this callable
     *         function by returning null
     * @see Function
     */
    default Function<T, R> swallow() {
        return this.handle((a, b) -> null);
    }

    /**
     * Returns a composed callable function that first applies the {@code before}
     * callable function to its input, and then applies this callable function to
     * the result.
     *
     * @param before The callable function to apply before this callable function is applied
     * @param <V> The input type of the {@code before} callable function, and of the
     *            composed callable function
     * @return A composed callable function that first applies the {@code before}
     *         callable function and then applies this callable function.
     * @throws NullPointerException If {@code before} is null
     */
    default <V> CallableFunction<V, R> compose(final CallableFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before, "The composed function cannot be null.");
        return (final V v) -> this.apply(before.apply(v));
    }

    /**
     * Returns a composed callable function that first applies this callable
     * function to its input, and then applies the {@code after} callable function
     * to the result.
     *
     * @param after The callable function to apply after this callable function is applied
     * @param <V> The output type of the {@code after} callable function, and of the
     *            composed callable function
     * @return A composed callable function that first applies this callable function
     *         and then applies the {@code after} callable function
     * @throws NullPointerException If {@code after} is null
     */
    default <V> CallableFunction<T, V> andThen(final CallableFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after, "The applied function cannot be null.");
        return (final T t) -> after.apply(this.apply(t));
    }

    /**
     * Returns a callable function that always returns its input argument.
     *
     * @param <T> The type of the input and output objects to the callable function
     * @return A callable function that always returns its input argument
     */
    static <T> CallableFunction<T, T> identity() { return t -> t; }
}
