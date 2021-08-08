/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.abstracts.AbstractConsumer1;

import java.util.function.Consumer;

/**
 * Represents an operation that accepts one argument and returns no result.
 * This is the one-arity specialization of {@link ConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(Object)}.
 *
 * @param <T1> the type of the input to the operation
 *
 * @see ConsumerN
 * @see Function1
 * @since 1.0.0
 */
@FunctionalInterface
public interface Consumer1<T1> extends AbstractConsumer1<T1, Consumer1<T1>> {

    /**
     * Creates an instance of this object from its {@link Consumer} variant.
     *
     * @param consumer the variant of this object
     * @param <T1> the type of the input to the operation
     * @return an instance of this object
     *
     * @see Consumer
     */
    static <T1> Consumer1<T1> fromVariant(final Consumer<? super T1> consumer) {
        return consumer::accept;
    }

    @Override
    default Consumer1<T1> andThen(final Consumer1<T1> after) {
        return (Consumer1<T1>) AbstractConsumer1.super.andThen(after);
    }

    @Override
    default Consumer1<T1> andThenUnchecked(final Consumer1<T1> after) {
        return (final T1 t1) -> {
            this.accept(t1);
            after.accept(t1);
        };
    }
}
