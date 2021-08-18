/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.consumer;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.Consumer1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.consumer.AbstractThrowingConsumer1;

/**
 * Represents an operation that accepts one argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingConsumerN}.
 * This is the non-producing specialization of {@link ThrowingFunction1}.
 * This is the throwing variation of {@link Consumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(Object)}.
 *
 * @param <T1> the type of the input to the operation
 *
 * @see ThrowingConsumerN
 * @see ThrowingFunction1
 * @see Consumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingConsumer1<T1> extends AbstractThrowingConsumer1<T1, AbstractThrowingConsumer1.Handler<T1>, ThrowingConsumer1<T1>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @param <T1> the type of the input to the operation
     * @return a throwing instance of the original type
     *
     * @see Consumer1
     */
    static <T1> ThrowingConsumer1<T1> from(final Consumer1<T1> consumer) {
        return consumer::accept;
    }

    /**
     * @see Consumer1
     */
    @Override
    default Consumer1<T1> handle(final Handler<T1> handler) {
        return (final T1 t1) -> {
            try {
                this.accept(t1);
            } catch (final Throwable t) {
                handler.acceptThrown(t, t1);
            }
        };
    }

    /**
     * @see Consumer1
     */
    @Override
    default Consumer1<T1> swallow() {
        return this.handle((t, t1) -> {});
    }

    @Override
    default ThrowingConsumer1<T1> andThen(final ThrowingConsumer1<T1> after) {
        return (ThrowingConsumer1<T1>) AbstractThrowingConsumer1.super.andThen(after);
    }

    @Override
    default ThrowingConsumer1<T1> andThenUnchecked(final ThrowingConsumer1<T1> after) {
        return (final T1 t1) -> {
            this.accept(t1);
            after.accept(t1);
        };
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<T1, V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingFunction1<T1, V>) AbstractThrowingConsumer1.super.andThen(after);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<T1, V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final T1 t1) -> {
            this.accept(t1);
            return after.apply(null);
        };
    }
}
