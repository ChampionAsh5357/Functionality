/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.FunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code float}-valued result.
 * This is the {@code float}-producing primitive specialization for {@link FunctionN}.
 * All {@code float}-producing functions are derived from this {@code n}-arity specialization.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsFloatUnchecked(Object...)}.
 *
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToFloatFunctionN extends FunctionN<Float> {

    /**
     * Applies this function to the given arguments. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     */
    float applyAllAsFloatUnchecked(final Object... args);

    @Override
    default Float applyAllUnchecked(final Object... args) {
        return this.applyAllAsFloatUnchecked(args);
    }

    /**
     * An instance of {@link ToFloatFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToFloatFunctionN
     */
    class Instance implements ToFloatFunctionN {

        private final int arity;
        private final ToFloatFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ToFloatFunction1<Object[]> function) {
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public float applyAllAsFloatUnchecked(final Object... args) {
            return this.function.applyAsFloat(args);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> FunctionN.Instance<V> andThen(Function1<? super Float, ? extends V> after) {
            return (FunctionN.Instance<V>) ToFloatFunctionN.super.andThen(after);
        }

        @Override
        public <V> FunctionN.Instance<V> andThenUnchecked(Function1<? super Float, ? extends V> after) {
            return new FunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsFloat(args)));
        }
    }
}
