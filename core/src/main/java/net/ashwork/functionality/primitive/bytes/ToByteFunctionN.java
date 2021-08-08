/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.FunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code byte}-valued result.
 * This is the {@code byte}-producing primitive specialization for {@link FunctionN}.
 * All {@code byte}-producing functions are derived from this {@code n}-arity specialization.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsByteUnchecked(Object...)}.
 *
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToByteFunctionN extends FunctionN<Byte> {

    /**
     * Applies this function to the given arguments. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     */
    byte applyAllAsByteUnchecked(final Object... args);

    @Override
    default Byte applyAllUnchecked(final Object... args) {
        return this.applyAllAsByteUnchecked(args);
    }

    /**
     * An instance of {@link ToByteFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToByteFunctionN
     */
    class Instance implements ToByteFunctionN {

        private final int arity;
        private final ToByteFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ToByteFunction1<Object[]> function) {
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public byte applyAllAsByteUnchecked(final Object... args) {
            return this.function.applyAsByte(args);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> FunctionN.Instance<V> andThen(Function1<? super Byte, ? extends V> after) {
            return (FunctionN.Instance<V>) ToByteFunctionN.super.andThen(after);
        }

        @Override
        public <V> FunctionN.Instance<V> andThenUnchecked(Function1<? super Byte, ? extends V> after) {
            return new FunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsByte(args)));
        }
    }
}
