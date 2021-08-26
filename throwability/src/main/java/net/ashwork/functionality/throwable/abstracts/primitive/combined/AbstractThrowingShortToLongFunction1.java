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
import net.ashwork.functionality.primitive.combined.ShortToLongFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingShortFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code short}-valued argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToLongFunctionN}.
 * This is the {@code short}-consuming primitive specialization of {@link AbstractThrowingToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link AbstractThrowingShortFunction1}.
 * This is the throwing variation of {@link ShortToLongFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingShortFunction1
 * @see AbstractThrowingToLongFunction1
 * @see AbstractThrowingToLongFunctionN
 * @see ShortToLongFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingShortToLongFunction1<H extends AbstractThrowingShortToLongFunction1.Handler> extends AbstractThrowingToLongFunctionN<H>, InputChainableInput<Short>, UnboxedAll<AbstractThrowingFunction1<Short, Long, ?>, AbstractThrowingToLongFunction1<Short, ?>, AbstractThrowingShortFunction1<Long, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    long applyAsLong(final short value) throws Throwable;

    @Override
    default long applyAllAsLongUnchecked(final Object... args) throws Throwable {
        return this.applyAsLong((short) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Short, Long, ?> box();

    /**
     * @see AbstractThrowingToLongFunction1
     */
    @Override
    AbstractThrowingToLongFunction1<Short, ?> boxInput();

    /**
     * @see AbstractThrowingShortFunction1
     */
    @Override
    AbstractThrowingShortFunction1<Long, ?> boxResult();

    /**
     * @see ShortToLongFunction1
     */
    @Override
    default ShortToLongFunction1 handle(final H handler) {
        return (final short value) -> {
            try {
                return this.applyAsLong(value);
            } catch (final Throwable t) {
                return handler.onThrownAsLong(t, value);
            }
        };
    }

    /**
     * @see ShortToLongFunction1
     */
    @Override
    ShortToLongFunction1 swallow();

    /**
     * @see AbstractThrowingToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToLongFunction1<V, ?> compose(final Function1<? super V, ? extends Short> before) {
        return (AbstractThrowingToLongFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToLongFunction1
     */
    @Override
    <V> AbstractThrowingToLongFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Short> before);

    /**
     * @see AbstractThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingShortFunction1<V, ?> andThen(final Function1<? super Long, ? extends V> after) {
        return (AbstractThrowingShortFunction1<V, ?>) AbstractThrowingToLongFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingShortFunction1
     */
    @Override
    <V> AbstractThrowingShortFunction1<V, ?> andThenUnchecked(final Function1<? super Long, ? extends V> after);

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
        long onThrownAsLong(final Throwable t, final short value);

        @Override
        default long onThrownAsLongUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsLong(t, (short) args[0]);
        }
    }
}
