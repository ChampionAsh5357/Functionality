/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.FunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code double}-valued result.
 * This is the {@code double}-producing primitive specialization for {@link FunctionN}.
 * All {@code double}-producing functions are derived from this {@code n}-arity specialization.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsDoubleUnchecked(Object...)}.
 *
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToDoubleFunctionN extends FunctionN<Double> {

    /**
     * Applies this function to the given arguments. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     */
    double applyAllAsDoubleUnchecked(final Object... args);

    @Override
    default Double applyAllUnchecked(final Object... args) {
        return this.applyAllAsDoubleUnchecked(args);
    }

    /**
     * An instance of {@link ToDoubleFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToDoubleFunctionN
     */
    class Instance implements ToDoubleFunctionN {

        private final int arity;
        private final ToDoubleFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ToDoubleFunction1<Object[]> function) {
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public double applyAllAsDoubleUnchecked(final Object... args) {
            return this.function.applyAsDouble(args);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> FunctionN.Instance<V> andThen(Function1<? super Double, ? extends V> after) {
            return (FunctionN.Instance<V>) ToDoubleFunctionN.super.andThen(after);
        }

        @Override
        public <V> FunctionN.Instance<V> andThenUnchecked(Function1<? super Double, ? extends V> after) {
            return new FunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsDouble(args)));
        }
    }
}
