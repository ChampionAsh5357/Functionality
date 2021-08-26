/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.combined.FloatToDoubleFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingToDoubleFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingToDoubleFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingFloatFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code double}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToDoubleFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link AbstractThrowingToDoubleFunction1}.
 * This is the {@code double}-producing primitive specialization of {@link AbstractThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatToDoubleFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFloatFunction1
 * @see AbstractThrowingToDoubleFunction1
 * @see AbstractThrowingToDoubleFunctionN
 * @see FloatToDoubleFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingFloatToDoubleFunction1<H extends AbstractThrowingFloatToDoubleFunction1.Handler> extends AbstractThrowingToDoubleFunctionN<H>, InputChainableInput<Float>, UnboxedAll<AbstractThrowingFunction1<Float, Double, ?>, AbstractThrowingToDoubleFunction1<Float, ?>, AbstractThrowingFloatFunction1<Double, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    double applyAsDouble(final float value) throws Throwable;

    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) throws Throwable {
        return this.applyAsDouble((float) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Float, Double, ?> box();

    /**
     * @see AbstractThrowingToDoubleFunction1
     */
    @Override
    AbstractThrowingToDoubleFunction1<Float, ?> boxInput();

    /**
     * @see AbstractThrowingFloatFunction1
     */
    @Override
    AbstractThrowingFloatFunction1<Double, ?> boxResult();

    /**
     * @see FloatToDoubleFunction1
     */
    @Override
    default FloatToDoubleFunction1 handle(final H handler) {
        return (final float value) -> {
            try {
                return this.applyAsDouble(value);
            } catch (final Throwable t) {
                return handler.onThrownAsDouble(t, value);
            }
        };
    }

    /**
     * @see FloatToDoubleFunction1
     */
    @Override
    FloatToDoubleFunction1 swallow();

    /**
     * @see AbstractThrowingToDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToDoubleFunction1<V, ?> compose(final Function1<? super V, ? extends Float> before) {
        return (AbstractThrowingToDoubleFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToDoubleFunction1
     */
    @Override
    <V> AbstractThrowingToDoubleFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Float> before);

    /**
     * @see AbstractThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFloatFunction1<V, ?> andThen(final Function1<? super Double, ? extends V> after) {
        return (AbstractThrowingFloatFunction1<V, ?>) AbstractThrowingToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFloatFunction1
     */
    @Override
    <V> AbstractThrowingFloatFunction1<V, ?> andThenUnchecked(final Function1<? super Double, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToDoubleFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the function argument
         * @return the handled result
         */
        double onThrownAsDouble(final Throwable t, final float value);

        @Override
        default double onThrownAsDoubleUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsDouble(t, (float) args[0]);
        }
    }
}
