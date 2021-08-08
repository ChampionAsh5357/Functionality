/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.FunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code char}-valued result.
 * This is the {@code char}-producing primitive specialization for {@link FunctionN}.
 * All {@code char}-producing functions are derived from this {@code n}-arity specialization.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsCharUnchecked(Object...)}.
 *
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToCharFunctionN extends FunctionN<Character> {

    /**
     * Applies this function to the given arguments. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     */
    char applyAllAsCharUnchecked(final Object... args);

    @Override
    default Character applyAllUnchecked(final Object... args) {
        return this.applyAllAsCharUnchecked(args);
    }

    /**
     * An instance of {@link ToCharFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToCharFunctionN
     */
    class Instance implements ToCharFunctionN {

        private final int arity;
        private final ToCharFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ToCharFunction1<Object[]> function) {
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public char applyAllAsCharUnchecked(final Object... args) {
            return this.function.applyAsChar(args);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> FunctionN.Instance<V> andThen(Function1<? super Character, ? extends V> after) {
            return (FunctionN.Instance<V>) ToCharFunctionN.super.andThen(after);
        }

        @Override
        public <V> FunctionN.Instance<V> andThenUnchecked(Function1<? super Character, ? extends V> after) {
            return new FunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsChar(args)));
        }
    }
}
