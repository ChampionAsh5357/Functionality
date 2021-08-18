/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.floats.ToFloatFunctionN;
import net.ashwork.functionality.throwable.ThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingToFloatFunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code float}-valued result or throws a throwable.
 * This is the {@code float}-producing primitive specialization for {@link ThrowingFunctionN}.
 * All {@code float}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToFloatFunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsFloatUnchecked(Object...)}.
 *
 * @see ThrowingFunctionN
 * @see ToFloatFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToFloatFunctionN extends AbstractThrowingToFloatFunctionN<AbstractThrowingToFloatFunctionN.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToFloatFunctionN
     */
    static ThrowingToFloatFunctionN from(final ToFloatFunctionN function) {
        return function::applyAllAsFloatUnchecked;
    }

    @Override
    default ToFloatFunctionN swallow() {
        return this.handle((t, args) -> 0.0f);
    }

    /**
     * @see ThrowingFunctionN
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super Float, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingToFloatFunctionN.super.andThen(after);
    }

    /**
     * @see ThrowingFunctionN
     */
    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.applyAllAsFloatUnchecked(args));
    }

    /**
     * An instance of {@link ToFloatFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToFloatFunctionN
     */
    class Instance implements AbstractThrowingToFloatFunctionN<Handler> {

        private final int arity;
        private final ThrowingToFloatFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param function the function instance to be applied
         */
        public Instance(final ToFloatFunctionN.Instance function) {
            this(function.arity(), function::applyAllAsFloatUnchecked);
        }

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ThrowingToFloatFunction1<Object[]> function) {
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public float applyAllAsFloatUnchecked(Object... args) throws Throwable {
            return this.function.applyAsFloat(args);
        }

        /**
         * @see ToFloatFunctionN.Instance
         */
        @Override
        public ToFloatFunctionN.Instance handle(Handler handler) {
            return new ToFloatFunctionN.Instance(this.arity(), (final Object[] args) -> {
                try {
                    return this.applyAllAsFloatUnchecked(args);
                } catch (final Throwable t) {
                    return handler.onThrownAsFloatUnchecked(t, args);
                }
            });
        }

        /**
         * @see ToFloatFunctionN.Instance
         */
        @Override
        public ToFloatFunctionN.Instance swallow() {
            return this.handle((t, args) -> 0.0f);
        }

        /**
         * @see ThrowingFunctionN.Instance
         */
        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThen(Function1<? super Float, ? extends V> after) {
            return (ThrowingFunctionN.Instance<V>) AbstractThrowingToFloatFunctionN.super.andThen(after);
        }

        /**
         * @see ThrowingFunctionN.Instance
         */
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThenUnchecked(Function1<? super Float, ? extends V> after) {
            return new ThrowingFunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsFloat(args)));
        }
    }
}
