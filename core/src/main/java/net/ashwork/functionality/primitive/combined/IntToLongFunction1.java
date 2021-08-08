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
import net.ashwork.functionality.primitive.ints.IntFunction1;
import net.ashwork.functionality.primitive.longs.ToLongFunction1;
import net.ashwork.functionality.primitive.longs.ToLongFunctionN;

import java.util.function.IntToLongFunction;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code long}-valued result.
 * This is the one-arity specialization of {@link ToLongFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link ToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link IntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(int)}.
 *
 * @see IntFunction1
 * @see ToLongFunction1
 * @see ToLongFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface IntToLongFunction1 extends ToLongFunctionN, InputChainableInput<Integer>, UnboxedAll<Function1<Integer, Long>, ToLongFunction1<Integer>, IntFunction1<Long>>, Variant<IntToLongFunction> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    long applyAsLong(final int value);

    @Override
    default long applyAllAsLongUnchecked(final Object... args) {
        return this.applyAsLong((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link IntToLongFunction} variant.
     *
     * @param function the variant of this object
     * @return an instance of this object
     *
     * @see IntToLongFunction
     */
    static IntToLongFunction1 fromVariant(final IntToLongFunction function) {
        return function::applyAsLong;
    }

    @Override
    default IntToLongFunction toVariant() {
        return this::applyAsLong;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Integer, Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    default ToLongFunction1<Integer> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see IntFunction1
     */
    @Override
    default IntFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    /**
     * @see ToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToLongFunction1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (ToLongFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    default <V> ToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see IntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> IntFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (IntFunction1<V>) ToLongFunctionN.super.andThen(after);
    }

    /**
     * @see IntFunction1
     */
    @Override
    default <V> IntFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final int value) -> after.apply(this.applyAsLong(value));
    }
}
