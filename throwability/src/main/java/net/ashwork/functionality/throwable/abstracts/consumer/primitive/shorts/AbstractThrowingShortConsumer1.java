/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.consumer.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.abstracts.primitive.shorts.AbstractShortConsumer1;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.consumer.AbstractThrowingConsumer1;
import net.ashwork.functionality.throwable.abstracts.consumer.AbstractThrowingConsumerN;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingShortFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts a {@code short}-valued argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingConsumerN}.
 * This is the non-producing specialization of {@link AbstractThrowingFunction1}.
 * This is the {@code short}-consuming primitive specialization of {@link AbstractThrowingConsumer1}.
 * this is the throwing variation of {@link AbstractShortConsumer1}.
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
 * @see AbstractShortConsumer1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingShortConsumer1<H extends AbstractThrowingShortConsumer1.Handler, C extends AbstractThrowingShortConsumer1<H, C>> extends AbstractThrowingConsumerN<H, C>, UnboxedInput<AbstractThrowingConsumer1<Short, ?, ?>> {

    /**
     * Performs this operation on the given argument or throws a throwable.
     *
     * @param value the input argument
     */
    void accept(final short value) throws Throwable;

    @Override
    default void acceptAllUnchecked(final Object... args) throws Throwable {
        this.accept((short) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingConsumer1
     */
    @Override
    AbstractThrowingConsumer1<Short, ?, ?> boxInput();

    /**
     * @see AbstractShortConsumer1
     */
    @Override
    AbstractShortConsumer1<?> handle(final H handler);

    /**
     * @see AbstractShortConsumer1
     */
    @Override
    AbstractShortConsumer1<?> swallow();

    @Override
    default AbstractThrowingShortConsumer1<H, C> andThen(final C after) {
        return (AbstractThrowingShortConsumer1<H, C>) AbstractThrowingConsumerN.super.andThen(after);
    }

    @Override
    AbstractThrowingShortConsumer1<H, C> andThenUnchecked(final C after);

    /**
     * @see AbstractThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingShortFunction1<V, ?> andThen(final Function1<? super Void, ? extends V> after) {
        return (AbstractThrowingShortFunction1<V, ?>) AbstractThrowingConsumerN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingShortFunction1
     */
    @Override
    <V> AbstractThrowingShortFunction1<V, ?> andThenUnchecked(final Function1<? super Void, ? extends V> after);

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
        void acceptThrown(final Throwable t, final short value);

        @Override
        default void acceptThrownUnchecked(final Throwable t, final Object... args) {
            this.acceptThrown(t, (short) args[0]);
        }
    }
}
