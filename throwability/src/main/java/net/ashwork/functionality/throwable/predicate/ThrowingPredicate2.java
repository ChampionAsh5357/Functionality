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
import net.ashwork.functionality.predicate.Predicate2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicate2;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunction2;

/**
 * Represents a predicate that accepts two arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link ThrowingPredicateN}.
 * This is the predicate specialization for {@link ThrowingToBooleanFunction2}.
 * This is the throwing variation of {@link Predicate2}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the predicate
 * @param <T2> the type of the second argument to the predicate
 *
 * @see ThrowingPredicateN
 * @see ThrowingToBooleanFunction2
 * @see Predicate2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingPredicate2<T1, T2> extends AbstractThrowingPredicate2<T1, T2, AbstractThrowingPredicate2.Handler<T1, T2>, ThrowingPredicate2<T1, T2>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @param <T1> the type of the first argument to the predicate
     * @param <T2> the type of the second argument to the predicate
     * @return a throwing instance of the original type
     *
     * @see Predicate2
     */
    static <T1, T2> ThrowingPredicate2<T1, T2> from(final Predicate2<T1, T2> predicate) {
        return predicate::test;
    }

    /**
     * Creates an instance of this object from its {@link ThrowingToBooleanFunction2} function variant.
     *
     * @param <T1> the type of the first argument to the predicate
     * @param <T2> the type of the second argument to the predicate
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ThrowingToBooleanFunction2
     */
    static <T1, T2> ThrowingPredicate2<T1, T2> fromFunctionVariant(final ThrowingToBooleanFunction2<T1, T2> function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingToBooleanFunction2
     */
    @Override
    default ThrowingToBooleanFunction2<T1, T2> toFunctionVariant() {
        return this::test;
    }

    /**
     * @see Predicate2
     */
    @Override
    default Predicate2<T1, T2> handle(final Handler<T1, T2> handler) {
        return (final T1 t1, final T2 t2) -> {
            try {
                return this.test(t1, t2);
            } catch (final Throwable t) {
                return handler.testThrown(t, t1, t2);
            }
        };
    }

    /**
     * @see Predicate2
     */
    @Override
    default Predicate2<T1, T2> swallow() {
        return this.handle((t, t1, t2) -> false);
    }

    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingFunction2<T1, T2, V>) AbstractThrowingPredicate2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after){
        return (final T1 t1, final T2 t2) -> after.apply(this.test(t1, t2));
    }

    @Override
    default ThrowingPredicate2<T1, T2> not() {
        return (final T1 t1, final T2 t2) -> !this.test(t1, t2);
    }

    @Override
    default ThrowingPredicate2<T1, T2> and(final ThrowingPredicate2<T1, T2> other) {
        return (final T1 t1, final T2 t2) -> this.test(t1, t2) && other.test(t1, t2);
    }

    @Override
    default ThrowingPredicate2<T1, T2> or(final ThrowingPredicate2<T1, T2> other) {
        return (final T1 t1, final T2 t2) -> this.test(t1, t2) || other.test(t1, t2);
    }

    @Override
    default ThrowingPredicate2<T1, T2> xor(final ThrowingPredicate2<T1, T2> other) {
        return (ThrowingPredicate2<T1, T2>) AbstractThrowingPredicate2.super.xor(other);
    }

    @Override
    default ThrowingPredicate2<T1, T2> sub(final ThrowingPredicate2<T1, T2> other) {
        return (ThrowingPredicate2<T1, T2>) AbstractThrowingPredicate2.super.sub(other);
    }

    @Override
    default ThrowingPredicate2<T1, T2> nand(final ThrowingPredicate2<T1, T2> other) {
        return (ThrowingPredicate2<T1, T2>) AbstractThrowingPredicate2.super.nand(other);
    }

    @Override
    default ThrowingPredicate2<T1, T2> nor(final ThrowingPredicate2<T1, T2> other) {
        return (ThrowingPredicate2<T1, T2>) AbstractThrowingPredicate2.super.nor(other);
    }

    @Override
    default ThrowingPredicate2<T1, T2> xnor(final ThrowingPredicate2<T1, T2> other) {
        return (ThrowingPredicate2<T1, T2>) AbstractThrowingPredicate2.super.xnor(other);
    }

    @Override
    default ThrowingPredicate2<T1, T2> orNot(final ThrowingPredicate2<T1, T2> other) {
        return (ThrowingPredicate2<T1, T2>) AbstractThrowingPredicate2.super.orNot(other);
    }
}
