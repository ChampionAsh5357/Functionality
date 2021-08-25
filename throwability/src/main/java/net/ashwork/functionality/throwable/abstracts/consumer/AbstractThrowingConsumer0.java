/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.consumer;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.abstracts.AbstractConsumer0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts no arguments and returns no result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingConsumerN}.
 * This is the non-producing specialization of {@link AbstractThrowingFunction0}.
 * This is the throwing variation of {@link AbstractConsumer0}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the consumer
 * @param <C> the type of this consumer
 *
 * @see AbstractThrowingConsumerN
 * @see AbstractThrowingFunction0
 * @see AbstractConsumer0
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingConsumer0<H extends AbstractThrowingConsumer0.Handler, C extends AbstractThrowingConsumer0<H, C>> extends AbstractThrowingConsumerN<H, C> {

    /**
     * Performs this operation or throws a throwable.
     */
    void accept() throws Throwable;

    @Override
    default void acceptAllUnchecked(final Object... args) throws Throwable {
        this.accept();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see AbstractConsumer0
     */
    @Override
    default AbstractConsumer0<?> handle(final H handler) {
        return (AbstractConsumer0<?>) AbstractThrowingConsumerN.super.handle(handler);
    }

    /**
     * @see AbstractConsumer0
     */
    @Override
    AbstractConsumer0<?> swallow();

    @Override
    default AbstractThrowingConsumer0<H, C> andThen(final C after) {
        return (AbstractThrowingConsumer0<H, C>) AbstractThrowingConsumerN.super.andThen(after);
    }

    @Override
    AbstractThrowingConsumer0<H, C> andThenUnchecked(final C after);

    /**
     * @see AbstractThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction0<V, ?> andThen(final Function1<? super Void, ? extends V> after) {
        return (AbstractThrowingFunction0<V, ?>) AbstractThrowingConsumerN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction0
     */
    @Override
    <V> AbstractThrowingFunction0<V, ?> andThenUnchecked(final Function1<? super Void, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and operates safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingConsumerN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and operates safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         */
        void acceptThrown(final Throwable t);

        @Override
        default void acceptThrownUnchecked(final Throwable t, final Object... args) {
            this.acceptThrown(t);
        }
    }
}
