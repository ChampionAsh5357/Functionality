/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.consumer.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.primitive.bytes.ByteConsumer1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.consumer.primitive.bytes.AbstractThrowingByteConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumer1;
import net.ashwork.functionality.throwable.consumer.ThrowingConsumerN;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingByteFunction1;

/**
 * Represents an operation that accepts a {@code byte}-valued argument and returns no result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingConsumerN}.
 * This is the non-producing specialization of {@link ThrowingFunction1}.
 * This is the {@code byte}-consuming primitive specialization of {@link ThrowingConsumer1}.
 * this is the throwing variation of {@link ByteConsumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(byte)}.
 *
 * @see ThrowingConsumerN
 * @see ThrowingFunction1
 * @see ThrowingConsumer1
 * @see ByteConsumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingByteConsumer1 extends AbstractThrowingByteConsumer1<AbstractThrowingByteConsumer1.Handler, ThrowingByteConsumer1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ThrowingByteConsumer1
     */
    static ThrowingByteConsumer1 from(final ByteConsumer1 consumer) {
        return consumer::accept;
    }

    /**
     * @see ThrowingConsumer1
     */
    @Override
    default ThrowingConsumer1<Byte> boxInput() {
        return this::accept;
    }

    /**
     * @see ByteConsumer1
     */
    @Override
    default ByteConsumer1 handle(final Handler handler) {
        return (final byte value) -> {
            try {
                this.accept(value);
            } catch (final Throwable t) {
                handler.acceptThrown(t, value);
            }
        };
    }

    /**
     * @see ByteConsumer1
     */
    @Override
    default ByteConsumer1 swallow() {
        return this.handle((t, value) -> {});
    }

    @Override
    default ThrowingByteConsumer1 andThen(final ThrowingByteConsumer1 after) {
        return (ThrowingByteConsumer1) AbstractThrowingByteConsumer1.super.andThen(after);
    }

    @Override
    default ThrowingByteConsumer1 andThenUnchecked(final ThrowingByteConsumer1 after) {
        return (final byte value) -> {
            this.accept(value);
            after.accept(value);
        };
    }

    /**
     * @see ThrowingByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingByteFunction1<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingByteFunction1<V>) AbstractThrowingByteConsumer1.super.andThen(after);
    }

    /**
     * @see ThrowingByteFunction1
     */
    @Override
    default <V> ThrowingByteFunction1<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final byte value) -> {
            this.accept(value);
            return after.apply(null);
        };
    }
}
