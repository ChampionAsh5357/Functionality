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
import net.ashwork.functionality.primitive.combined.ShortToBooleanFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingShortToBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunctionN;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingShortFunction1;

/**
 * Represents a function that accepts a {@code short}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToBooleanFunctionN}.
 * This is the {@code short}-consuming primitive specialization of {@link ThrowingToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link ThrowingShortFunction1}.
 * This is the throwing variation of {@link ShortToBooleanFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(short)}.
 *
 * @see ThrowingShortFunction1
 * @see ThrowingToBooleanFunction1
 * @see ThrowingToBooleanFunctionN
 * @see ShortToBooleanFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingShortToBooleanFunction1 extends AbstractThrowingShortToBooleanFunction1<ThrowingFunction1<Short, Boolean>, ThrowingToBooleanFunction1<Short>, ThrowingShortFunction1<Boolean>, AbstractThrowingShortToBooleanFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ShortToBooleanFunction1
     */
    static ThrowingShortToBooleanFunction1 from(final ShortToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Short, Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default ThrowingToBooleanFunction1<Short> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default ThrowingShortFunction1<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    @Override
    default ShortToBooleanFunction1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToBooleanFunction1<V> compose(final Function1<? super V, ? extends Short> before) {
        return (ThrowingToBooleanFunction1<V>) AbstractThrowingShortToBooleanFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default <V> ThrowingToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends Short> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see ThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingShortFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingShortFunction1<V>) AbstractThrowingShortToBooleanFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default <V> ThrowingShortFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final short value) -> after.apply(this.applyAsBoolean(value));
    }
}
