/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate;

import net.ashwork.functionality.predicate.abstracts.AbstractPredicate2;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction2;

import java.util.function.BiPredicate;

/**
 * Represents a predicate that accepts two arguments and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link PredicateN}.
 * This is the predicate specialization for {@link ToBooleanFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the predicate
 * @param <T2> the type of the second argument to the predicate
 *
 * @see PredicateN
 * @see ToBooleanFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface Predicate2<T1, T2> extends AbstractPredicate2<T1, T2, Predicate2<T1, T2>> {

    /**
     * Creates an instance of this object from its {@link ToBooleanFunction2} function variant.
     *
     * @param <T1> the type of the first argument to the predicate
     * @param <T2> the type of the second argument to the predicate
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ToBooleanFunction2
     */
    static <T1, T2> Predicate2<T1, T2> fromFunctionVariant(final ToBooleanFunction2<T1, T2> function) {
        return function::applyAsBoolean;
    }

    /**
     * Creates an instance of this object from its {@link BiPredicate} variant.
     *
     * @param <T1> the type of the first argument to the predicate
     * @param <T2> the type of the second argument to the predicate
     * @param predicate the variant of this object
     * @return an instance of this object
     *
     * @see BiPredicate
     */
    static <T1, T2> Predicate2<T1, T2> fromVariant(final BiPredicate<T1, T2> predicate) {
        return predicate::test;
    }

    @Override
    default Predicate2<T1, T2> not() {
        return (final T1 t1, final T2 t2) -> !this.test(t1, t2);
    }

    @Override
    default Predicate2<T1, T2> and(final Predicate2<T1, T2> other) {
        return (final T1 t1, final T2 t2) -> this.test(t1, t2) && other.test(t1, t2);
    }

    @Override
    default Predicate2<T1, T2> or(final Predicate2<T1, T2> other) {
        return (final T1 t1, final T2 t2) -> this.test(t1, t2) || other.test(t1, t2);
    }

    @Override
    default Predicate2<T1, T2> xor(final Predicate2<T1, T2> other) {
        return (Predicate2<T1, T2>) AbstractPredicate2.super.xor(other);
    }

    @Override
    default Predicate2<T1, T2> sub(final Predicate2<T1, T2> other) {
        return (Predicate2<T1, T2>) AbstractPredicate2.super.sub(other);
    }

    @Override
    default Predicate2<T1, T2> nand(final Predicate2<T1, T2> other) {
        return (Predicate2<T1, T2>) AbstractPredicate2.super.nand(other);
    }

    @Override
    default Predicate2<T1, T2> nor(final Predicate2<T1, T2> other) {
        return (Predicate2<T1, T2>) AbstractPredicate2.super.nor(other);
    }

    @Override
    default Predicate2<T1, T2> xnor(final Predicate2<T1, T2> other) {
        return (Predicate2<T1, T2>) AbstractPredicate2.super.xnor(other);
    }

    @Override
    default Predicate2<T1, T2> orNot(final Predicate2<T1, T2> other) {
        return (Predicate2<T1, T2>) AbstractPredicate2.super.orNot(other);
    }
}
