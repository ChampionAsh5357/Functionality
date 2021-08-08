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
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.util.InheritOnly;

import java.util.function.Consumer;

/**
 * Represents an operation that accepts one argument and returns no result.
 * This is the one-arity specialization of {@link AbstractConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the input to the operation
 * @param <C> the type of this consumer
 *
 * @see AbstractConsumerN
 * @see Function1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractConsumer1<T1, C extends AbstractConsumer1<T1, C>> extends AbstractConsumerN<C>, Variant<Consumer<T1>> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t1 the input argument
     */
    void accept(final T1 t1);

    @SuppressWarnings("unchecked")
    @Override
    default void acceptAllUnchecked(final Object... args) {
        this.accept((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Consumer
     */
    @Override
    default Consumer<T1> toVariant() {
        return this::accept;
    }

    @Override
    default AbstractConsumer1<T1, C> andThen(final C after) {
        return (AbstractConsumer1<T1, C>) AbstractConsumerN.super.andThen(after);
    }

    @Override
    default AbstractConsumer1<T1, C> andThenUnchecked(final C after) {
        return (final T1 t1) -> {
            this.accept(t1);
            after.accept(t1);
        };
    }

    /**
     * @see Function1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<T1, V> andThen(final Function1<? super Void, ? extends V> after) {
        return (Function1<T1, V>) AbstractConsumerN.super.andThen(after);
    }

    /**
     * @see Function1
     */
    @Override
    default <V> Function1<T1, V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final T1 t1) -> {
            this.accept(t1);
            return after.apply(null);
        };
    }
}
