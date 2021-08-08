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
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction1;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunctionN;
import net.ashwork.functionality.primitive.floats.FloatFunction1;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization of {@link ToBooleanFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link ToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link FloatFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(float)}.
 *
 * @see FloatFunction1
 * @see ToBooleanFunction1
 * @see ToBooleanFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface FloatToBooleanFunction1 extends ToBooleanFunctionN, InputChainableInput<Float>, UnboxedAll<Function1<Float, Boolean>, ToBooleanFunction1<Float>, FloatFunction1<Boolean>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    boolean applyAsBoolean(final float value);

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) {
        return this.applyAsBoolean((float) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Float, Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ToBooleanFunction1
     */
    @Override
    default ToBooleanFunction1<Float> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see FloatFunction1
     */
    @Override
    default FloatFunction1<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    /**
     * @see ToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToBooleanFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ToBooleanFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToBooleanFunction1
     */
    @Override
    default <V> ToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see FloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> FloatFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (FloatFunction1<V>) ToBooleanFunctionN.super.andThen(after);
    }

    /**
     * @see FloatFunction1
     */
    @Override
    default <V> FloatFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsBoolean(value));
    }
}
