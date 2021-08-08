/*
 * Operating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.operator.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.Operator1;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.booleans.BooleanFunction1;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction1;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunctionN;

/**
 * Represents an operation that accepts a {@code boolean}-valued operand and produces a result of the same type as its operand.
 * This is the one-arity specialization of {@link OperatorN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link BooleanFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(boolean)}.
 *
 * @see OperatorN
 * @see ToBooleanFunction1
 * @see BooleanFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface BooleanOperator1 extends OperatorN<Boolean>, ToBooleanFunctionN, InputChainableInput<Boolean>, UnboxedAll<Operator1<Boolean>, ToBooleanFunction1<Boolean>, BooleanFunction1<Boolean>> {

    /**
     * Applies this operator to the given operand.
     *
     * @param value the operand
     * @return the operator result
     */
    boolean applyAsBoolean(final boolean value);

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) {
        return this.applyAsBoolean((boolean) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Operator1
     */
    @Override
    default Operator1<Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ToBooleanFunction1
     */
    @Override
    default ToBooleanFunction1<Boolean> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see BooleanFunction1
     */
    @Override
    default BooleanFunction1<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    /**
     * @see ToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToBooleanFunction1<V> compose(final Function1<? super V, ? extends Boolean> before) {
        return (ToBooleanFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToBooleanFunction1
     */
    @Override
    default <V> ToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends Boolean> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see BooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> BooleanFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (BooleanFunction1<V>) ToBooleanFunctionN.super.andThen(after);
    }

    /**
     * @see BooleanFunction1
     */
    @Override
    default <V> BooleanFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final boolean value) -> after.apply(this.applyAsBoolean(value));
    }
}
