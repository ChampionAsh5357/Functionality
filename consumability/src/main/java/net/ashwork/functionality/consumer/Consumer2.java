/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer;

import net.ashwork.functionality.Function2;
import net.ashwork.functionality.consumer.abstracts.AbstractConsumer2;

import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts two arguments and returns no result.
 * This is the two-arity specialization of {@link ConsumerN}.
 * This is the non-producing specialization of {@link Function2}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the operation
 * @param <T2> the type of the second argument to the operation
 *
 * @see ConsumerN
 * @see Function2
 * @since 1.0.0
 */
@FunctionalInterface
public interface Consumer2<T1, T2> extends AbstractConsumer2<T1, T2, Consumer2<T1, T2>> {

    /**
     * Creates an instance of this object from its {@link BiConsumer} variant.
     *
     * @param consumer the variant of this object
     * @param <T1> the type of the first argument to the operation
     * @param <T2> the type of the second argument to the operation
     * @return an instance of this object
     *
     * @see BiConsumer
     */
    static <T1, T2> Consumer2<T1, T2> fromVariant(final BiConsumer<? super T1, ? super T2> consumer) {
        return consumer::accept;
    }

    @Override
    default Consumer2<T1, T2> andThen(final Consumer2<T1, T2> after) {
        return (Consumer2<T1, T2>) AbstractConsumer2.super.andThen(after);
    }

    @Override
    default Consumer2<T1, T2> andThenUnchecked(final Consumer2<T1, T2> after) {
        return (final T1 t1, final T2 t2) -> {
            this.accept(t1, t2);
            after.accept(t1, t2);
        };
    }
}
