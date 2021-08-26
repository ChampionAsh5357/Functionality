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
import net.ashwork.functionality.primitive.combined.IntToFloatFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingToFloatFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingToFloatFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingIntFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code float}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToFloatFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link AbstractThrowingToFloatFunction1}.
 * This is the {@code float}-producing primitive specialization of {@link AbstractThrowingIntFunction1}.
 * This is the throwing variation of {@link IntToFloatFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingIntFunction1
 * @see AbstractThrowingToFloatFunction1
 * @see AbstractThrowingToFloatFunctionN
 * @see IntToFloatFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingIntToFloatFunction1<H extends AbstractThrowingIntToFloatFunction1.Handler> extends AbstractThrowingToFloatFunctionN<H>, InputChainableInput<Integer>, UnboxedAll<AbstractThrowingFunction1<Integer, Float, ?>, AbstractThrowingToFloatFunction1<Integer, ?>, AbstractThrowingIntFunction1<Float, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    float applyAsFloat(final int value) throws Throwable;

    @Override
    default float applyAllAsFloatUnchecked(final Object... args) throws Throwable {
        return this.applyAsFloat((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Integer, Float, ?> box();

    /**
     * @see AbstractThrowingToFloatFunction1
     */
    @Override
    AbstractThrowingToFloatFunction1<Integer, ?> boxInput();

    /**
     * @see AbstractThrowingIntFunction1
     */
    @Override
    AbstractThrowingIntFunction1<Float, ?> boxResult();

    /**
     * @see IntToFloatFunction1
     */
    @Override
    default IntToFloatFunction1 handle(final H handler) {
        return (final int value) -> {
            try {
                return this.applyAsFloat(value);
            } catch (final Throwable t) {
                return handler.onThrownAsFloat(t, value);
            }
        };
    }

    /**
     * @see IntToFloatFunction1
     */
    @Override
    IntToFloatFunction1 swallow();

    /**
     * @see AbstractThrowingToFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToFloatFunction1<V, ?> compose(final Function1<? super V, ? extends Integer> before) {
        return (AbstractThrowingToFloatFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToFloatFunction1
     */
    @Override
    <V> AbstractThrowingToFloatFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Integer> before);

    /**
     * @see AbstractThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingIntFunction1<V, ?> andThen(final Function1<? super Float, ? extends V> after) {
        return (AbstractThrowingIntFunction1<V, ?>) AbstractThrowingToFloatFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingIntFunction1
     */
    @Override
    <V> AbstractThrowingIntFunction1<V, ?> andThenUnchecked(final Function1<? super Float, ? extends V> after);

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
         * @param value the function argument
         * @return the handled result
         */
        float onThrownAsFloat(final Throwable t, final int value);

        @Override
        default float onThrownAsFloatUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsFloat(t, (int) args[0]);
        }
    }
}
