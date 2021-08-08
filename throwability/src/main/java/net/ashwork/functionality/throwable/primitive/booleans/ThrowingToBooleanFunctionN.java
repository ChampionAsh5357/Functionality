/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunctionN;
import net.ashwork.functionality.throwable.ThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingToBooleanFunctionN;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the {@code boolean}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code boolean}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToBooleanFunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllAsBooleanUnchecked(Object...)}.
 *
 * @see AbstractThrowingFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToBooleanFunctionN extends AbstractThrowingToBooleanFunctionN<AbstractThrowingToBooleanFunctionN.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToBooleanFunctionN
     */
    static ThrowingToBooleanFunctionN from(final ToBooleanFunctionN function) {
        return function::applyAllAsBooleanUnchecked;
    }

    @Override
    default ToBooleanFunctionN swallow() {
        return this.handle((t, args) -> false);
    }

    /**
     * @see ThrowingFunctionN
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingToBooleanFunctionN.super.andThen(after);
    }

    /**
     * @see ThrowingFunctionN
     */
    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.applyAllAsBooleanUnchecked(args));
    }

    /**
     * An instance of {@link ToBooleanFunctionN} which properly defines the
     * arity of that particular function.
     *
     * @see ToBooleanFunctionN
     */
    class Instance implements AbstractThrowingToBooleanFunctionN<Handler> {

        private final int arity;
        private final ThrowingToBooleanFunction1<Object[]> function;

        /**
         * Constructs an instance of the function.
         *
         * @param function the function instance to be applied
         */
        public Instance(final ToBooleanFunctionN.Instance function) {
            this(function.arity(), function::applyAllAsBooleanUnchecked);
        }

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final ThrowingToBooleanFunction1<Object[]> function) {  //TODO: Swap
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public boolean applyAllAsBooleanUnchecked(Object... args) throws Throwable {
            return this.function.applyAsBoolean(args);
        }

        /**
         * @see ToBooleanFunctionN.Instance
         */
        @Override
        public ToBooleanFunctionN.Instance handle(Handler handler) {
            return new ToBooleanFunctionN.Instance(this.arity(), (final Object[] args) -> {
                try {
                    return this.applyAllAsBooleanUnchecked(args);
                } catch (final Throwable t) {
                    return handler.onThrownAsBooleanUnchecked(t, args);
                }
            });
        }

        /**
         * @see ToBooleanFunctionN.Instance
         */
        @Override
        public ToBooleanFunctionN.Instance swallow() {
            return this.handle((t, args) -> false);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThen(Function1<? super Boolean, ? extends V> after) {
            return (ThrowingFunctionN.Instance<V>) AbstractThrowingToBooleanFunctionN.super.andThen(after);
        }

        @Override
        public <V> ThrowingFunctionN.Instance<V> andThenUnchecked(Function1<? super Boolean, ? extends V> after) {
            return new ThrowingFunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.applyAsBoolean(args)));
        }
    }
}
