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
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.operator.Operator2;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction2;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunctionN;

/**
 * Represents an operation that accepts two {@code boolean}-valued operands and produces a result of the same type as its operands.
 * This is the two-arity specialization of {@link OperatorN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ToBooleanFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(boolean, boolean)}.
 *
 * @see OperatorN
 * @see ToBooleanFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface BooleanOperator2 extends OperatorN<Boolean>, ToBooleanFunctionN, Unboxed<Operator2<Boolean>>, UnboxedInput<ToBooleanFunction2<Boolean, Boolean>> {

    /**
     * Applies this operator to the given operands.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     */
    boolean applyAsBoolean(final boolean value1, final boolean value2);

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) {
        return this.applyAsBoolean((boolean) args[0], (boolean) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see Operator2
     */
    @Override
    default Operator2<Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ToBooleanFunction2
     */
    @Override
    default ToBooleanFunction2<Boolean, Boolean> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<Boolean, Boolean, V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (Function2<Boolean, Boolean, V>) ToBooleanFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<Boolean, Boolean, V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final Boolean value1, final Boolean value2) -> after.apply(this.applyAsBoolean(value1, value2));
    }
}
