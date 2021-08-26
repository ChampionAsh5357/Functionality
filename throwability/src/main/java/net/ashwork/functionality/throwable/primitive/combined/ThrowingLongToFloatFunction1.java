/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.combined.LongToFloatFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingLongToFloatFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunctionN;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingLongFunction1;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a {@code float}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToFloatFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link ThrowingToFloatFunction1}.
 * This is the {@code float}-producing primitive specialization of {@link ThrowingLongFunction1}.
 * This is the throwing variation of {@link LongToFloatFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat(long)}.
 *
 * @see ThrowingLongFunction1
 * @see ThrowingToFloatFunction1
 * @see ThrowingToFloatFunctionN
 * @see LongToFloatFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingLongToFloatFunction1 extends AbstractThrowingLongToFloatFunction1<AbstractThrowingLongToFloatFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see LongToFloatFunction1
     */
    static ThrowingLongToFloatFunction1 from(final LongToFloatFunction1 function) {
        return function::applyAsFloat;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Long, Float> box() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default ThrowingToFloatFunction1<Long> boxInput() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default ThrowingLongFunction1<Float> boxResult() {
        return this::applyAsFloat;
    }

    @Override
    default LongToFloatFunction1 swallow() {
        return this.handle((t, value) -> 0.0f);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToFloatFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ThrowingToFloatFunction1<V>) AbstractThrowingLongToFloatFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default <V> ThrowingToFloatFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsFloat(before.apply(v));
    }

    /**
     * @see ThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingLongFunction1<V> andThen(final Function1<? super Float, ? extends V> after) {
        return (ThrowingLongFunction1<V>) AbstractThrowingLongToFloatFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default <V> ThrowingLongFunction1<V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsFloat(value));
    }
}
