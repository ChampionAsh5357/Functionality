/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.consumer.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.primitive.longs.LongConsumer1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.consumer.primitive.longs.AbstractThrowingLongConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumerN;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingLongFunction1;

/**
 * Represents an operation that accepts a {@code long}-valued argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingConsumerN}.
 * This is the non-producing specialization of {@link ThrowingFunction1}.
 * This is the {@code long}-consuming primitive specialization of {@link ThrowingConsumer1}.
 * this is the throwing variation of {@link LongConsumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(long)}.
 *
 * @see ThrowingConsumerN
 * @see ThrowingFunction1
 * @see ThrowingConsumer1
 * @see LongConsumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingLongConsumer1 extends AbstractThrowingLongConsumer1<AbstractThrowingLongConsumer1.Handler, ThrowingLongConsumer1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ThrowingLongConsumer1
     */
    static ThrowingLongConsumer1 from(final LongConsumer1 consumer) {
        return consumer::accept;
    }

    /**
     * @see ThrowingConsumer1
     */
    @Override
    default ThrowingConsumer1<Long> boxInput() {
        return this::accept;
    }

    /**
     * @see LongConsumer1
     */
    @Override
    default LongConsumer1 handle(final Handler handler) {
        return (final long value) -> {
            try {
                this.accept(value);
            } catch (final Throwable t) {
                handler.acceptThrown(t, value);
            }
        };
    }

    /**
     * @see LongConsumer1
     */
    @Override
    default LongConsumer1 swallow() {
        return this.handle((t, value) -> {});
    }

    @Override
    default ThrowingLongConsumer1 andThen(final ThrowingLongConsumer1 after) {
        return (ThrowingLongConsumer1) AbstractThrowingLongConsumer1.super.andThen(after);
    }

    @Override
    default ThrowingLongConsumer1 andThenUnchecked(final ThrowingLongConsumer1 after) {
        return (final long value) -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * @see ThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingLongFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingLongFunction1<V>) AbstractThrowingLongConsumer1.super.andThen(after);
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default <V> ThrowingLongFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final long value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}
