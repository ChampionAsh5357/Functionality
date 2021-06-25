/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.callable.consumer;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents an operation that accepts a single input argument, returns
 * no result, and may throw an exception.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(Object)}.
 *
 * @param <T> The type of the input to the operation
 *
 * @since 1.1.0
 */
@FunctionalInterface
public interface ConsumerCallable<T> {

    /**
     * Performs this operation on the given argument,
     * or throws an exception if unable to do so.
     *
     * @param t The input argument
     * @throws Exception If unable to perform the operation
     */
    void accept(final T t) throws Exception;

    /**
     * Returns a consumer that handles the exception thrown if this
     * consumer was unable to perform the operation.
     *
     * @param onException The operation to perform if an exception was
     *                    thrown
     * @return A consumer which handles any exception thrown by this
     *         consumer
     * @see Consumer
     */
    default Consumer<T> handle(final ExceptionHandler<? super T> onException) {
        return (final T t) -> {
            try {
                this.accept(t);
            } catch (final Exception e) {
                onException.handle(t, e);
            }
        };
    }

    /**
     * Returns a consumer that swallows the exception thrown if this
     * consumer was unable to perform the operation.
     *
     * @return A consumer which swallows any exception thrown by this
     *         consumer
     * @see Consumer
     */
    default Consumer<T> swallow() {
        return this.handle((t, e) -> {});
    }

    /**
     * Returns a composed consumer that performs, in sequence, this
     * operation followed by the {@code after} operation.
     *
     * @param after The operation to perform after this operation
     * @return A composed consumer that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException If {@code after} is null
     */
    default ConsumerCallable<T> andThen(final ConsumerCallable<? super T> after) {
        Objects.requireNonNull(after, "The applied function cannot be null.");
        return (final T t) -> {
            this.accept(t);
            after.accept(t);
        };
    }

    /**
     * Represents a handler that takes in the outer callable's parameters and
     * the thrown exception and returns a result safely.
     *
     * @param <T> The type of the input to the outer callable
     */
    @FunctionalInterface
    interface ExceptionHandler<T> {

        /**
         * Handles an exception thrown by the outer callable and returns safely.
         * This should never throw an exception.
         *
         * @param t The callable argument
         * @param e The thrown exception
         */
        void handle(final T t, final Exception e);
    }
}
