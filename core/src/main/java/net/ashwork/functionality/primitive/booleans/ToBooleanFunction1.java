/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedResult;

/**
 * Represents a function that accepts one argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization of {@link ToBooleanFunctionN}.
 * This is the {@code boolean}-producing primitive specialization of {@link Function1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(Object)}.
 *
 * @param <T1> the type of the input to the function
 *
 * @see Function1
 * @see ToBooleanFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToBooleanFunction1<T1> extends ToBooleanFunctionN, InputChainableInput<T1>, UnboxedResult<Function1<T1, Boolean>> {

    /**
     * Applies this function to the given argument.
     *
     * @param t1 the function argument
     * @return the function result
     */
    boolean applyAsBoolean(final T1 t1);

    @SuppressWarnings("unchecked")
    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) {
        return this.applyAsBoolean((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<T1, Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ToBooleanFunction1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (ToBooleanFunction1<V>) InputChainableInput.super.compose(before);
    }

    @Override
    default <V> ToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see Function1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<T1, V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (Function1<T1, V>) ToBooleanFunctionN.super.andThen(after);
    }

    /**
     * @see Function1
     */
    @Override
    default <V> Function1<T1, V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.applyAsBoolean(t1));
    }
}
