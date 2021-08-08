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
import net.ashwork.functionality.primitive.longs.LongFunction1;
import net.ashwork.functionality.primitive.shorts.ToShortFunction1;
import net.ashwork.functionality.primitive.shorts.ToShortFunctionN;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a {@code short}-valued result.
 * This is the one-arity specialization of {@link ToShortFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link ToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link LongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsShort(long)}.
 *
 * @see LongFunction1
 * @see ToShortFunction1
 * @see ToShortFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface LongToShortFunction1 extends ToShortFunctionN, InputChainableInput<Long>, UnboxedAll<Function1<Long, Short>, ToShortFunction1<Long>, LongFunction1<Short>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    short applyAsShort(final long value);

    @Override
    default short applyAllAsShortUnchecked(final Object... args) {
        return this.applyAsShort((long) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Long, Short> box() {
        return this::applyAsShort;
    }

    /**
     * @see ToShortFunction1
     */
    @Override
    default ToShortFunction1<Long> boxInput() {
        return this::applyAsShort;
    }

    /**
     * @see LongFunction1
     */
    @Override
    default LongFunction1<Short> boxResult() {
        return this::applyAsShort;
    }

    /**
     * @see ToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToShortFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ToShortFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToShortFunction1
     */
    @Override
    default <V> ToShortFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsShort(before.apply(v));
    }

    /**
     * @see LongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> LongFunction1<V> andThen(final Function1<? super Short, ? extends V> after) {
        return (LongFunction1<V>) ToShortFunctionN.super.andThen(after);
    }

    /**
     * @see LongFunction1
     */
    @Override
    default <V> LongFunction1<V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsShort(value));
    }
}
