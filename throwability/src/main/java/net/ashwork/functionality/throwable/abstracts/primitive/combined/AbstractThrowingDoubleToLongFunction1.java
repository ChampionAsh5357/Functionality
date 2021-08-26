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
import net.ashwork.functionality.primitive.combined.DoubleToLongFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingDoubleFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code double}-valued argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToLongFunctionN}.
 * This is the {@code double}-consuming primitive specialization of {@link AbstractThrowingToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link AbstractThrowingDoubleFunction1}.
 * This is the throwing variation of {@link DoubleToLongFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingDoubleFunction1
 * @see AbstractThrowingToLongFunction1
 * @see AbstractThrowingToLongFunctionN
 * @see DoubleToLongFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingDoubleToLongFunction1<H extends AbstractThrowingDoubleToLongFunction1.Handler> extends AbstractThrowingToLongFunctionN<H>, InputChainableInput<Double>, UnboxedAll<AbstractThrowingFunction1<Double, Long, ?>, AbstractThrowingToLongFunction1<Double, ?>, AbstractThrowingDoubleFunction1<Long, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    long applyAsLong(final double value) throws Throwable;

    @Override
    default long applyAllAsLongUnchecked(final Object... args) throws Throwable {
        return this.applyAsLong((double) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Double, Long, ?> box();

    /**
     * @see AbstractThrowingToLongFunction1
     */
    @Override
    AbstractThrowingToLongFunction1<Double, ?> boxInput();

    /**
     * @see AbstractThrowingDoubleFunction1
     */
    @Override
    AbstractThrowingDoubleFunction1<Long, ?> boxResult();

    /**
     * @see DoubleToLongFunction1
     */
    @Override
    default DoubleToLongFunction1 handle(final H handler) {
        return (final double value) -> {
            try {
                return this.applyAsLong(value);
            } catch (final Throwable t) {
                return handler.onThrownAsLong(t, value);
            }
        };
    }

    /**
     * @see DoubleToLongFunction1
     */
    @Override
    DoubleToLongFunction1 swallow();

    /**
     * @see AbstractThrowingToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToLongFunction1<V, ?> compose(final Function1<? super V, ? extends Double> before) {
        return (AbstractThrowingToLongFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToLongFunction1
     */
    @Override
    <V> AbstractThrowingToLongFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Double> before);

    /**
     * @see AbstractThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingDoubleFunction1<V, ?> andThen(final Function1<? super Long, ? extends V> after) {
        return (AbstractThrowingDoubleFunction1<V, ?>) AbstractThrowingToLongFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingDoubleFunction1
     */
    @Override
    <V> AbstractThrowingDoubleFunction1<V, ?> andThenUnchecked(final Function1<? super Long, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToLongFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the function argument
         * @return the handled result
         */
        long onThrownAsLong(final Throwable t, final double value);

        @Override
        default long onThrownAsLongUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsLong(t, (double) args[0]);
        }
    }
}
