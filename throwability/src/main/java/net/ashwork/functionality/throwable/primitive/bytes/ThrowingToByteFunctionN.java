/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.bytes.ToByteFunctionN;
import net.ashwork.functionality.throwable.ThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code byte}-valued result or throws a throwable.
 * This is the {@code byte}-producing primitive specialization for {@link ThrowingFunctionN}.
 * All {@code byte}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToByteFunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsByteUnchecked(Object...)}.
 *
 * @see ThrowingFunctionN
 * @see ToByteFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToByteFunctionN extends AbstractThrowingToByteFunctionN<AbstractThrowingToByteFunctionN.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToByteFunctionN
     */
    static ThrowingToByteFunctionN from(final ToByteFunctionN function) {
        return function::applyAllAsByteUnchecked;
    }

    @Override
    default ToByteFunctionN swallow() {
        return this.handle((t, args) -> (byte) 0);
    }

    /**
     * @see ThrowingFunctionN
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingToByteFunctionN.super.andThen(after);
    }

    /**
     * @see ThrowingFunctionN
     */
    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.applyAllAsByteUnchecked(args));
    }

    /**
     * An instance of {@link ToByteFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToByteFunctionN
     */
    class Instance implements AbstractThrowingToByteFunctionN<Handler> {

        private final int arity;
        private final ThrowingToByteFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param function the function instance to be applied
         */
        public Instance(final ToByteFunctionN.Instance function) {
            this(function.arity(), function::applyAllAsByteUnchecked);
        }

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ThrowingToByteFunction1<Object[]> function) {
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public byte applyAllAsByteUnchecked(Object... args) throws Throwable {
            return this.function.applyAsByte(args);
        }

        /**
         * @see ToByteFunctionN.Instance
         */
        @Override
        public ToByteFunctionN.Instance handle(Handler handler) {
            return new ToByteFunctionN.Instance(this.arity(), (final Object[] args) -> {
                try {
                    return this.applyAllAsByteUnchecked(args);
                } catch (final Throwable t) {
                    return handler.onThrownAsByteUnchecked(t, args);
                }
            });
        }

        /**
         * @see ToByteFunctionN.Instance
         */
        @Override
        public ToByteFunctionN.Instance swallow() {
            return this.handle((t, args) -> (byte) 0);
        }

        /**
         * @see ThrowingFunctionN.Instance
         */
        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThen(Function1<? super Byte, ? extends V> after) {
            return (ThrowingFunctionN.Instance<V>) AbstractThrowingToByteFunctionN.super.andThen(after);
        }

        /**
         * @see ThrowingFunctionN.Instance
         */
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThenUnchecked(Function1<? super Byte, ? extends V> after) {
            return new ThrowingFunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsByte(args)));
        }
    }
}
