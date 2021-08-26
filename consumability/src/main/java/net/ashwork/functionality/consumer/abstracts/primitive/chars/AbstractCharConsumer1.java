/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.abstracts.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.abstracts.AbstractConsumer1;
import net.ashwork.functionality.consumer.abstracts.AbstractConsumerN;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.primitive.chars.CharFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts a {@code char}-valued argument and returns no result.
 * This is the one-arity specialization of {@link AbstractConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 * This is the {@code char}-consuming primitive specialization of {@link AbstractConsumer1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <C> the type of this consumer
 *
 * @see AbstractConsumerN
 * @see Function1
 * @see AbstractConsumer1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractCharConsumer1<C extends AbstractCharConsumer1<C>> extends AbstractConsumerN<C>, UnboxedInput<AbstractConsumer1<Character, ?>> {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     */
    void accept(final char value);

    @Override
    default void acceptAllUnchecked(final Object... args) { this.accept((char) args[0]); }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractConsumer1
     */
    @Override
    AbstractConsumer1<Character, ?> boxInput();

    @Override
    default AbstractCharConsumer1<C> andThen(final C after) {
        return (AbstractCharConsumer1<C>) AbstractConsumerN.super.andThen(after);
    }

    @Override
    AbstractCharConsumer1<C> andThenUnchecked(final C after);

    /**
     * @see CharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> CharFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (CharFunction1<V>) AbstractConsumerN.super.andThen(after);
    }

    /**
     * @see CharFunction1
     */
    @Override
    default <V> CharFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final char value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}
