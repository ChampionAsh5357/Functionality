/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.doubles;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.partial.Variant;

import java.util.function.DoubleSupplier;

/**
 * Represents a function that accepts no arguments and produces a {@code double}-valued result.
 * This is the zero-arity specialization of {@link ToDoubleFunctionN}.
 * This is the {@code double}-producing primitive specialization of {@link Function0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble()}.
 *
 * @see Function0
 * @see ToDoubleFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToDoubleFunction0 extends ToDoubleFunctionN, UnboxedResult<Function0<Double>>, Variant<DoubleSupplier> {

    /**
     * Applies this function.
     *
     * @return the function result
     */
    double applyAsDouble();

    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) {
        return this.applyAsDouble();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * Creates an instance of this object from its {@link DoubleSupplier} variant.
     *
     * @param supplier the variant of this object
     * @return an instance of this object
     *
     * @see DoubleSupplier
     */
    static ToDoubleFunction0 fromVariant(final DoubleSupplier supplier) {
        return supplier::getAsDouble;
    }

    @Override
    default DoubleSupplier toVariant() {
        return this::applyAsDouble;
    }

    /**
     * @see Function0
     */
    @Override
    default Function0<Double> boxResult() {
        return this::applyAsDouble;
    }

    /**
     * @see Function0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function0<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (Function0<V>) ToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see Function0
     */
    @Override
    default <V> Function0<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return () -> after.apply(this.applyAsDouble());
    }
}
