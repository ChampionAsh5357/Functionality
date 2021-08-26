/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.consumer.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.primitive.floats.FloatConsumer1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.consumer.primitive.floats.AbstractThrowingFloatConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumerN;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingFloatFunction1;

/**
 * Represents an operation that accepts a {@code float}-valued argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingConsumerN}.
 * This is the non-producing specialization of {@link ThrowingFunction1}.
 * This is the {@code float}-consuming primitive specialization of {@link ThrowingConsumer1}.
 * this is the throwing variation of {@link FloatConsumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(float)}.
 *
 * @see ThrowingConsumerN
 * @see ThrowingFunction1
 * @see ThrowingConsumer1
 * @see FloatConsumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatConsumer1 extends AbstractThrowingFloatConsumer1<AbstractThrowingFloatConsumer1.Handler, ThrowingFloatConsumer1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ThrowingFloatConsumer1
     */
    static ThrowingFloatConsumer1 from(final FloatConsumer1 consumer) {
        return consumer::accept;
    }

    /**
     * @see ThrowingConsumer1
     */
    @Override
    default ThrowingConsumer1<Float> boxInput() {
        return this::accept;
    }

    /**
     * @see FloatConsumer1
     */
    @Override
    default FloatConsumer1 handle(final Handler handler) {
        return (final float value) -> {
            try {
                this.accept(value);
            } catch (final Throwable t) {
                handler.acceptThrown(t, value);
            }
        };
    }

    /**
     * @see FloatConsumer1
     */
    @Override
    default FloatConsumer1 swallow() {
        return this.handle((t, value) -> {});
    }

    @Override
    default ThrowingFloatConsumer1 andThen(final ThrowingFloatConsumer1 after) {
        return (ThrowingFloatConsumer1) AbstractThrowingFloatConsumer1.super.andThen(after);
    }

    @Override
    default ThrowingFloatConsumer1 andThenUnchecked(final ThrowingFloatConsumer1 after) {
        return (final float value) -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFloatFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingFloatFunction1<V>) AbstractThrowingFloatConsumer1.super.andThen(after);
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default <V> ThrowingFloatFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final float value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}
