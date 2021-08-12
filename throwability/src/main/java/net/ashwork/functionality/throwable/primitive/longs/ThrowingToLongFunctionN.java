/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.longs.ToLongFunctionN;
import net.ashwork.functionality.throwable.ThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code long}-valued result or throws a throwable.
 * This is the {@code long}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code long}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToLongFunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsLongUnchecked(Object...)}.
 *
 * @see AbstractThrowingFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToLongFunctionN extends AbstractThrowingToLongFunctionN<AbstractThrowingToLongFunctionN.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToLongFunctionN
     */
    static ThrowingToLongFunctionN from(final ToLongFunctionN function) {
        return function::applyAllAsLongUnchecked;
    }

    @Override
    default ToLongFunctionN swallow() {
        return this.handle((t, args) -> 0L);
    }

    /**
     * @see ThrowingFunctionN
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingToLongFunctionN.super.andThen(after);
    }

    /**
     * @see ThrowingFunctionN
     */
    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.applyAllAsLongUnchecked(args));
    }

    /**
     * An instance of {@link ToLongFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToLongFunctionN
     */
    class Instance implements AbstractThrowingToLongFunctionN<Handler> {

        private final int arity;
        private final ThrowingToLongFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param function the function instance to be applied
         */
        public Instance(final ToLongFunctionN.Instance function) {
            this(function.arity(), function::applyAllAsLongUnchecked);
        }

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ThrowingToLongFunction1<Object[]> function) {  //TODO: Swap
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public long applyAllAsLongUnchecked(Object... args) throws Throwable {
            return this.function.applyAsLong(args);
        }

        /**
         * @see ToLongFunctionN.Instance
         */
        @Override
        public ToLongFunctionN.Instance handle(Handler handler) {
            return new ToLongFunctionN.Instance(this.arity(), (final Object[] args) -> {
                try {
                    return this.applyAllAsLongUnchecked(args);
                } catch (final Throwable t) {
                    return handler.onThrownAsLongUnchecked(t, args);
                }
            });
        }

        /**
         * @see ToLongFunctionN.Instance
         */
        @Override
        public ToLongFunctionN.Instance swallow() {
            return this.handle((t, args) -> 0L);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThen(Function1<? super Long, ? extends V> after) {
            return (ThrowingFunctionN.Instance<V>) AbstractThrowingToLongFunctionN.super.andThen(after);
        }

        @Override
        public <V> ThrowingFunctionN.Instance<V> andThenUnchecked(Function1<? super Long, ? extends V> after) {
            return new ThrowingFunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsLong(args)));
        }
    }
}
