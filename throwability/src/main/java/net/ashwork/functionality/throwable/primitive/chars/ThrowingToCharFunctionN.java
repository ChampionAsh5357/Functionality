/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.chars.ToCharFunctionN;
import net.ashwork.functionality.throwable.ThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingToCharFunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code char}-valued result or throws a throwable.
 * This is the {@code char}-producing primitive specialization for {@link ThrowingFunctionN}.
 * All {@code char}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToCharFunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsCharUnchecked(Object...)}.
 *
 * @see ThrowingFunctionN
 * @see ToCharFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToCharFunctionN extends AbstractThrowingToCharFunctionN<AbstractThrowingToCharFunctionN.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToCharFunctionN
     */
    static ThrowingToCharFunctionN from(final ToCharFunctionN function) {
        return function::applyAllAsCharUnchecked;
    }

    @Override
    default ToCharFunctionN swallow() {
        return this.handle((t, args) -> '\u0000');
    }

    /**
     * @see ThrowingFunctionN
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingToCharFunctionN.super.andThen(after);
    }

    /**
     * @see ThrowingFunctionN
     */
    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.applyAllAsCharUnchecked(args));
    }

    /**
     * An instance of {@link ToCharFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToCharFunctionN
     */
    class Instance implements AbstractThrowingToCharFunctionN<Handler> {

        private final int arity;
        private final ThrowingToCharFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param function the function instance to be applied
         */
        public Instance(final ToCharFunctionN.Instance function) {
            this(function.arity(), function::applyAllAsCharUnchecked);
        }

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ThrowingToCharFunction1<Object[]> function) {
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public char applyAllAsCharUnchecked(Object... args) throws Throwable {
            return this.function.applyAsChar(args);
        }

        /**
         * @see ToCharFunctionN.Instance
         */
        @Override
        public ToCharFunctionN.Instance handle(Handler handler) {
            return new ToCharFunctionN.Instance(this.arity(), (final Object[] args) -> {
                try {
                    return this.applyAllAsCharUnchecked(args);
                } catch (final Throwable t) {
                    return handler.onThrownAsCharUnchecked(t, args);
                }
            });
        }

        /**
         * @see ToCharFunctionN.Instance
         */
        @Override
        public ToCharFunctionN.Instance swallow() {
            return this.handle((t, args) -> '\u0000');
        }

        /**
         * @see ThrowingFunctionN.Instance
         */
        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThen(Function1<? super Character, ? extends V> after) {
            return (ThrowingFunctionN.Instance<V>) AbstractThrowingToCharFunctionN.super.andThen(after);
        }

        /**
         * @see ThrowingFunctionN.Instance
         */
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThenUnchecked(Function1<? super Character, ? extends V> after) {
            return new ThrowingFunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsChar(args)));
        }
    }
}
