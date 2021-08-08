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
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.util.InheritOnly;

import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts two arguments and returns no result.
 * This is the two-arity specialization of {@link AbstractConsumerN}.
 * This is the non-producing specialization of {@link Function2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the first argument to the operation
 * @param <T2> the type of the second argument to the operation
 * @param <C> the type of this consumer
 *
 * @see AbstractConsumerN
 * @see Function2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractConsumer2<T1, T2, C extends AbstractConsumer2<T1, T2, C>> extends AbstractConsumerN<C>, Variant<BiConsumer<T1, T2>> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t1 the first input argument
     * @param t2 the second input argument
     */
    void accept(final T1 t1, final T2 t2);

    @SuppressWarnings("unchecked")
    @Override
    default void acceptAllUnchecked(final Object... args) {
        this.accept((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see BiConsumer
     */
    @Override
    default BiConsumer<T1, T2> toVariant() {
        return this::accept;
    }

    @Override
    default AbstractConsumer2<T1, T2, C> andThen(final C after) {
        return (AbstractConsumer2<T1, T2, C>) AbstractConsumerN.super.andThen(after);
    }

    @Override
    default AbstractConsumer2<T1, T2, C> andThenUnchecked(final C after) {
        return (final T1 t1, final T2 t2) -> {
            this.accept(t1, t2);
            after.accept(t1, t2);
        };
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<T1, T2, V> andThen(final Function1<? super Void, ? extends V> after) {
        return (Function2<T1, T2, V>) AbstractConsumerN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<T1, T2, V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> {
            this.accept(t1, t2);
            return after.apply(null);
        };
    }
}
