/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.predicate;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.Predicate1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicate1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunction1;

/**
 * Represents a predicate that accepts one argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link ThrowingPredicateN}.
 * This is the predicate specialization for {@link ThrowingToBooleanFunction1}.
 * This is the throwing variation of {@link Predicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(Object)}.
 *
 * @param <T1> the type of the input to the predicate
 *
 * @see ThrowingPredicateN
 * @see ThrowingToBooleanFunction1
 * @see Predicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingPredicate1<T1> extends AbstractThrowingPredicate1<T1, AbstractThrowingPredicate1.Handler<T1>, ThrowingPredicate1<T1>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @param <T1> the type of the input to the function
     * @return a throwing instance of the original type
     *
     * @see Predicate1
     */
    static <T1> ThrowingPredicate1<T1> from(final Predicate1<T1> predicate) {
        return predicate::test;
    }

    /**
     * Creates an instance of this object from its {@link ThrowingToBooleanFunction1} function variant.
     *
     * @param <T1> the type of the input to the predicate
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ThrowingToBooleanFunction1
     */
    static <T1> ThrowingPredicate1<T1> fromFunctionVariant(final ThrowingToBooleanFunction1<T1> function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default ThrowingToBooleanFunction1<T1> toFunctionVariant() {
        return this::test;
    }

    /**
     * @see Predicate1
     */
    @Override
    default Predicate1<T1> handle(final Handler<T1> handler) {
        return (final T1 t1) -> {
            try {
                return this.test(t1);
            } catch (final Throwable t) {
                return handler.testThrown(t, t1);
            }
        };
    }

    /**
     * @see Predicate1
     */
    @Override
    default Predicate1<T1> swallow() {
        return this.handle((t, t1) -> false);
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingPredicate1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (ThrowingPredicate1<V>) AbstractThrowingPredicate1.super.compose(before);
    }

    @Override
    default <V> ThrowingPredicate1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<T1, V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingFunction1<T1, V>) AbstractThrowingPredicate1.super.andThen(after);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<T1, V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.test(t1));
    }

    @Override
    default ThrowingPredicate1<T1> not() {
        return (final T1 t1) -> !this.test(t1);
    }

    @Override
    default ThrowingPredicate1<T1> and(final ThrowingPredicate1<T1> other) {
        return (final T1 t1) -> this.test(t1) && other.test(t1);
    }

    @Override
    default ThrowingPredicate1<T1> or(final ThrowingPredicate1<T1> other) {
        return (final T1 t1) -> this.test(t1) || other.test(t1);
    }

    @Override
    default ThrowingPredicate1<T1> xor(final ThrowingPredicate1<T1> other) {
        return (ThrowingPredicate1<T1>) AbstractThrowingPredicate1.super.xor(other);
    }

    @Override
    default ThrowingPredicate1<T1> sub(final ThrowingPredicate1<T1> other) {
        return (ThrowingPredicate1<T1>) AbstractThrowingPredicate1.super.sub(other);
    }

    @Override
    default ThrowingPredicate1<T1> nand(final ThrowingPredicate1<T1> other) {
        return (ThrowingPredicate1<T1>) AbstractThrowingPredicate1.super.nand(other);
    }

    @Override
    default ThrowingPredicate1<T1> nor(final ThrowingPredicate1<T1> other) {
        return (ThrowingPredicate1<T1>) AbstractThrowingPredicate1.super.nor(other);
    }

    @Override
    default ThrowingPredicate1<T1> xnor(final ThrowingPredicate1<T1> other) {
        return (ThrowingPredicate1<T1>) AbstractThrowingPredicate1.super.xnor(other);
    }

    @Override
    default ThrowingPredicate1<T1> orNot(final ThrowingPredicate1<T1> other) {
        return (ThrowingPredicate1<T1>) AbstractThrowingPredicate1.super.orNot(other);
    }
}
