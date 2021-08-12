/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunctionN;
import net.ashwork.functionality.throwable.ThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingToDoubleFunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code double}-valued result or throws a throwable.
 * This is the {@code double}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code double}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToDoubleFunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsDoubleUnchecked(Object...)}.
 *
 * @see AbstractThrowingFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToDoubleFunctionN extends AbstractThrowingToDoubleFunctionN<AbstractThrowingToDoubleFunctionN.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToDoubleFunctionN
     */
    static ThrowingToDoubleFunctionN from(final ToDoubleFunctionN function) {
        return function::applyAllAsDoubleUnchecked;
    }

    @Override
    default ToDoubleFunctionN swallow() {
        return this.handle((t, args) -> 0D);
    }

    /**
     * @see ThrowingFunctionN
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see ThrowingFunctionN
     */
    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.applyAllAsDoubleUnchecked(args));
    }

    /**
     * An instance of {@link ToDoubleFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToDoubleFunctionN
     */
    class Instance implements AbstractThrowingToDoubleFunctionN<Handler> {

        private final int arity;
        private final ThrowingToDoubleFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param function the function instance to be applied
         */
        public Instance(final ToDoubleFunctionN.Instance function) {
            this(function.arity(), function::applyAllAsDoubleUnchecked);
        }

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ThrowingToDoubleFunction1<Object[]> function) {  //TODO: Swap
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public double applyAllAsDoubleUnchecked(Object... args) throws Throwable {
            return this.function.applyAsDouble(args);
        }

        /**
         * @see ToDoubleFunctionN.Instance
         */
        @Override
        public ToDoubleFunctionN.Instance handle(Handler handler) {
            return new ToDoubleFunctionN.Instance(this.arity(), (final Object[] args) -> {
                try {
                    return this.applyAllAsDoubleUnchecked(args);
                } catch (final Throwable t) {
                    return handler.onThrownAsDoubleUnchecked(t, args);
                }
            });
        }

        /**
         * @see ToDoubleFunctionN.Instance
         */
        @Override
        public ToDoubleFunctionN.Instance swallow() {
            return this.handle((t, args) -> 0D);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThen(Function1<? super Double, ? extends V> after) {
            return (ThrowingFunctionN.Instance<V>) AbstractThrowingToDoubleFunctionN.super.andThen(after);
        }

        @Override
        public <V> ThrowingFunctionN.Instance<V> andThenUnchecked(Function1<? super Double, ? extends V> after) {
            return new ThrowingFunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsDouble(args)));
        }
    }
}
