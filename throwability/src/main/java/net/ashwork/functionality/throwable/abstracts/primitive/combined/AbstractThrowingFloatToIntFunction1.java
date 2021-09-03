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
import net.ashwork.functionality.primitive.combined.FloatToIntFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingFloatFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces an {@code int}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToIntFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link AbstractThrowingToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link AbstractThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatToIntFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFloatFunction1
 * @see AbstractThrowingToIntFunction1
 * @see AbstractThrowingToIntFunctionN
 * @see FloatToIntFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingFloatToIntFunction1<H extends AbstractThrowingFloatToIntFunction1.Handler> extends AbstractThrowingToIntFunctionN<H>, InputChainableInput<Float>, UnboxedAll<AbstractThrowingFunction1<Float, Integer, ?>, AbstractThrowingToIntFunction1<Float, ?>, AbstractThrowingFloatFunction1<Integer, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    int applyAsInt(final float value) throws Throwable;

    @Override
    default int applyAllAsIntUnchecked(final Object... args) throws Throwable {
        return this.applyAsInt((float) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Float, Integer, ?> box();

    /**
     * @see AbstractThrowingToIntFunction1
     */
    @Override
    AbstractThrowingToIntFunction1<Float, ?> boxInput();

    /**
     * @see AbstractThrowingFloatFunction1
     */
    @Override
    AbstractThrowingFloatFunction1<Integer, ?> boxResult();

    /**
     * @see FloatToIntFunction1
     */
    @Override
    default FloatToIntFunction1 handle(final H handler) {
        return (final float value) -> {
            try {
                return this.applyAsInt(value);
            } catch (final Throwable t) {
                return handler.onThrownAsInt(t, value);
            }
        };
    }

    /**
     * @see FloatToIntFunction1
     */
    @Override
    FloatToIntFunction1 swallow();

    /**
     * @see AbstractThrowingToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToIntFunction1<V, ?> compose(final Function1<? super V, ? extends Float> before) {
        return (AbstractThrowingToIntFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToIntFunction1
     */
    @Override
    <V> AbstractThrowingToIntFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Float> before);

    /**
     * @see AbstractThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFloatFunction1<V, ?> andThen(final Function1<? super Integer, ? extends V> after) {
        return (AbstractThrowingFloatFunction1<V, ?>) AbstractThrowingToIntFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFloatFunction1
     */
    @Override
    <V> AbstractThrowingFloatFunction1<V, ?> andThenUnchecked(final Function1<? super Integer, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToIntFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the function argument
         * @return the handled result
         */
        int onThrownAsInt(final Throwable t, final float value);

        @Override
        default int onThrownAsIntUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsInt(t, (float) args[0]);
        }
    }
}
