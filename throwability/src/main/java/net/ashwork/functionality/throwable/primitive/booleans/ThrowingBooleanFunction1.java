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
import net.ashwork.functionality.primitive.booleans.BooleanFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingBooleanFunction1;

/**
 * Represents a function that accepts a {@code boolean}-valued argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToBooleanFunctionN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ThrowingFunction1}.
 * This is the throwing variation of {@link BooleanFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(boolean)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see ThrowingFunction1
 * @see ThrowingToBooleanFunctionN
 * @see BooleanFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingBooleanFunction1<R> extends AbstractThrowingBooleanFunction1<R, AbstractThrowingBooleanFunction1.Handler<R>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <R> the type of the result of the function
     * @return a throwing instance of the original type
     *
     * @see BooleanFunction1
     */
    static <R> ThrowingBooleanFunction1<R> from(final BooleanFunction1<R> function) {
        return function::apply;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Boolean, R> boxInput() {
        return this::apply;
    }

    @Override
    default BooleanFunction1<R> swallow() {
        return this.handle((t, value) -> null);
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<V, R> compose(final Function1<? super V, ? extends Boolean> before) {
        return (ThrowingFunction1<V, R>) AbstractThrowingBooleanFunction1.super.compose(before);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<V, R> composeUnchecked(final Function1<? super V, ? extends Boolean> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingBooleanFunction1<V> andThen(final Function1<? super R, ? extends V> after) {
        return (ThrowingBooleanFunction1<V>) AbstractThrowingBooleanFunction1.super.andThen(after);
    }

    @Override
    default <V> ThrowingBooleanFunction1<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final boolean value) -> after.apply(this.apply(value));
    }
}
