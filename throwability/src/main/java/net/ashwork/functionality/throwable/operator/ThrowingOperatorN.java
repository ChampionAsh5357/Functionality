/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.operator;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.ThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;

/**
 * Represents an operation that accepts {@code n} operands and produces a result of the same type as its operands or throws a throwable.
 * All operators are derived from this {@code n}-arity specialization.
 * This is a specialization of {@link ThrowingFunctionN} where the operands and result are of the same type.
 * This is the throwing variation of {@link OperatorN}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAllUnchecked(Object...)}.
 *
 * @param <T> the type of the operands and result of the operator
 *
 * @see ThrowingFunctionN
 * @see OperatorN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingOperatorN<T> extends AbstractThrowingOperatorN<T, AbstractThrowingFunctionN.Handler<T>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @param <T> the type of the operands and result of the operator
     * @return a throwing instance of the original type
     *
     * @see OperatorN
     */
    static <T> ThrowingOperatorN<T> from(final OperatorN<T> operator) {
        return operator::applyAllUnchecked;
    }

    @Override
    default OperatorN<T> swallow() {
        return this.handle((t, args) -> null);
    }

    /**
     * @see ThrowingFunctionN
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super T, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see ThrowingFunctionN
     */
    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super T, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.applyAllUnchecked(args));
    }

    /**
     * An instance of {@link AbstractThrowingOperatorN} which properly defines the
     * arity of that particular operator.
     *
     * @param <T> the type of the operands and result of the operator
     *
     * @see AbstractThrowingOperatorN
     */
    class Instance<T> implements AbstractThrowingOperatorN<T, AbstractThrowingFunctionN.Handler<T>> {

        private final int arity;
        private final ThrowingFunction1<T[], T> operator;

        /**
         * Constructs an instance of the operator.
         *
         * @param operator the operator instance to be applied
         */
        public Instance(final OperatorN.Instance<T> operator) {
            this(operator.arity(), operator::applyAllUnchecked);
        }

        /**
         * Constructs an instance of the operator.
         *
         * @param arity the number of arguments of the operator
         * @param operator the operator to be applied
         */
        public Instance(final int arity, final ThrowingFunction1<T[], T> operator) {
            this.arity = arity;
            this.operator = operator;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T applyAllUnchecked(Object... args) throws Throwable {
            return this.operator.apply((T[]) args);
        }

        /**
         * @see OperatorN.Instance
         */
        @Override
        public OperatorN.Instance<T> handle(Handler<T> handler) {
            return new OperatorN.Instance<>(this.arity(), (final T[] args) -> {
                try {
                    return this.applyAllUnchecked((Object[]) args);
                } catch (final Throwable t) {
                    return handler.onThrownUnchecked(t, (Object[]) args);
                }
            });
        }

        /**
         * @see OperatorN.Instance
         */
        @Override
        public OperatorN.Instance<T> swallow() {
            return this.handle((t, args) -> null);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThen(Function1<? super T, ? extends V> after) {
            return (ThrowingFunctionN.Instance<V>) AbstractThrowingOperatorN.super.andThen(after);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThenUnchecked(Function1<? super T, ? extends V> after) {
            return new ThrowingFunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.operator.apply((T[]) args)));
        }
    }
}
