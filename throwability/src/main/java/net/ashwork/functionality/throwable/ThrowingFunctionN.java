/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.FunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a result or throws a throwable.
 * All throwing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link FunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllUnchecked(Object...)}.
 *
 * @param <R> the type of the result of the function
 *
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFunctionN<R> extends AbstractThrowingFunctionN<R, AbstractThrowingFunctionN.Handler<R>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <R> the type of the result of the function
     * @return a throwing instance of the original type
     *
     * @see FunctionN
     */
    static <R> ThrowingFunctionN<R> from(final FunctionN<R> function) {
        return function::applyAllUnchecked;
    }

    @Override
    default FunctionN<R> swallow() {
        return this.handle((throwable, args) -> null);
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super R, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingFunctionN.super.andThen(after);
    }

    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.applyAllUnchecked(args));
    }

    /**
     * An instance of {@link AbstractThrowingFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @param <R> the type of the result of the function
     *
     * @see AbstractThrowingFunctionN
     */
    class Instance<R> implements AbstractThrowingFunctionN<R, Handler<R>> {

        private final int arity;
        private final ThrowingFunction1<Object[], R> function;

        /**
         * Constructs an instance of the function.
         *
         * @param function the function instance to be applied
         */
        public Instance(final FunctionN.Instance<R> function) {
            this(function.arity(), function::applyAllUnchecked);
        }

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ThrowingFunction1<Object[], R> function) {
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public R applyAllUnchecked(Object... args) throws Throwable {
            return this.function.apply(args);
        }

        @Override
        public FunctionN.Instance<R> handle(Handler<R> handler) {
            return new FunctionN.Instance<>(this.arity(), (final Object[] args) -> {
                try {
                    return this.applyAllUnchecked(args);
                } catch (final Throwable t) {
                    return handler.onThrownUnchecked(t, args);
                }
            });
        }

        @Override
        public FunctionN.Instance<R> swallow() {
            return this.handle((throwable, args) -> null);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> Instance<V> andThen(Function1<? super R, ? extends V> after) {
            return (Instance<V>) AbstractThrowingFunctionN.super.andThen(after);
        }

        @Override
        public <V> Instance<V> andThenUnchecked(Function1<? super R, ? extends V> after) {
            return new Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.apply(args)));
        }
    }
}
