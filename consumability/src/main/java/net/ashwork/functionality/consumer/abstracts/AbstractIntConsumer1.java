/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.abstracts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.primitive.ints.IntFunction1;
import net.ashwork.functionality.util.InheritOnly;

import java.util.function.IntConsumer;

/**
 * Represents an operation that accepts an {@code int}-valued argument and returns no result.
 * This is the one-arity specialization of {@link AbstractConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 * This is the {@code int}-consuming primitive specialization of {@link AbstractConsumer1}.
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
public interface AbstractIntConsumer1<B extends AbstractConsumer1<Integer, B>, C extends AbstractIntConsumer1<B, C>> extends AbstractConsumerN<C>, UnboxedInput<AbstractConsumer1<Integer, B>>, Variant<IntConsumer> {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     */
    void accept(final int value);

    @Override
    default void acceptAllUnchecked(final Object... args) { this.accept((int) args[0]); }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see IntConsumer
     */
    @Override
    default IntConsumer toVariant() {
        return this::accept;
    }

    /**
     * @see AbstractConsumer1
     */
    @Override
    default AbstractConsumer1<Integer, B> boxInput() {
        return this::accept;
    }

    @Override
    default AbstractIntConsumer1<B, C> andThen(final C after) {
        return (AbstractIntConsumer1<B, C>) AbstractConsumerN.super.andThen(after);
    }

    @Override
    default AbstractIntConsumer1<B, C> andThenUnchecked(final C after) {
        return (final int value) -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * @see IntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> IntFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (IntFunction1<V>) AbstractConsumerN.super.andThen(after);
    }

    /**
     * @see IntFunction1
     */
    @Override
    default <V> IntFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final int value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}