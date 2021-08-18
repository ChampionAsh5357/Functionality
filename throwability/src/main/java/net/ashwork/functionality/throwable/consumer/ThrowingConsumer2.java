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
import net.ashwork.functionality.consumer.Consumer2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.consumer.AbstractThrowingConsumer2;

/**
 * Represents an operation that accepts two arguments and returns no result or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingConsumerN}.
 * This is the non-producing specialization of {@link ThrowingFunction2}.
 * This is the throwing variation of {@link Consumer2}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the operation
 * @param <T2> the type of the second argument to the operation
 *
 * @see ThrowingConsumerN
 * @see ThrowingFunction2
 * @see Consumer2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingConsumer2<T1, T2> extends AbstractThrowingConsumer2<T1, T2, AbstractThrowingConsumer2.Handler<T1, T2>, ThrowingConsumer2<T1, T2>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @param <T1> the type of the first argument to the operation
     * @param <T2> the type of the second argument to the operation
     * @return a throwing instance of the original type
     *
     * @see Consumer2
     */
    static <T1, T2> ThrowingConsumer2<T1, T2> from(final Consumer2<T1, T2> consumer) {
        return consumer::accept;
    }

    /**
     * @see Consumer2
     */
    @Override
    default Consumer2<T1, T2> handle(final Handler<T1, T2> handler) {
        return (final T1 t1, final T2 t2) -> {
            try {
                this.accept(t1, t2);
            } catch (final Throwable t) {
                handler.acceptThrown(t, t1, t2);
            }
        };
    }

    /**
     * @see Consumer2
     */
    @Override
    default Consumer2<T1, T2> swallow() {
        return this.handle((t, t1, t2) -> {});
    }

    @Override
    default ThrowingConsumer2<T1, T2> andThen(final ThrowingConsumer2<T1, T2> after) {
        return (ThrowingConsumer2<T1, T2>) AbstractThrowingConsumer2.super.andThen(after);
    }

    @Override
    default ThrowingConsumer2<T1, T2> andThenUnchecked(final ThrowingConsumer2<T1, T2> after) {
        return (final T1 t1, final T2 t2) -> {
            this.accept(t1, t2);
            after.accept(t1, t2);
        };
    }

    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingFunction2<T1, T2, V>) AbstractThrowingConsumer2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> {
            this.accept(t1, t2);
            return after.apply(null);
        };
    }
}
