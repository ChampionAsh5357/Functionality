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
import net.ashwork.functionality.primitive.combined.ByteToCharFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingToCharFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingToCharFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingByteFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a {@code char}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToCharFunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link AbstractThrowingToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link AbstractThrowingByteFunction1}.
 * This is the throwing variation of {@link ByteToCharFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingByteFunction1
 * @see AbstractThrowingToCharFunction1
 * @see AbstractThrowingToCharFunctionN
 * @see ByteToCharFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingByteToCharFunction1<H extends AbstractThrowingByteToCharFunction1.Handler> extends AbstractThrowingToCharFunctionN<H>, InputChainableInput<Byte>, UnboxedAll<AbstractThrowingFunction1<Byte, Character, ?>, AbstractThrowingToCharFunction1<Byte, ?>, AbstractThrowingByteFunction1<Character, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    char applyAsChar(final byte value) throws Throwable;

    @Override
    default char applyAllAsCharUnchecked(final Object... args) throws Throwable {
        return this.applyAsChar((byte) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Byte, Character, ?> box();

    /**
     * @see AbstractThrowingToCharFunction1
     */
    @Override
    AbstractThrowingToCharFunction1<Byte, ?> boxInput();

    /**
     * @see AbstractThrowingByteFunction1
     */
    @Override
    AbstractThrowingByteFunction1<Character, ?> boxResult();

    /**
     * @see ByteToCharFunction1
     */
    @Override
    default ByteToCharFunction1 handle(final H handler) {
        return (final byte value) -> {
            try {
                return this.applyAsChar(value);
            } catch (final Throwable t) {
                return handler.onThrownAsChar(t, value);
            }
        };
    }

    /**
     * @see ByteToCharFunction1
     */
    @Override
    ByteToCharFunction1 swallow();

    /**
     * @see AbstractThrowingToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToCharFunction1<V, ?> compose(final Function1<? super V, ? extends Byte> before) {
        return (AbstractThrowingToCharFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToCharFunction1
     */
    @Override
    <V> AbstractThrowingToCharFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Byte> before);

    /**
     * @see AbstractThrowingByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingByteFunction1<V, ?> andThen(final Function1<? super Character, ? extends V> after) {
        return (AbstractThrowingByteFunction1<V, ?>) AbstractThrowingToCharFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingByteFunction1
     */
    @Override
    <V> AbstractThrowingByteFunction1<V, ?> andThenUnchecked(final Function1<? super Character, ? extends V> after);

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
        char onThrownAsChar(final Throwable t, final byte value);

        @Override
        default char onThrownAsCharUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsChar(t, (byte) args[0]);
        }
    }
}
