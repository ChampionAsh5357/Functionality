/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.consumer.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.primitive.doubles.DoubleConsumer1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.consumer.primitive.doubles.AbstractThrowingDoubleConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumerN;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingDoubleFunction1;

/**
 * Represents an operation that accepts a {@code double}-valued argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingConsumerN}.
 * This is the non-producing specialization of {@link ThrowingFunction1}.
 * This is the {@code double}-consuming primitive specialization of {@link ThrowingConsumer1}.
 * this is the throwing variation of {@link DoubleConsumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(double)}.
 *
 * @see ThrowingConsumerN
 * @see ThrowingFunction1
 * @see ThrowingConsumer1
 * @see DoubleConsumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingDoubleConsumer1 extends AbstractThrowingDoubleConsumer1<AbstractThrowingDoubleConsumer1.Handler, ThrowingDoubleConsumer1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ThrowingDoubleConsumer1
     */
    static ThrowingDoubleConsumer1 from(final DoubleConsumer1 consumer) {
        return consumer::accept;
    }

    /**
     * @see ThrowingConsumer1
     */
    @Override
    default ThrowingConsumer1<Double> boxInput() {
        return this::accept;
    }

    /**
     * @see DoubleConsumer1
     */
    @Override
    default DoubleConsumer1 handle(final Handler handler) {
        return (final double value) -> {
            try {
                this.accept(value);
            } catch (final Throwable t) {
                handler.acceptThrown(t, value);
            }
        };
    }

    /**
     * @see DoubleConsumer1
     */
    @Override
    default DoubleConsumer1 swallow() {
        return this.handle((t, value) -> {});
    }

    @Override
    default ThrowingDoubleConsumer1 andThen(final ThrowingDoubleConsumer1 after) {
        return (ThrowingDoubleConsumer1) AbstractThrowingDoubleConsumer1.super.andThen(after);
    }

    @Override
    default ThrowingDoubleConsumer1 andThenUnchecked(final ThrowingDoubleConsumer1 after) {
        return (final double value) -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingDoubleFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingDoubleFunction1<V>) AbstractThrowingDoubleConsumer1.super.andThen(after);
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default <V> ThrowingDoubleFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final double value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}
