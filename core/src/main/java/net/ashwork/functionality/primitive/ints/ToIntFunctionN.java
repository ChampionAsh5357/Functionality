/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.FunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces an {@code int}-valued result.
 * This is the {@code int}-producing primitive specialization for {@link FunctionN}.
 * All {@code int}-producing functions are derived from this {@code n}-arity specialization.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsIntUnchecked(Object...)}.
 *
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToIntFunctionN extends FunctionN<Integer> {

    /**
     * Applies this function to the given arguments. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     */
    int applyAllAsIntUnchecked(final Object... args);

    @Override
    default Integer applyAllUnchecked(final Object... args) {
        return this.applyAllAsIntUnchecked(args);
    }

    /**
     * An instance of {@link ToIntFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToIntFunctionN
     */
    class Instance implements ToIntFunctionN {

        private final int arity;
        private final ToIntFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ToIntFunction1<Object[]> function) {
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public int applyAllAsIntUnchecked(final Object... args) {
            return this.function.applyAsInt(args);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> FunctionN.Instance<V> andThen(Function1<? super Integer, ? extends V> after) {
            return (FunctionN.Instance<V>) ToIntFunctionN.super.andThen(after);
        }

        @Override
        public <V> FunctionN.Instance<V> andThenUnchecked(Function1<? super Integer, ? extends V> after) {
            return new FunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsInt(args)));
        }
    }
}
