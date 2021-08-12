/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.ints.ToIntFunctionN;
import net.ashwork.functionality.throwable.ThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces an {@code int}-valued result or throws a throwable.
 * This is the {@code int}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code int}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToIntFunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsIntUnchecked(Object...)}.
 *
 * @see AbstractThrowingFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToIntFunctionN extends AbstractThrowingToIntFunctionN<AbstractThrowingToIntFunctionN.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToIntFunctionN
     */
    static ThrowingToIntFunctionN from(final ToIntFunctionN function) {
        return function::applyAllAsIntUnchecked;
    }

    @Override
    default ToIntFunctionN swallow() {
        return this.handle((t, args) -> 0);
    }

    /**
     * @see ThrowingFunctionN
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingToIntFunctionN.super.andThen(after);
    }

    /**
     * @see ThrowingFunctionN
     */
    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.applyAllAsIntUnchecked(args));
    }

    /**
     * An instance of {@link ToIntFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToIntFunctionN
     */
    class Instance implements AbstractThrowingToIntFunctionN<Handler> {

        private final int arity;
        private final ThrowingToIntFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param function the function instance to be applied
         */
        public Instance(final ToIntFunctionN.Instance function) {
            this(function.arity(), function::applyAllAsIntUnchecked);
        }

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ThrowingToIntFunction1<Object[]> function) {  //TODO: Swap
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public int applyAllAsIntUnchecked(Object... args) throws Throwable {
            return this.function.applyAsInt(args);
        }

        /**
         * @see ToIntFunctionN.Instance
         */
        @Override
        public ToIntFunctionN.Instance handle(Handler handler) {
            return new ToIntFunctionN.Instance(this.arity(), (final Object[] args) -> {
                try {
                    return this.applyAllAsIntUnchecked(args);
                } catch (final Throwable t) {
                    return handler.onThrownAsIntUnchecked(t, args);
                }
            });
        }

        /**
         * @see ToIntFunctionN.Instance
         */
        @Override
        public ToIntFunctionN.Instance swallow() {
            return this.handle((t, args) -> 0);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThen(Function1<? super Integer, ? extends V> after) {
            return (ThrowingFunctionN.Instance<V>) AbstractThrowingToIntFunctionN.super.andThen(after);
        }

        @Override
        public <V> ThrowingFunctionN.Instance<V> andThenUnchecked(Function1<? super Integer, ? extends V> after) {
            return new ThrowingFunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsInt(args)));
        }
    }
}
