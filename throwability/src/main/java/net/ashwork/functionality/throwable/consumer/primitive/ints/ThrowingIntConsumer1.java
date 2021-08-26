/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.consumer.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.primitive.ints.IntConsumer1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.consumer.primitive.ints.AbstractThrowingIntConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumerN;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingIntFunction1;

/**
 * Represents an operation that accepts a {@code int}-valued argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingConsumerN}.
 * This is the non-producing specialization of {@link ThrowingFunction1}.
 * This is the {@code int}-consuming primitive specialization of {@link ThrowingConsumer1}.
 * this is the throwing variation of {@link IntConsumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(int)}.
 *
 * @see ThrowingConsumerN
 * @see ThrowingFunction1
 * @see ThrowingConsumer1
 * @see IntConsumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingIntConsumer1 extends AbstractThrowingIntConsumer1<AbstractThrowingIntConsumer1.Handler, ThrowingIntConsumer1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ThrowingIntConsumer1
     */
    static ThrowingIntConsumer1 from(final IntConsumer1 consumer) {
        return consumer::accept;
    }

    /**
     * @see ThrowingConsumer1
     */
    @Override
    default ThrowingConsumer1<Integer> boxInput() {
        return this::accept;
    }

    /**
     * @see IntConsumer1
     */
    @Override
    default IntConsumer1 handle(final Handler handler) {
        return (final int value) -> {
            try {
                this.accept(value);
            } catch (final Throwable t) {
                handler.acceptThrown(t, value);
            }
        };
    }

    /**
     * @see IntConsumer1
     */
    @Override
    default IntConsumer1 swallow() {
        return this.handle((t, value) -> {});
    }

    @Override
    default ThrowingIntConsumer1 andThen(final ThrowingIntConsumer1 after) {
        return (ThrowingIntConsumer1) AbstractThrowingIntConsumer1.super.andThen(after);
    }

    @Override
    default ThrowingIntConsumer1 andThenUnchecked(final ThrowingIntConsumer1 after) {
        return (final int value) -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * @see ThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingIntFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingIntFunction1<V>) AbstractThrowingIntConsumer1.super.andThen(after);
    }

    /**
     * @see ThrowingIntFunction1
     */
    @Override
    default <V> ThrowingIntFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final int value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}
