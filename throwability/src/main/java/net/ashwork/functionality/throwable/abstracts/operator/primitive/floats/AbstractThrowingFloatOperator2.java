/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.operator.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.floats.FloatOperator2;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingToFloatFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingToFloatFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts two {@code float}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is the {@code float}-consuming primitive specialization of {@link AbstractThrowingToFloatFunction2}.
 * This is the throwing variation of {@link FloatOperator2}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingToFloatFunction2
 * @see FloatOperator2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingFloatOperator2<H extends AbstractThrowingFloatOperator2.Handler> extends AbstractThrowingOperatorN<Float, H>, AbstractThrowingToFloatFunctionN<H>, Unboxed<AbstractThrowingOperator2<Float, ?>>, UnboxedInput<AbstractThrowingToFloatFunction2<Float, Float, ?>> {

    /**
     * Applies this operator to the given operands or throws a throwable.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     * @throws Throwable if the operation cannot be computed
     */
    float applyAsFloat(final float value1, final float value2) throws Throwable;

    @Override
    default float applyAllAsFloatUnchecked(final Object... args) throws Throwable {
        return this.applyAsFloat((float) args[0], (float) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractThrowingOperator2
     */
    @Override
    AbstractThrowingOperator2<Float, ?> box();

    /**
     * @see AbstractThrowingToFloatFunction2
     */
    @Override
    AbstractThrowingToFloatFunction2<Float, Float, ?> boxInput();

    /**
     * @see FloatOperator2
     */
    @Override
    default FloatOperator2 handle(final H handler) {
        return (final float value1, final float value2) -> {
            try {
                return this.applyAsFloat(value1, value2);
            } catch (final Throwable t) {
                return handler.onThrown(t, value1, value2);
            }
        };
    }

    /**
     * @see FloatOperator2
     */
    @Override
    FloatOperator2 swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<Float, Float, V, ?> andThen(final Function1<? super Float, ? extends V> after) {
        return (AbstractThrowingFunction2<Float, Float, V, ?>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<Float, Float, V, ?> andThenUnchecked(final Function1<? super Float, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToFloatFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value1 the first operand
         * @param value2 the second operand
         * @return the handled result
         */
        float onThrown(final Throwable t, final float value1, final float value2);

        @Override
        default float onThrownAsFloatUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (float) args[0], (float) args[1]);
        }
    }
}
