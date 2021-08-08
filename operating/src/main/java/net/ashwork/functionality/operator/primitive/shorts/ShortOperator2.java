/*
 * Operating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.operator.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.operator.Operator2;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.primitive.shorts.ToShortFunction2;
import net.ashwork.functionality.primitive.shorts.ToShortFunctionN;

/**
 * Represents an operation that accepts two {@code short}-valued operands and produces a result of the same type as its operands.
 * This is the two-arity specialization of {@link OperatorN}.
 * This is the {@code short}-consuming primitive specialization of {@link ToShortFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsShort(short, short)}.
 *
 * @see OperatorN
 * @see ToShortFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ShortOperator2 extends OperatorN<Short>, ToShortFunctionN, Unboxed<Operator2<Short>>, UnboxedInput<ToShortFunction2<Short, Short>> {

    /**
     * Applies this operator to the given operands.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     */
    short applyAsShort(final short value1, final short value2);

    @Override
    default short applyAllAsShortUnchecked(final Object... args) {
        return this.applyAsShort((short) args[0], (short) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see Operator2
     */
    @Override
    default Operator2<Short> box() {
        return this::applyAsShort;
    }

    /**
     * @see ToShortFunction2
     */
    @Override
    default ToShortFunction2<Short, Short> boxInput() {
        return this::applyAsShort;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<Short, Short, V> andThen(final Function1<? super Short, ? extends V> after) {
        return (Function2<Short, Short, V>) ToShortFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<Short, Short, V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final Short value1, final Short value2) -> after.apply(this.applyAsShort(value1, value2));
    }
}
