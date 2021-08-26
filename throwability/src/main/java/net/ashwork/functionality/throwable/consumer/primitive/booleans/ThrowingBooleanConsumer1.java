/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.consumer.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.primitive.booleans.BooleanConsumer1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.consumer.primitive.booleans.AbstractThrowingBooleanConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumerN;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingBooleanFunction1;

/**
 * Represents an operation that accepts a {@code boolean}-valued argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingConsumerN}.
 * This is the non-producing specialization of {@link ThrowingFunction1}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ThrowingConsumer1}.
 * this is the throwing variation of {@link BooleanConsumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(boolean)}.
 *
 * @see ThrowingConsumerN
 * @see ThrowingFunction1
 * @see ThrowingConsumer1
 * @see BooleanConsumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingBooleanConsumer1 extends AbstractThrowingBooleanConsumer1<AbstractThrowingBooleanConsumer1.Handler, ThrowingBooleanConsumer1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ThrowingBooleanConsumer1
     */
    static ThrowingBooleanConsumer1 from(final BooleanConsumer1 consumer) {
        return consumer::accept;
    }

    /**
     * @see ThrowingConsumer1
     */
    @Override
    default ThrowingConsumer1<Boolean> boxInput() {
        return this::accept;
    }

    /**
     * @see BooleanConsumer1
     */
    @Override
    default BooleanConsumer1 handle(final Handler handler) {
        return (final boolean value) -> {
            try {
                this.accept(value);
            } catch (final Throwable t) {
                handler.acceptThrown(t, value);
            }
        };
    }

    /**
     * @see BooleanConsumer1
     */
    @Override
    default BooleanConsumer1 swallow() {
        return this.handle((t, value) -> {});
    }

    @Override
    default ThrowingBooleanConsumer1 andThen(final ThrowingBooleanConsumer1 after) {
        return (ThrowingBooleanConsumer1) AbstractThrowingBooleanConsumer1.super.andThen(after);
    }

    @Override
    default ThrowingBooleanConsumer1 andThenUnchecked(final ThrowingBooleanConsumer1 after) {
        return (final boolean value) -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingBooleanFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingBooleanFunction1<V>) AbstractThrowingBooleanConsumer1.super.andThen(after);
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default <V> ThrowingBooleanFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final boolean value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}
