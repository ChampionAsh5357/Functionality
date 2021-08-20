/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.abstracts.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.abstracts.AbstractConsumer1;
import net.ashwork.functionality.consumer.abstracts.AbstractConsumerN;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.primitive.shorts.ShortFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts a {@code short}-valued argument and returns no result.
 * This is the one-arity specialization of {@link AbstractConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 * This is the {@code short}-consuming primitive specialization of {@link AbstractConsumer1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <B> the type of the boxed consumer
 * @param <C> the type of this consumer
 *
 * @see AbstractConsumerN
 * @see Function1
 * @see AbstractConsumer1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractShortConsumer1<B extends AbstractConsumer1<Short, B>, C extends AbstractShortConsumer1<B, C>> extends AbstractConsumerN<C>, UnboxedInput<AbstractConsumer1<Short, B>> {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     */
    void accept(final short value);

    @Override
    default void acceptAllUnchecked(final Object... args) { this.accept((short) args[0]); }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractConsumer1
     */
    @Override
    default AbstractConsumer1<Short, B> boxInput() {
        return this::accept;
    }

    @Override
    default AbstractShortConsumer1<B, C> andThen(final C after) {
        return (AbstractShortConsumer1<B, C>) AbstractConsumerN.super.andThen(after);
    }

    @Override
    default AbstractShortConsumer1<B, C> andThenUnchecked(final C after) {
        return (final short value) -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * @see ShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ShortFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ShortFunction1<V>) AbstractConsumerN.super.andThen(after);
    }

    /**
     * @see ShortFunction1
     */
    @Override
    default <V> ShortFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final short value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}
