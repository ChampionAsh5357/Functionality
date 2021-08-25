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
import net.ashwork.functionality.consumer.abstracts.AbstractConsumer2;
import net.ashwork.functionality.consumer.abstracts.AbstractConsumerN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts two arguments and returns no result or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingConsumerN}.
 * This is the non-producing specialization of {@link AbstractThrowingFunction2}.
 * This is the throwing variation of {@link AbstractConsumer2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the first argument to the operation
 * @param <T2> the type of the second argument to the operation
 * @param <H> the type of the handler to safely call the consumer
 * @param <C> the type of this consumer
 *
 * @see AbstractThrowingConsumerN
 * @see AbstractThrowingFunction2
 * @see AbstractConsumer2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingConsumer2<T1, T2, H extends AbstractThrowingConsumer2.Handler<T1, T2>, C extends AbstractThrowingConsumer2<T1, T2, H, C>> extends AbstractThrowingConsumerN<H, C> {

    /**
     * Performs this operation on the given arguments or throws a throwable.
     *
     * @param t1 the first input argument
     * @param t2 the second input argument
     */
    void accept(final T1 t1, final T2 t2) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default void acceptAllUnchecked(final Object... args) throws Throwable {
        this.accept((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractConsumer2
     */
    @SuppressWarnings("unchecked")
    @Override
    default AbstractConsumer2<T1, T2, ?> handle(final H handler) {
        return (AbstractConsumer2<T1, T2, ?>) AbstractThrowingConsumerN.super.handle(handler);
    }

    /**
     * @see AbstractConsumer2
     */
    @Override
    AbstractConsumer2<T1, T2, ?> swallow();

    @Override
    default AbstractThrowingConsumer2<T1, T2, H, C> andThen(final C after) {
        return (AbstractThrowingConsumer2<T1, T2, H, C>) AbstractThrowingConsumerN.super.andThen(after);
    }

    @Override
    AbstractThrowingConsumer2<T1, T2, H, C> andThenUnchecked(final C after);

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<T1, T2, V, ?> andThen(final Function1<? super Void, ? extends V> after) {
        return (AbstractThrowingFunction2<T1, T2, V, ?>) AbstractThrowingConsumerN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<T1, T2, V, ?> andThenUnchecked(final Function1<? super Void, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and operates safely.
     *
     * @param <T1> the type of the first argument to the operation
     * @param <T2> the type of the second argument to the operation
     */
    @FunctionalInterface
    interface Handler<T1, T2> extends AbstractThrowingConsumerN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and operates safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the first input argument
         * @param t2 the second input argument
         */
        void acceptThrown(final Throwable t, final T1 t1, final T2 t2);

        @SuppressWarnings("unchecked")
        @Override
        default void acceptThrownUnchecked(final Throwable t, final Object... args) {
            this.acceptThrown(t, (T1) args[0], (T2) args[1]);
        }
    }
}
