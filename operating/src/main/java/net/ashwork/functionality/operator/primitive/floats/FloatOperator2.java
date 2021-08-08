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
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.operator.Operator2;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.primitive.floats.ToFloatFunction2;
import net.ashwork.functionality.primitive.floats.ToFloatFunctionN;

/**
 * Represents an operation that accepts two {@code float}-valued operands and produces a result of the same type as its operands.
 * This is the two-arity specialization of {@link OperatorN}.
 * This is the {@code float}-consuming primitive specialization of {@link ToFloatFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat(float, float)}.
 *
 * @see OperatorN
 * @see ToFloatFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface FloatOperator2 extends OperatorN<Float>, ToFloatFunctionN, Unboxed<Operator2<Float>>, UnboxedInput<ToFloatFunction2<Float, Float>> {

    /**
     * Applies this operator to the given operands.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     */
    float applyAsFloat(final float value1, final float value2);

    @Override
    default float applyAllAsFloatUnchecked(final Object... args) {
        return this.applyAsFloat((float) args[0], (float) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see Operator2
     */
    @Override
    default Operator2<Float> box() {
        return this::applyAsFloat;
    }

    /**
     * @see ToFloatFunction2
     */
    @Override
    default ToFloatFunction2<Float, Float> boxInput() {
        return this::applyAsFloat;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<Float, Float, V> andThen(final Function1<? super Float, ? extends V> after) {
        return (Function2<Float, Float, V>) ToFloatFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<Float, Float, V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final Float value1, final Float value2) -> after.apply(this.applyAsFloat(value1, value2));
    }
}
