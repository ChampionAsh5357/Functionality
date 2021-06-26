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
import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts two input arguments, returns
 * no result, and may throw an exception. This is the two-arity
 * specialization of {@link ConsumerCallable}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(Object, Object)}.
 *
 * @param <T> The type of the first argument to the operation
 * @param <U> The type of the second argument to the operation
 *
 * @see ConsumerCallable
 * @since 2.0.0
 */
@FunctionalInterface
public interface BiConsumerCallable<T, U> {

    /**
     * Creates a callable instance from a non-callable type.
     *
     * @param consumer The non-callable type
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @return A callable instance of the original type
     */
    static <T, U> BiConsumerCallable<T, U> from(final BiConsumer<T, U> consumer) { return consumer::accept; }

    /**
     * Performs this operation on the given arguments,
     * or throws an exception if unable to do so.
     *
     * @param t The first input argument
     * @param u The second input argument
     * @throws Exception If unable to perform the operation
     */
    void accept(final T t, final U u) throws Exception;

    /**
     * Returns a consumer that handles the exception thrown if this
     * consumer was unable to perform the operation.
     *
     * @param onException The operation to perform if an exception was
     *                    thrown
     * @return A consumer which handles any exception thrown by this
     *         consumer
     * @see BiConsumer
     */
    default BiConsumer<T, U> handle(final ExceptionHandler<? super T, ? super U> onException) {
        return (final T t, final U u) -> {
            try {
                this.accept(t, u);
            } catch (final Exception e) {
                onException.handle(t, u, e);
            }
        };
    }

    /**
     * Returns a consumer that swallows the exception thrown if this
     * consumer was unable to perform the operation.
     *
     * @return A consumer which swallows any exception thrown by this
     *         consumer
     * @see BiConsumer
     */
    default BiConsumer<T, U> swallow() {
        return this.handle((t, u, e) -> {});
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
    default BiConsumerCallable<T, U> andThen(final BiConsumerCallable<? super T, ? super U> after) {
        Objects.requireNonNull(after, "The applied function cannot be null.");
        return (final T t, final U u) -> {
            this.accept(t, u);
            after.accept(t, u);
        };
    }

    /**
     * Represents a handler that takes in the outer callable's parameters and
     * the thrown exception and returns a result safely.
     *
     * @param <T> The type of the first argument to the outer callable
     * @param <U> The type of the second argument to the outer callable
     */
    @FunctionalInterface
    interface ExceptionHandler<T, U> {

        /**
         * Handles an exception thrown by the outer callable and returns safely.
         * This should never throw an exception.
         *
         * @param t The first callable argument
         * @param u The second callable argument
         * @param e The thrown exception
         */
        void handle(final T t, final U u, final Exception e);
    }
}
