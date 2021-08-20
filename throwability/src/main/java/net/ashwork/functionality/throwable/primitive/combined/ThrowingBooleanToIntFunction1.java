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
import net.ashwork.functionality.primitive.combined.BooleanToIntFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingBooleanToIntFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunctionN;

/**
 * Represents a function that accepts a {@code boolean}-valued argument and produces an {@code int}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToIntFunctionN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ThrowingToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link ThrowingBooleanFunction1}.
 * This is the throwing variation of {@link BooleanToIntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(boolean)}.
 *
 * @see ThrowingBooleanFunction1
 * @see ThrowingToIntFunction1
 * @see ThrowingToIntFunctionN
 * @see BooleanToIntFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingBooleanToIntFunction1 extends AbstractThrowingBooleanToIntFunction1<ThrowingFunction1<Boolean, Integer>, ThrowingToIntFunction1<Boolean>, ThrowingBooleanFunction1<Integer>, AbstractThrowingBooleanToIntFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see BooleanToIntFunction1
     */
    static ThrowingBooleanToIntFunction1 from(final BooleanToIntFunction1 function) {
        return function::applyAsInt;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Boolean, Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default ThrowingToIntFunction1<Boolean> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default ThrowingBooleanFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    @Override
    default BooleanToIntFunction1 swallow() {
        return this.handle((t, value) -> 0);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToIntFunction1<V> compose(final Function1<? super V, ? extends Boolean> before) {
        return (ThrowingToIntFunction1<V>) AbstractThrowingBooleanToIntFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default <V> ThrowingToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Boolean> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingBooleanFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingBooleanFunction1<V>) AbstractThrowingBooleanToIntFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default <V> ThrowingBooleanFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final boolean value) -> after.apply(this.applyAsInt(value));
    }
}
