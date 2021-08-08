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
import net.ashwork.functionality.primitive.booleans.BooleanFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts a {@code boolean}-valued argument and returns no result.
 * This is the one-arity specialization of {@link AbstractConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 * This is the {@code boolean}-consuming primitive specialization of {@link AbstractConsumer1}.
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
public interface AbstractBooleanConsumer1<B extends AbstractConsumer1<Boolean, B>, C extends AbstractBooleanConsumer1<B, C>> extends AbstractConsumerN<C>, UnboxedInput<AbstractConsumer1<Boolean, B>> {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     */
    void accept(final boolean value);

    @Override
    default void acceptAllUnchecked(final Object... args) { this.accept((boolean) args[0]); }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractConsumer1
     */
    @Override
    default AbstractConsumer1<Boolean, B> boxInput() {
        return this::accept;
    }

    @Override
    default AbstractBooleanConsumer1<B, C> andThen(final C after) {
        return (AbstractBooleanConsumer1<B, C>) AbstractConsumerN.super.andThen(after);
    }

    @Override
    default AbstractBooleanConsumer1<B, C> andThenUnchecked(final C after) {
        return (final boolean value) -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * @see BooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> BooleanFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (BooleanFunction1<V>) AbstractConsumerN.super.andThen(after);
    }

    /**
     * @see BooleanFunction1
     */
    @Override
    default <V> BooleanFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final boolean value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}
