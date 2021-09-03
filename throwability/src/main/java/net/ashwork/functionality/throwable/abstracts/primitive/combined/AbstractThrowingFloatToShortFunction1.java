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
import net.ashwork.functionality.primitive.combined.FloatToShortFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingFloatFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code short}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToShortFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link AbstractThrowingToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link AbstractThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatToShortFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFloatFunction1
 * @see AbstractThrowingToShortFunction1
 * @see AbstractThrowingToShortFunctionN
 * @see FloatToShortFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingFloatToShortFunction1<H extends AbstractThrowingFloatToShortFunction1.Handler> extends AbstractThrowingToShortFunctionN<H>, InputChainableInput<Float>, UnboxedAll<AbstractThrowingFunction1<Float, Short, ?>, AbstractThrowingToShortFunction1<Float, ?>, AbstractThrowingFloatFunction1<Short, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    short applyAsShort(final float value) throws Throwable;

    @Override
    default short applyAllAsShortUnchecked(final Object... args) throws Throwable {
        return this.applyAsShort((float) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Float, Short, ?> box();

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @Override
    AbstractThrowingToShortFunction1<Float, ?> boxInput();

    /**
     * @see AbstractThrowingFloatFunction1
     */
    @Override
    AbstractThrowingFloatFunction1<Short, ?> boxResult();

    /**
     * @see FloatToShortFunction1
     */
    @Override
    default FloatToShortFunction1 handle(final H handler) {
        return (final float value) -> {
            try {
                return this.applyAsShort(value);
            } catch (final Throwable t) {
                return handler.onThrownAsShort(t, value);
            }
        };
    }

    /**
     * @see FloatToShortFunction1
     */
    @Override
    FloatToShortFunction1 swallow();

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToShortFunction1<V, ?> compose(final Function1<? super V, ? extends Float> before) {
        return (AbstractThrowingToShortFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @Override
    <V> AbstractThrowingToShortFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Float> before);

    /**
     * @see AbstractThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFloatFunction1<V, ?> andThen(final Function1<? super Short, ? extends V> after) {
        return (AbstractThrowingFloatFunction1<V, ?>) AbstractThrowingToShortFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFloatFunction1
     */
    @Override
    <V> AbstractThrowingFloatFunction1<V, ?> andThenUnchecked(final Function1<? super Short, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToShortFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the function argument
         * @return the handled result
         */
        short onThrownAsShort(final Throwable t, final float value);

        @Override
        default short onThrownAsShortUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsShort(t, (float) args[0]);
        }
    }
}
