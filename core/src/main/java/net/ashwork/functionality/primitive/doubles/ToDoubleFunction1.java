/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.partial.Variant;

import java.util.function.ToDoubleFunction;

/**
 * Represents a function that accepts one argument and produces a {@code double}-valued result.
 * This is the one-arity specialization of {@link ToDoubleFunctionN}.
 * This is the {@code double}-producing primitive specialization of {@link Function1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(Object)}.
 *
 * @param <T1> the type of the input to the function
 *
 * @see Function1
 * @see ToDoubleFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToDoubleFunction1<T1> extends ToDoubleFunctionN, InputChainableInput<T1>, UnboxedResult<Function1<T1, Double>>, Variant<ToDoubleFunction<T1>> {

    /**
     * Applies this function to the given argument.
     *
     * @param t1 the function argument
     * @return the function result
     */
    double applyAsDouble(final T1 t1);

    @SuppressWarnings("unchecked")
    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) {
        return this.applyAsDouble((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link ToDoubleFunction} variant.
     *
     * @param function the variant of this object
     * @param <T1> the type of the input to the function
     * @return an instance of this object
     *
     * @see ToDoubleFunction
     */
    static <T1> ToDoubleFunction1<T1> fromVariant(final ToDoubleFunction<T1> function) {
        return function::applyAsDouble;
    }

    @Override
    default ToDoubleFunction<T1> toVariant() {
        return this::applyAsDouble;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<T1, Double> boxResult() {
        return this::applyAsDouble;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ToDoubleFunction1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (ToDoubleFunction1<V>) InputChainableInput.super.compose(before);
    }

    @Override
    default <V> ToDoubleFunction1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.applyAsDouble(before.apply(v));
    }

    /**
     * @see Function1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<T1, V> andThen(final Function1<? super Double, ? extends V> after) {
        return (Function1<T1, V>) ToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see Function1
     */
    @Override
    default <V> Function1<T1, V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.applyAsDouble(t1));
    }
}
