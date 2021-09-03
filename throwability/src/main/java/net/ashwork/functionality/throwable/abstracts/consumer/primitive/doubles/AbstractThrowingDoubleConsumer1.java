/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.consumer.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.abstracts.primitive.doubles.AbstractDoubleConsumer1;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.consumer.AbstractThrowingConsumer1;
import net.ashwork.functionality.throwable.abstracts.consumer.AbstractThrowingConsumerN;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingDoubleFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts a {@code double}-valued argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingConsumerN}.
 * This is the non-producing specialization of {@link AbstractThrowingFunction1}.
 * This is the {@code double}-consuming primitive specialization of {@link AbstractThrowingConsumer1}.
 * this is the throwing variation of {@link AbstractDoubleConsumer1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the consumer
 * @param <C> the type of this consumer
 *
 * @see AbstractThrowingConsumerN
 * @see AbstractThrowingFunction1
 * @see AbstractThrowingConsumer1
 * @see AbstractDoubleConsumer1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingDoubleConsumer1<H extends AbstractThrowingDoubleConsumer1.Handler, C extends AbstractThrowingDoubleConsumer1<H, C>> extends AbstractThrowingConsumerN<H, C>, UnboxedInput<AbstractThrowingConsumer1<Double, ?, ?>> {

    /**
     * Performs this operation on the given argument or throws a throwable.
     *
     * @param value the input argument
     * @throws Throwable if the operation cannot be performed
     */
    void accept(final double value) throws Throwable;

    @Override
    default void acceptAllUnchecked(final Object... args) throws Throwable {
        this.accept((double) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingConsumer1
     */
    @Override
    AbstractThrowingConsumer1<Double, ?, ?> boxInput();

    /**
     * @see AbstractDoubleConsumer1
     */
    @Override
    AbstractDoubleConsumer1<?> handle(final H handler);

    /**
     * @see AbstractDoubleConsumer1
     */
    @Override
    AbstractDoubleConsumer1<?> swallow();

    @Override
    default AbstractThrowingDoubleConsumer1<H, C> andThen(final C after) {
        return (AbstractThrowingDoubleConsumer1<H, C>) AbstractThrowingConsumerN.super.andThen(after);
    }

    @Override
    AbstractThrowingDoubleConsumer1<H, C> andThenUnchecked(final C after);

    /**
     * @see AbstractThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingDoubleFunction1<V, ?> andThen(final Function1<? super Void, ? extends V> after) {
        return (AbstractThrowingDoubleFunction1<V, ?>) AbstractThrowingConsumerN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingDoubleFunction1
     */
    @Override
    <V> AbstractThrowingDoubleFunction1<V, ?> andThenUnchecked(final Function1<? super Void, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and operates safely.
     */
    interface Handler extends AbstractThrowingConsumerN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and operates safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the input argument
         */
        void acceptThrown(final Throwable t, final double value);

        @Override
        default void acceptThrownUnchecked(final Throwable t, final Object... args) {
            this.acceptThrown(t, (double) args[0]);
        }
    }
}
