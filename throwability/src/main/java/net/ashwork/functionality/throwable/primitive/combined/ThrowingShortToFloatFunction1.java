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
import net.ashwork.functionality.primitive.combined.ShortToFloatFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingShortToFloatFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingShortFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunctionN;

/**
 * Represents a function that accepts a {@code short}-valued argument and produces a {@code float}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToFloatFunctionN}.
 * This is the {@code short}-consuming primitive specialization of {@link ThrowingToFloatFunction1}.
 * This is the {@code float}-producing primitive specialization of {@link ThrowingShortFunction1}.
 * This is the throwing variation of {@link ShortToFloatFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat(short)}.
 *
 * @see ThrowingShortFunction1
 * @see ThrowingToFloatFunction1
 * @see ThrowingToFloatFunctionN
 * @see ShortToFloatFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingShortToFloatFunction1 extends AbstractThrowingShortToFloatFunction1<ThrowingFunction1<Short, Float>, ThrowingToFloatFunction1<Short>, ThrowingShortFunction1<Float>, AbstractThrowingShortToFloatFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ShortToFloatFunction1
     */
    static ThrowingShortToFloatFunction1 from(final ShortToFloatFunction1 function) {
        return function::applyAsFloat;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Short, Float> box() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default ThrowingToFloatFunction1<Short> boxInput() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default ThrowingShortFunction1<Float> boxResult() {
        return this::applyAsFloat;
    }

    @Override
    default ShortToFloatFunction1 swallow() {
        return this.handle((t, value) -> 0.0f);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToFloatFunction1<V> compose(final Function1<? super V, ? extends Short> before) {
        return (ThrowingToFloatFunction1<V>) AbstractThrowingShortToFloatFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default <V> ThrowingToFloatFunction1<V> composeUnchecked(final Function1<? super V, ? extends Short> before) {
        return (final V v) -> this.applyAsFloat(before.apply(v));
    }

    /**
     * @see ThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingShortFunction1<V> andThen(final Function1<? super Float, ? extends V> after) {
        return (ThrowingShortFunction1<V>) AbstractThrowingShortToFloatFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default <V> ThrowingShortFunction1<V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final short value) -> after.apply(this.applyAsFloat(value));
    }
}
