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
import net.ashwork.functionality.primitive.combined.BooleanToLongFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingBooleanToLongFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunctionN;

/**
 * Represents a function that accepts a {@code boolean}-valued argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToLongFunctionN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ThrowingToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link ThrowingBooleanFunction1}.
 * This is the throwing variation of {@link BooleanToLongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(boolean)}.
 *
 * @see ThrowingBooleanFunction1
 * @see ThrowingToLongFunction1
 * @see ThrowingToLongFunctionN
 * @see BooleanToLongFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingBooleanToLongFunction1 extends AbstractThrowingBooleanToLongFunction1<AbstractThrowingBooleanToLongFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see BooleanToLongFunction1
     */
    static ThrowingBooleanToLongFunction1 from(final BooleanToLongFunction1 function) {
        return function::applyAsLong;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Boolean, Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default ThrowingToLongFunction1<Boolean> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default ThrowingBooleanFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    @Override
    default BooleanToLongFunction1 swallow() {
        return this.handle((t, value) -> 0L);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToLongFunction1<V> compose(final Function1<? super V, ? extends Boolean> before) {
        return (ThrowingToLongFunction1<V>) AbstractThrowingBooleanToLongFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default <V> ThrowingToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Boolean> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingBooleanFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingBooleanFunction1<V>) AbstractThrowingBooleanToLongFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default <V> ThrowingBooleanFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final boolean value) -> after.apply(this.applyAsLong(value));
    }
}
