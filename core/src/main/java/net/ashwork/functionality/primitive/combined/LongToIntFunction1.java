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
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.primitive.ints.ToIntFunction1;
import net.ashwork.functionality.primitive.ints.ToIntFunctionN;
import net.ashwork.functionality.primitive.longs.LongFunction1;

import java.util.function.LongToIntFunction;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces an {@code int}-valued result.
 * This is the one-arity specialization of {@link ToIntFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link ToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link LongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(long)}.
 *
 * @see LongFunction1
 * @see ToIntFunction1
 * @see ToIntFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface LongToIntFunction1 extends ToIntFunctionN, InputChainableInput<Long>, UnboxedAll<Function1<Long, Integer>, ToIntFunction1<Long>, LongFunction1<Integer>>, Variant<LongToIntFunction> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    int applyAsInt(final long value);

    @Override
    default int applyAllAsIntUnchecked(final Object... args) {
        return this.applyAsInt((long) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link LongToIntFunction} variant.
     *
     * @param function the variant of this object
     * @return an instance of this object
     *
     * @see LongToIntFunction
     */
    static LongToIntFunction1 fromVariant(final LongToIntFunction function) {
        return function::applyAsInt;
    }

    @Override
    default LongToIntFunction toVariant() {
        return this::applyAsInt;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Long, Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default ToIntFunction1<Long> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see LongFunction1
     */
    @Override
    default LongFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToIntFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ToIntFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default <V> ToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see LongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> LongFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (LongFunction1<V>) ToIntFunctionN.super.andThen(after);
    }

    /**
     * @see LongFunction1
     */
    @Override
    default <V> LongFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsInt(value));
    }
}
