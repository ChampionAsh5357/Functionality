/*
 * Operating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.operator.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.Operator1;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.floats.FloatFunction1;
import net.ashwork.functionality.primitive.floats.ToFloatFunction1;
import net.ashwork.functionality.primitive.floats.ToFloatFunctionN;

/**
 * Represents an operation that accepts a {@code float}-valued operand and produces a result of the same type as its operand.
 * This is the one-arity specialization of {@link OperatorN}.
 * This is the {@code float}-consuming primitive specialization of {@link ToFloatFunction1}.
 * This is the {@code float}-producing primitive specialization of {@link FloatFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat(float)}.
 *
 * @see OperatorN
 * @see ToFloatFunction1
 * @see FloatFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface FloatOperator1 extends OperatorN<Float>, ToFloatFunctionN, InputChainableInput<Float>, UnboxedAll<Operator1<Float>, ToFloatFunction1<Float>, FloatFunction1<Float>> {

    /**
     * Applies this operator to the given operand.
     *
     * @param value the operand
     * @return the operator result
     */
    float applyAsFloat(final float value);

    @Override
    default float applyAllAsFloatUnchecked(final Object... args) {
        return this.applyAsFloat((float) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Operator1
     */
    @Override
    default Operator1<Float> box() {
        return this::applyAsFloat;
    }

    /**
     * @see ToFloatFunction1
     */
    @Override
    default ToFloatFunction1<Float> boxInput() {
        return this::applyAsFloat;
    }

    /**
     * @see FloatFunction1
     */
    @Override
    default FloatFunction1<Float> boxResult() {
        return this::applyAsFloat;
    }

    /**
     * @see ToFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToFloatFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ToFloatFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToFloatFunction1
     */
    @Override
    default <V> ToFloatFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsFloat(before.apply(v));
    }

    /**
     * @see FloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> FloatFunction1<V> andThen(final Function1<? super Float, ? extends V> after) {
        return (FloatFunction1<V>) ToFloatFunctionN.super.andThen(after);
    }

    /**
     * @see FloatFunction1
     */
    @Override
    default <V> FloatFunction1<V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsFloat(value));
    }
}
