/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.consumer.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.primitive.chars.CharConsumer1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.consumer.primitive.chars.AbstractThrowingCharConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumerN;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingCharFunction1;

/**
 * Represents an operation that accepts a {@code char}-valued argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingConsumerN}.
 * This is the non-producing specialization of {@link ThrowingFunction1}.
 * This is the {@code char}-consuming primitive specialization of {@link ThrowingConsumer1}.
 * this is the throwing variation of {@link CharConsumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(char)}.
 *
 * @see ThrowingConsumerN
 * @see ThrowingFunction1
 * @see ThrowingConsumer1
 * @see CharConsumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingCharConsumer1 extends AbstractThrowingCharConsumer1<AbstractThrowingCharConsumer1.Handler, ThrowingCharConsumer1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ThrowingCharConsumer1
     */
    static ThrowingCharConsumer1 from(final CharConsumer1 consumer) {
        return consumer::accept;
    }

    /**
     * @see ThrowingConsumer1
     */
    @Override
    default ThrowingConsumer1<Character> boxInput() {
        return this::accept;
    }

    /**
     * @see CharConsumer1
     */
    @Override
    default CharConsumer1 handle(final Handler handler) {
        return (final char value) -> {
            try {
                this.accept(value);
            } catch (final Throwable t) {
                handler.acceptThrown(t, value);
            }
        };
    }

    /**
     * @see CharConsumer1
     */
    @Override
    default CharConsumer1 swallow() {
        return this.handle((t, value) -> {});
    }

    @Override
    default ThrowingCharConsumer1 andThen(final ThrowingCharConsumer1 after) {
        return (ThrowingCharConsumer1) AbstractThrowingCharConsumer1.super.andThen(after);
    }

    @Override
    default ThrowingCharConsumer1 andThenUnchecked(final ThrowingCharConsumer1 after) {
        return (final char value) -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * @see ThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingCharFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingCharFunction1<V>) AbstractThrowingCharConsumer1.super.andThen(after);
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default <V> ThrowingCharFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final char value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}
