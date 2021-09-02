/*
 * Operating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.operator;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.FunctionN;

/**
 * Represents an operation that accepts {@code n} operands and produces a result of the same type as its operands.
 * All operators are derived from this {@code n}-arity specialization.
 * This is a specialization of {@link FunctionN} where the operands and result are of the same type.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllUnchecked(Object...)}.
 *
 * @param <T> the type of the operands and result of the operator
 *
 * @see FunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface OperatorN<T> extends FunctionN<T> {

    /**
     * An instance of {@link OperatorN} which properly defines the
     * arity of that particular operator.
     *
     * @param <T> the type of the operands and result of the operator
     *
     * @see OperatorN
     */
    class Instance<T> implements OperatorN<T> {

        private final int arity;
        private final Function1<Object[], T> operator;

        /**
         * Constructs an instance of the function.
         *
         * @param arity the number of arguments of the function
         * @param operator the operator to be applied
         */
        public Instance(final int arity, final Function1<Object[], T> operator) {
            this.arity = arity;
            this.operator = operator;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public T applyAllUnchecked(Object... args) {
            return this.operator.apply(args);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> FunctionN.Instance<V> andThen(Function1<? super T, ? extends V> after) {
            return (FunctionN.Instance<V>) OperatorN.super.andThen(after);
        }

        @Override
        public <V> FunctionN.Instance<V> andThenUnchecked(Function1<? super T, ? extends V> after) {
            return new FunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.operator.apply(args)));
        }
    }
}
