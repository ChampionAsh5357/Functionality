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
import net.ashwork.functionality.primitive.combined.IntToFloatFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingIntToFloatFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunctionN;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingIntFunction1;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code float}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToFloatFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link ThrowingToFloatFunction1}.
 * This is the {@code float}-producing primitive specialization of {@link ThrowingIntFunction1}.
 * This is the throwing variation of {@link IntToFloatFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat(int)}.
 *
 * @see ThrowingIntFunction1
 * @see ThrowingToFloatFunction1
 * @see ThrowingToFloatFunctionN
 * @see IntToFloatFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingIntToFloatFunction1 extends AbstractThrowingIntToFloatFunction1<AbstractThrowingIntToFloatFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see IntToFloatFunction1
     */
    static ThrowingIntToFloatFunction1 from(final IntToFloatFunction1 function) {
        return function::applyAsFloat;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Integer, Float> box() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default ThrowingToFloatFunction1<Integer> boxInput() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingIntFunction1
     */
    @Override
    default ThrowingIntFunction1<Float> boxResult() {
        return this::applyAsFloat;
    }

    @Override
    default IntToFloatFunction1 swallow() {
        return this.handle((t, value) -> 0.0f);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToFloatFunction1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (ThrowingToFloatFunction1<V>) AbstractThrowingIntToFloatFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default <V> ThrowingToFloatFunction1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.applyAsFloat(before.apply(v));
    }

    /**
     * @see ThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingIntFunction1<V> andThen(final Function1<? super Float, ? extends V> after) {
        return (ThrowingIntFunction1<V>) AbstractThrowingIntToFloatFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingIntFunction1
     */
    @Override
    default <V> ThrowingIntFunction1<V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final int value) -> after.apply(this.applyAsFloat(value));
    }
}
