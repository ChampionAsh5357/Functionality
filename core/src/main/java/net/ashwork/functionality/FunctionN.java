/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality;

import net.ashwork.functionality.partial.Arity;
import net.ashwork.functionality.partial.ResultChainableResult;

/**
 * Represents a function that accepts {@code n} arguments and produces a result.
 * All functions are derived from this {@code n}-arity specialization.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllUnchecked(Object...)}.
 *
 * @param <R> the type of the result of the function
 *
 * @since 3.0.0
 */
@FunctionalInterface
public interface FunctionN<R> extends ResultChainableResult<R>, Arity {

    /**
     * Applies this function to the given arguments. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     */
    R applyAllUnchecked(final Object... args);

    /**
     * Returns the number of arguments of the function. If used as a
     * functional interface with a non-specific number of arguments,
     * this will return {@code -1}.
     *
     * @return the number of arguments of the function
     */
    @Override
    default int arity() {
        return -1;
    }

    /**
     * Applies this function to the given arguments. If the number of
     * arguments do not match the {@link #arity()} of this function,
     * an exception will be thrown.
     *
     * @param args the number of arguments of the function
     * @return the function result
     * @throws FunctionSizeException if the number of arguments of the
     *                               function is not equal to its arity
     */
    default R sizedApplyAllUnchecked(final Object... args) {
        return this.applyAllUnchecked(checkSize(this.arity(), args));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> FunctionN<V> andThen(final Function1<? super R, ? extends V> after) {
        return (FunctionN<V>) ResultChainableResult.super.andThen(after);
    }

    @Override
    default <V> FunctionN<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.applyAllUnchecked(args));
    }

    /**
     * Checks the size of the {@code array} to see if it is equal to
     * the expected {@code size}. If not, an exception is thrown.
     *
     * @param size the expected size of the array
     * @param array the array being checked
     * @param <T> the type of the elements in the array
     * @return the array if the sizes are equal
     * @throws FunctionSizeException if the array's length and the expected
     *                               size are not equal
     */
    static <T> T[] checkSize(final int size, final T[] array) {
        return checkSize(size, array, "Function Arity " + size + " does not match the current argument size: " + array.length);
    }

    /**
     * Checks the size of the {@code array} to see if it is equal to
     * the expected {@code size}. If not, an exception is thrown.
     *
     * @param size the expected size of the array
     * @param array the array being checked
     * @param message the error message shown if the array's length and
     *                the expected size are not equal
     * @param <T> the type of the elements in the array
     * @return the array if the sizes are equal
     * @throws FunctionSizeException if the array's length and the expected
     *                               size are not equal
     */
    static <T> T[] checkSize(final int size, final T[] array, final String message) {
        if (size != array.length)
            throw new FunctionSizeException(message);
        return array;
    }

    /**
     * An instance of {@link FunctionN} which properly defines the
     * arity of that particular function.
     *
     * @param <R> the type of the result of the function
     *
     * @see FunctionN
     */
    class Instance<R> implements FunctionN<R> {

        private final int arity;
        private final Function1<Object[], R> function;

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param function the function to be applied
         */
        public Instance(final int arity, final Function1<Object[], R> function) {
            this.arity = arity;
            this.function = function;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public R applyAllUnchecked(final Object... args) {
            return this.function.apply(args);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> Instance<V> andThen(Function1<? super R, ? extends V> after) {
            return (Instance<V>) FunctionN.super.andThen(after);
        }

        @Override
        public <V> Instance<V> andThenUnchecked(Function1<? super R, ? extends V> after) {
            return new Instance<>(this.arity(), (final Object[] args) -> after.apply(this.function.apply(args)));
        }
    }

    /**
     * A {@link RuntimeException} that is thrown whenever the size of
     * the arguments of a function is not equal to its arity.
     *
     * @see RuntimeException
     */
    class FunctionSizeException extends RuntimeException {

        /**
         * Constructs an instance of the exception.
         *
         * @param message the error message shown by the exception
         */
        public FunctionSizeException(final String message) {
            super(message);
        }
    }
}
