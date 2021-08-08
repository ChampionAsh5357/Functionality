/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.FunctionN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.partial.Variant;

import java.util.function.LongFunction;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a result.
 * This is the one-arity specialization of {@link FunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link Function1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(long)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see Function1
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface LongFunction1<R> extends FunctionN<R>, InputChainableInput<Long>, UnboxedInput<Function1<Long, R>>, Variant<LongFunction<R>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    R apply(final long value);

    @Override
    default R applyAllUnchecked(final Object... args) {
        return this.apply((long) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link LongFunction} variant.
     *
     * @param function the variant of this object
     * @param <R> the type of the result of the function
     * @return an instance of this object
     *
     * @see LongFunction
     */
    static <R> LongFunction1<R> fromVariant(final LongFunction<R> function) {
        return function::apply;
    }

    @Override
    default LongFunction<R> toVariant() {
        return this::apply;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Long, R> boxInput() {
        return this::apply;
    }

    /**
     * @see Function1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<V, R> compose(final Function1<? super V, ? extends Long> before) {
        return (Function1<V, R>) InputChainableInput.super.compose(before);
    }

    /**
     * @see Function1
     */
    @Override
    default <V> Function1<V, R> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> LongFunction1<V> andThen(final Function1<? super R, ? extends V> after) {
        return (LongFunction1<V>) FunctionN.super.andThen(after);
    }

    @Override
    default <V> LongFunction1<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final long value) -> after.apply(this.apply(value));
    }
}
