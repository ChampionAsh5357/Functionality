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
import net.ashwork.functionality.operator.Operator1;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.shorts.ShortFunction1;
import net.ashwork.functionality.primitive.shorts.ToShortFunction1;
import net.ashwork.functionality.primitive.shorts.ToShortFunctionN;

/**
 * Represents an operation that accepts a {@code short}-valued operand and produces a result of the same type as its operand.
 * This is the one-arity specialization of {@link OperatorN}.
 * This is the {@code short}-consuming primitive specialization of {@link ToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link ShortFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsShort(short)}.
 *
 * @see OperatorN
 * @see ToShortFunction1
 * @see ShortFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ShortOperator1 extends OperatorN<Short>, ToShortFunctionN, InputChainableInput<Short>, UnboxedAll<Operator1<Short>, ToShortFunction1<Short>, ShortFunction1<Short>> {

    /**
     * Applies this operator to the given operand.
     *
     * @param value the operand
     * @return the operator result
     */
    short applyAsShort(final short value);

    @Override
    default short applyAllAsShortUnchecked(final Object... args) {
        return this.applyAsShort((short) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Operator1
     */
    @Override
    default Operator1<Short> box() {
        return this::applyAsShort;
    }

    /**
     * @see ToShortFunction1
     */
    @Override
    default ToShortFunction1<Short> boxInput() {
        return this::applyAsShort;
    }

    /**
     * @see ShortFunction1
     */
    @Override
    default ShortFunction1<Short> boxResult() {
        return this::applyAsShort;
    }

    /**
     * @see ToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToShortFunction1<V> compose(final Function1<? super V, ? extends Short> before) {
        return (ToShortFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToShortFunction1
     */
    @Override
    default <V> ToShortFunction1<V> composeUnchecked(final Function1<? super V, ? extends Short> before) {
        return (final V v) -> this.applyAsShort(before.apply(v));
    }

    /**
     * @see ShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ShortFunction1<V> andThen(final Function1<? super Short, ? extends V> after) {
        return (ShortFunction1<V>) ToShortFunctionN.super.andThen(after);
    }

    /**
     * @see ShortFunction1
     */
    @Override
    default <V> ShortFunction1<V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final short value) -> after.apply(this.applyAsShort(value));
    }
}
