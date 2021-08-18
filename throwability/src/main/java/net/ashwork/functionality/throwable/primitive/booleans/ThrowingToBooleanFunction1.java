/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunction1;

/**
 * Represents a function that accepts one argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToBooleanFunctionN}.
 * This is the {@code boolean}-producing primitive specialization of {@link ThrowingFunction1}.
 * This is the throwing variation of {@link ToBooleanFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(Object)}.
 *
 * @param <T1> the type of the input to the function
 *
 * @see ThrowingFunction1
 * @see ThrowingToBooleanFunctionN
 * @see ToBooleanFunction1
 * @since 1.0.0
 */
public interface ThrowingToBooleanFunction1<T1> extends AbstractThrowingToBooleanFunction1<T1, ThrowingFunction1<T1, Boolean>, AbstractThrowingToBooleanFunction1.Handler<T1>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the input to the function
     * @return a throwing instance of the original type
     *
     * @see ToBooleanFunction1
     */
    static <T1> ThrowingToBooleanFunction1<T1> from(final ToBooleanFunction1<T1> function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<T1, Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    @Override
    default ToBooleanFunction1<T1> swallow() {
        return this.handle((t, t1) -> false);
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToBooleanFunction1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (ThrowingToBooleanFunction1<V>) AbstractThrowingToBooleanFunction1.super.compose(before);
    }

    @Override
    default <V> ThrowingToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<T1, V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingFunction1<T1, V>) AbstractThrowingToBooleanFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<T1, V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.applyAsBoolean(t1));
    }
}
