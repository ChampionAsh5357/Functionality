/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.consumer;

import net.ashwork.functionality.consumer.abstracts.AbstractConsumer1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts one argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingConsumerN}.
 * This is the non-producing specialization of {@link AbstractThrowingFunction1}.
 * This is the throwing variation of {@link AbstractConsumer1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the input to the operation
 * @param <H> the type of the handler to safely call the consumer
 * @param <C> the type of this consumer
 *
 * @see AbstractThrowingConsumerN
 * @see AbstractThrowingFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingConsumer1<T1, H extends AbstractThrowingConsumer1.Handler<T1>, C extends AbstractThrowingConsumer1<T1, H, C>> extends AbstractThrowingConsumerN<H, C> {

    /**
     * Performs this operation on the given argument or throws a throwable.
     *
     * @param t1 the input argument
     */
    void accept(final T1 t1) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default void acceptAllUnchecked(final Object... args) throws Throwable {
        this.accept((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    @Override
    default AbstractThrowingConsumer1<T1, H, C> andThen(final C after) {
        return (AbstractThrowingConsumer1<T1, H, C>) AbstractThrowingConsumerN.super.andThen(after);
    }

    @Override
    AbstractThrowingConsumer1<T1, H, C> andThenUnchecked(final C after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and operates safely.
     *
     * @param <T1> the type of the input to the operation
     */
    @FunctionalInterface
    interface Handler<T1> extends AbstractThrowingConsumerN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and operates safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the input argument
         */
        void acceptThrown(final Throwable t, final T1 t1);

        @SuppressWarnings("unchecked")
        @Override
        default void acceptThrownUnchecked(final Throwable t, final Object... args) {
            this.acceptThrown(t, (T1) args[0]);
        }
    }
}
