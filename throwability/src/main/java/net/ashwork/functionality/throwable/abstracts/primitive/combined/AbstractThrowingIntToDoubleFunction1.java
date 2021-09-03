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
import net.ashwork.functionality.primitive.combined.IntToDoubleFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingToDoubleFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingToDoubleFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingIntFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code double}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToDoubleFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link AbstractThrowingToDoubleFunction1}.
 * This is the {@code double}-producing primitive specialization of {@link AbstractThrowingIntFunction1}.
 * This is the throwing variation of {@link IntToDoubleFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingIntFunction1
 * @see AbstractThrowingToDoubleFunction1
 * @see AbstractThrowingToDoubleFunctionN
 * @see IntToDoubleFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingIntToDoubleFunction1<H extends AbstractThrowingIntToDoubleFunction1.Handler> extends AbstractThrowingToDoubleFunctionN<H>, InputChainableInput<Integer>, UnboxedAll<AbstractThrowingFunction1<Integer, Double, ?>, AbstractThrowingToDoubleFunction1<Integer, ?>, AbstractThrowingIntFunction1<Double, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    double applyAsDouble(final int value) throws Throwable;

    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) throws Throwable {
        return this.applyAsDouble((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Integer, Double, ?> box();

    /**
     * @see AbstractThrowingToDoubleFunction1
     */
    @Override
    AbstractThrowingToDoubleFunction1<Integer, ?> boxInput();

    /**
     * @see AbstractThrowingIntFunction1
     */
    @Override
    AbstractThrowingIntFunction1<Double, ?> boxResult();

    /**
     * @see IntToDoubleFunction1
     */
    @Override
    default IntToDoubleFunction1 handle(final H handler) {
        return (final int value) -> {
            try {
                return this.applyAsDouble(value);
            } catch (final Throwable t) {
                return handler.onThrownAsDouble(t, value);
            }
        };
    }

    /**
     * @see IntToDoubleFunction1
     */
    @Override
    IntToDoubleFunction1 swallow();

    /**
     * @see AbstractThrowingToDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToDoubleFunction1<V, ?> compose(final Function1<? super V, ? extends Integer> before) {
        return (AbstractThrowingToDoubleFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToDoubleFunction1
     */
    @Override
    <V> AbstractThrowingToDoubleFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Integer> before);

    /**
     * @see AbstractThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingIntFunction1<V, ?> andThen(final Function1<? super Double, ? extends V> after) {
        return (AbstractThrowingIntFunction1<V, ?>) AbstractThrowingToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingIntFunction1
     */
    @Override
    <V> AbstractThrowingIntFunction1<V, ?> andThenUnchecked(final Function1<? super Double, ? extends V> after);

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
        double onThrownAsDouble(final Throwable t, final int value);

        @Override
        default double onThrownAsDoubleUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsDouble(t, (int) args[0]);
        }
    }
}
