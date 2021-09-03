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
import net.ashwork.functionality.primitive.combined.LongToShortFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingLongFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a {@code short}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToShortFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link AbstractThrowingToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link AbstractThrowingLongFunction1}.
 * This is the throwing variation of {@link LongToShortFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingLongFunction1
 * @see AbstractThrowingToShortFunction1
 * @see AbstractThrowingToShortFunctionN
 * @see LongToShortFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingLongToShortFunction1<H extends AbstractThrowingLongToShortFunction1.Handler> extends AbstractThrowingToShortFunctionN<H>, InputChainableInput<Long>, UnboxedAll<AbstractThrowingFunction1<Long, Short, ?>, AbstractThrowingToShortFunction1<Long, ?>, AbstractThrowingLongFunction1<Short, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    short applyAsShort(final long value) throws Throwable;

    @Override
    default short applyAllAsShortUnchecked(final Object... args) throws Throwable {
        return this.applyAsShort((long) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Long, Short, ?> box();

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @Override
    AbstractThrowingToShortFunction1<Long, ?> boxInput();

    /**
     * @see AbstractThrowingLongFunction1
     */
    @Override
    AbstractThrowingLongFunction1<Short, ?> boxResult();

    /**
     * @see LongToShortFunction1
     */
    @Override
    default LongToShortFunction1 handle(final H handler) {
        return (final long value) -> {
            try {
                return this.applyAsShort(value);
            } catch (final Throwable t) {
                return handler.onThrownAsShort(t, value);
            }
        };
    }

    /**
     * @see LongToShortFunction1
     */
    @Override
    LongToShortFunction1 swallow();

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToShortFunction1<V, ?> compose(final Function1<? super V, ? extends Long> before) {
        return (AbstractThrowingToShortFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @Override
    <V> AbstractThrowingToShortFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Long> before);

    /**
     * @see AbstractThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingLongFunction1<V, ?> andThen(final Function1<? super Short, ? extends V> after) {
        return (AbstractThrowingLongFunction1<V, ?>) AbstractThrowingToShortFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingLongFunction1
     */
    @Override
    <V> AbstractThrowingLongFunction1<V, ?> andThenUnchecked(final Function1<? super Short, ? extends V> after);

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
        short onThrownAsShort(final Throwable t, final long value);

        @Override
        default short onThrownAsShortUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsShort(t, (long) args[0]);
        }
    }
}
