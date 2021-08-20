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
import net.ashwork.functionality.primitive.combined.IntToBooleanFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingIntToBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunctionN;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingIntFunction1;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToBooleanFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link ThrowingToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link ThrowingIntFunction1}.
 * This is the throwing variation of {@link IntToBooleanFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(int)}.
 *
 * @see ThrowingIntFunction1
 * @see ThrowingToBooleanFunction1
 * @see ThrowingToBooleanFunctionN
 * @see IntToBooleanFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingIntToBooleanFunction1 extends AbstractThrowingIntToBooleanFunction1<ThrowingFunction1<Integer, Boolean>, ThrowingToBooleanFunction1<Integer>, ThrowingIntFunction1<Boolean>, AbstractThrowingIntToBooleanFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see IntToBooleanFunction1
     */
    static ThrowingIntToBooleanFunction1 from(final IntToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Integer, Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default ThrowingToBooleanFunction1<Integer> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingIntFunction1
     */
    @Override
    default ThrowingIntFunction1<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    @Override
    default IntToBooleanFunction1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToBooleanFunction1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (ThrowingToBooleanFunction1<V>) AbstractThrowingIntToBooleanFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default <V> ThrowingToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see ThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingIntFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingIntFunction1<V>) AbstractThrowingIntToBooleanFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingIntFunction1
     */
    @Override
    default <V> ThrowingIntFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final int value) -> after.apply(this.applyAsBoolean(value));
    }
}
