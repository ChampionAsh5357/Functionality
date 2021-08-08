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
import net.ashwork.functionality.primitive.booleans.BooleanFunction1;
import net.ashwork.functionality.primitive.longs.ToLongFunction1;
import net.ashwork.functionality.primitive.longs.ToLongFunctionN;

/**
 * Represents a function that accepts a {@code boolean}-valued argument and produces a {@code long}-valued result.
 * This is the one-arity specialization of {@link ToLongFunctionN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link BooleanFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(boolean)}.
 *
 * @see BooleanFunction1
 * @see ToLongFunction1
 * @see ToLongFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface BooleanToLongFunction1 extends ToLongFunctionN, InputChainableInput<Boolean>, UnboxedAll<Function1<Boolean, Long>, ToLongFunction1<Boolean>, BooleanFunction1<Long>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    long applyAsLong(final boolean value);

    @Override
    default long applyAllAsLongUnchecked(final Object... args) {
        return this.applyAsLong((boolean) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Boolean, Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    default ToLongFunction1<Boolean> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see BooleanFunction1
     */
    @Override
    default BooleanFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    /**
     * @see ToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToLongFunction1<V> compose(final Function1<? super V, ? extends Boolean> before) {
        return (ToLongFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    default <V> ToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Boolean> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see BooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> BooleanFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (BooleanFunction1<V>) ToLongFunctionN.super.andThen(after);
    }

    /**
     * @see BooleanFunction1
     */
    @Override
    default <V> BooleanFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final boolean value) -> after.apply(this.applyAsLong(value));
    }
}
