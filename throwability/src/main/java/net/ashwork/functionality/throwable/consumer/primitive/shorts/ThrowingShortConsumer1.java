/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.consumer.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.primitive.shorts.ShortConsumer1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.consumer.primitive.shorts.AbstractThrowingShortConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumerN;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingShortFunction1;

/**
 * Represents an operation that accepts a {@code short}-valued argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingConsumerN}.
 * This is the non-producing specialization of {@link ThrowingFunction1}.
 * This is the {@code short}-consuming primitive specialization of {@link ThrowingConsumer1}.
 * this is the throwing variation of {@link ShortConsumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(short)}.
 *
 * @see ThrowingConsumerN
 * @see ThrowingFunction1
 * @see ThrowingConsumer1
 * @see ShortConsumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingShortConsumer1 extends AbstractThrowingShortConsumer1<AbstractThrowingShortConsumer1.Handler, ThrowingShortConsumer1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ThrowingShortConsumer1
     */
    static ThrowingShortConsumer1 from(final ShortConsumer1 consumer) {
        return consumer::accept;
    }

    /**
     * @see ThrowingConsumer1
     */
    @Override
    default ThrowingConsumer1<Short> boxInput() {
        return this::accept;
    }

    /**
     * @see ShortConsumer1
     */
    @Override
    default ShortConsumer1 handle(final Handler handler) {
        return (final short value) -> {
            try {
                this.accept(value);
            } catch (final Throwable t) {
                handler.acceptThrown(t, value);
            }
        };
    }

    /**
     * @see ShortConsumer1
     */
    @Override
    default ShortConsumer1 swallow() {
        return this.handle((t, value) -> {});
    }

    @Override
    default ThrowingShortConsumer1 andThen(final ThrowingShortConsumer1 after) {
        return (ThrowingShortConsumer1) AbstractThrowingShortConsumer1.super.andThen(after);
    }

    @Override
    default ThrowingShortConsumer1 andThenUnchecked(final ThrowingShortConsumer1 after) {
        return (final short value) -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * @see ThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingShortFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingShortFunction1<V>) AbstractThrowingShortConsumer1.super.andThen(after);
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default <V> ThrowingShortFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final short value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}
