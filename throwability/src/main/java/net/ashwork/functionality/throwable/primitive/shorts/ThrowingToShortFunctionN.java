/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.shorts.ToShortFunctionN;
import net.ashwork.functionality.throwable.ThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code short}-valued result or throws a throwable.
 * This is the {@code short}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code short}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToShortFunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsShortUnchecked(Object...)}.
 *
 * @see AbstractThrowingFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToShortFunctionN extends AbstractThrowingToShortFunctionN<AbstractThrowingToShortFunctionN.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToShortFunctionN
     */
    static ThrowingToShortFunctionN from(final ToShortFunctionN function) {
        return function::applyAllAsShortUnchecked;
    }

    @Override
    default ToShortFunctionN swallow() {
        return this.handle((t, args) -> (short) 0);
    }

    /**
     * @see ThrowingFunctionN
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super Short, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingToShortFunctionN.super.andThen(after);
    }

    /**
     * @see ThrowingFunctionN
     */
    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.applyAllAsShortUnchecked(args));
    }

    /**
     * An instance of {@link ToShortFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToShortFunctionN
     */
    class Instance implements AbstractThrowingToShortFunctionN<Handler> {

        private final int arity;
        private final ThrowingToShortFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param function the function instance to be applied
         */
        public Instance(final ToShortFunctionN.Instance function) {
            this(function.arity(), function::applyAllAsShortUnchecked);
        }

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ThrowingToShortFunction1<Object[]> function) {  //TODO: Swap
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public short applyAllAsShortUnchecked(Object... args) throws Throwable {
            return this.function.applyAsShort(args);
        }

        /**
         * @see ToShortFunctionN.Instance
         */
        @Override
        public ToShortFunctionN.Instance handle(Handler handler) {
            return new ToShortFunctionN.Instance(this.arity(), (final Object[] args) -> {
                try {
                    return this.applyAllAsShortUnchecked(args);
                } catch (final Throwable t) {
                    return handler.onThrownAsShortUnchecked(t, args);
                }
            });
        }

        /**
         * @see ToShortFunctionN.Instance
         */
        @Override
        public ToShortFunctionN.Instance swallow() {
            return this.handle((t, args) -> (short) 0);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThen(Function1<? super Short, ? extends V> after) {
            return (ThrowingFunctionN.Instance<V>) AbstractThrowingToShortFunctionN.super.andThen(after);
        }

        @Override
        public <V> ThrowingFunctionN.Instance<V> andThenUnchecked(Function1<? super Short, ? extends V> after) {
            return new ThrowingFunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsShort(args)));
        }
    }
}
