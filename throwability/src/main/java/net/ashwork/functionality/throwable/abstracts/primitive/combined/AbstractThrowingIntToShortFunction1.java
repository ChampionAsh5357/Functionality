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
import net.ashwork.functionality.primitive.combined.IntToShortFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingIntFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code short}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToShortFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link AbstractThrowingToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link AbstractThrowingIntFunction1}.
 * This is the throwing variation of {@link IntToShortFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingIntFunction1
 * @see AbstractThrowingToShortFunction1
 * @see AbstractThrowingToShortFunctionN
 * @see IntToShortFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingIntToShortFunction1<H extends AbstractThrowingIntToShortFunction1.Handler> extends AbstractThrowingToShortFunctionN<H>, InputChainableInput<Integer>, UnboxedAll<AbstractThrowingFunction1<Integer, Short, ?>, AbstractThrowingToShortFunction1<Integer, ?>, AbstractThrowingIntFunction1<Short, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    short applyAsShort(final int value) throws Throwable;

    @Override
    default short applyAllAsShortUnchecked(final Object... args) throws Throwable {
        return this.applyAsShort((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Integer, Short, ?> box();

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @Override
    AbstractThrowingToShortFunction1<Integer, ?> boxInput();

    /**
     * @see AbstractThrowingIntFunction1
     */
    @Override
    AbstractThrowingIntFunction1<Short, ?> boxResult();

    /**
     * @see IntToShortFunction1
     */
    @Override
    default IntToShortFunction1 handle(final H handler) {
        return (final int value) -> {
            try {
                return this.applyAsShort(value);
            } catch (final Throwable t) {
                return handler.onThrownAsShort(t, value);
            }
        };
    }

    /**
     * @see IntToShortFunction1
     */
    @Override
    IntToShortFunction1 swallow();

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToShortFunction1<V, ?> compose(final Function1<? super V, ? extends Integer> before) {
        return (AbstractThrowingToShortFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @Override
    <V> AbstractThrowingToShortFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Integer> before);

    /**
     * @see AbstractThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingIntFunction1<V, ?> andThen(final Function1<? super Short, ? extends V> after) {
        return (AbstractThrowingIntFunction1<V, ?>) AbstractThrowingToShortFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingIntFunction1
     */
    @Override
    <V> AbstractThrowingIntFunction1<V, ?> andThenUnchecked(final Function1<? super Short, ? extends V> after);

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
        short onThrownAsShort(final Throwable t, final int value);

        @Override
        default short onThrownAsShortUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsShort(t, (int) args[0]);
        }
    }
}
