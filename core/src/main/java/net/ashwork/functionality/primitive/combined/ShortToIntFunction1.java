/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.shorts.ShortFunction1;
import net.ashwork.functionality.primitive.ints.ToIntFunction1;
import net.ashwork.functionality.primitive.ints.ToIntFunctionN;

/**
 * Represents a function that accepts a {@code short}-valued argument and produces an {@code int}-valued result.
 * This is the one-arity specialization of {@link ToIntFunctionN}.
 * This is the {@code short}-consuming primitive specialization of {@link ToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link ShortFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(short)}.
 *
 * @see ShortFunction1
 * @see ToIntFunction1
 * @see ToIntFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ShortToIntFunction1 extends ToIntFunctionN, InputChainableInput<Short>, UnboxedAll<Function1<Short, Integer>, ToIntFunction1<Short>, ShortFunction1<Integer>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    int applyAsInt(final short value);

    @Override
    default int applyAllAsIntUnchecked(final Object... args) {
        return this.applyAsInt((short) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Short, Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default ToIntFunction1<Short> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see ShortFunction1
     */
    @Override
    default ShortFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToIntFunction1<V> compose(final Function1<? super V, ? extends Short> before) {
        return (ToIntFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default <V> ToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Short> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see ShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ShortFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ShortFunction1<V>) ToIntFunctionN.super.andThen(after);
    }

    /**
     * @see ShortFunction1
     */
    @Override
    default <V> ShortFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final short value) -> after.apply(this.applyAsInt(value));
    }
}
