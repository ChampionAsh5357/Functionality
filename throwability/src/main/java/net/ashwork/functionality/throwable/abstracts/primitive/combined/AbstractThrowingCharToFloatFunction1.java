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
import net.ashwork.functionality.primitive.combined.CharToFloatFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingToFloatFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingToFloatFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingCharFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a {@code float}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToFloatFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link AbstractThrowingToFloatFunction1}.
 * This is the {@code float}-producing primitive specialization of {@link AbstractThrowingCharFunction1}.
 * This is the throwing variation of {@link CharToFloatFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingCharFunction1
 * @see AbstractThrowingToFloatFunction1
 * @see AbstractThrowingToFloatFunctionN
 * @see CharToFloatFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingCharToFloatFunction1<H extends AbstractThrowingCharToFloatFunction1.Handler> extends AbstractThrowingToFloatFunctionN<H>, InputChainableInput<Character>, UnboxedAll<AbstractThrowingFunction1<Character, Float, ?>, AbstractThrowingToFloatFunction1<Character, ?>, AbstractThrowingCharFunction1<Float, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    float applyAsFloat(final char value) throws Throwable;

    @Override
    default float applyAllAsFloatUnchecked(final Object... args) throws Throwable {
        return this.applyAsFloat((char) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Character, Float, ?> box();

    /**
     * @see AbstractThrowingToFloatFunction1
     */
    @Override
    AbstractThrowingToFloatFunction1<Character, ?> boxInput();

    /**
     * @see AbstractThrowingCharFunction1
     */
    @Override
    AbstractThrowingCharFunction1<Float, ?> boxResult();

    /**
     * @see CharToFloatFunction1
     */
    @Override
    default CharToFloatFunction1 handle(final H handler) {
        return (final char value) -> {
            try {
                return this.applyAsFloat(value);
            } catch (final Throwable t) {
                return handler.onThrownAsFloat(t, value);
            }
        };
    }

    /**
     * @see CharToFloatFunction1
     */
    @Override
    CharToFloatFunction1 swallow();

    /**
     * @see AbstractThrowingToFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToFloatFunction1<V, ?> compose(final Function1<? super V, ? extends Character> before) {
        return (AbstractThrowingToFloatFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToFloatFunction1
     */
    @Override
    <V> AbstractThrowingToFloatFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Character> before);

    /**
     * @see AbstractThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingCharFunction1<V, ?> andThen(final Function1<? super Float, ? extends V> after) {
        return (AbstractThrowingCharFunction1<V, ?>) AbstractThrowingToFloatFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingCharFunction1
     */
    @Override
    <V> AbstractThrowingCharFunction1<V, ?> andThenUnchecked(final Function1<? super Float, ? extends V> after);

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
        float onThrownAsFloat(final Throwable t, final char value);

        @Override
        default float onThrownAsFloatUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsFloat(t, (char) args[0]);
        }
    }
}
