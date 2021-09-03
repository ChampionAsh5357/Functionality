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
import net.ashwork.functionality.primitive.combined.LongToCharFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingToCharFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingToCharFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingLongFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a {@code char}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToCharFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link AbstractThrowingToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link AbstractThrowingLongFunction1}.
 * This is the throwing variation of {@link LongToCharFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingLongFunction1
 * @see AbstractThrowingToCharFunction1
 * @see AbstractThrowingToCharFunctionN
 * @see LongToCharFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingLongToCharFunction1<H extends AbstractThrowingLongToCharFunction1.Handler> extends AbstractThrowingToCharFunctionN<H>, InputChainableInput<Long>, UnboxedAll<AbstractThrowingFunction1<Long, Character, ?>, AbstractThrowingToCharFunction1<Long, ?>, AbstractThrowingLongFunction1<Character, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    char applyAsChar(final long value) throws Throwable;

    @Override
    default char applyAllAsCharUnchecked(final Object... args) throws Throwable {
        return this.applyAsChar((long) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Long, Character, ?> box();

    /**
     * @see AbstractThrowingToCharFunction1
     */
    @Override
    AbstractThrowingToCharFunction1<Long, ?> boxInput();

    /**
     * @see AbstractThrowingLongFunction1
     */
    @Override
    AbstractThrowingLongFunction1<Character, ?> boxResult();

    /**
     * @see LongToCharFunction1
     */
    @Override
    default LongToCharFunction1 handle(final H handler) {
        return (final long value) -> {
            try {
                return this.applyAsChar(value);
            } catch (final Throwable t) {
                return handler.onThrownAsChar(t, value);
            }
        };
    }

    /**
     * @see LongToCharFunction1
     */
    @Override
    LongToCharFunction1 swallow();

    /**
     * @see AbstractThrowingToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToCharFunction1<V, ?> compose(final Function1<? super V, ? extends Long> before) {
        return (AbstractThrowingToCharFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToCharFunction1
     */
    @Override
    <V> AbstractThrowingToCharFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Long> before);

    /**
     * @see AbstractThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingLongFunction1<V, ?> andThen(final Function1<? super Character, ? extends V> after) {
        return (AbstractThrowingLongFunction1<V, ?>) AbstractThrowingToCharFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingLongFunction1
     */
    @Override
    <V> AbstractThrowingLongFunction1<V, ?> andThenUnchecked(final Function1<? super Character, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToCharFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the function argument
         * @return the handled result
         */
        char onThrownAsChar(final Throwable t, final long value);

        @Override
        default char onThrownAsCharUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsChar(t, (long) args[0]);
        }
    }
}
