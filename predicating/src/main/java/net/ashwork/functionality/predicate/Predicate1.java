/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicate1;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction1;

import java.util.function.Predicate;

/**
 * Represents a predicate that accepts one argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link PredicateN}.
 * This is the predicate specialization for {@link ToBooleanFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(Object)}.
 *
 * @param <T1> the type of the input to the predicate
 *
 * @see PredicateN
 * @see ToBooleanFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface Predicate1<T1> extends AbstractPredicate1<T1, Predicate1<T1>> {

    /**
     * Creates an instance of this object from its {@link ToBooleanFunction1} function variant.
     *
     * @param <T1> the type of the input to the predicate
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ToBooleanFunction1
     */
    static <T1> Predicate1<T1> fromFunctionVariant(final ToBooleanFunction1<T1> function) {
        return function::applyAsBoolean;
    }

    /**
     * Creates an instance of this object from its {@link Predicate} variant.
     *
     * @param <T1> the type of the input to the predicate
     * @param predicate the variant of this object
     * @return an instance of this object
     *
     * @see Predicate
     */
    static <T1> Predicate1<T1> fromVariant(final Predicate<T1> predicate) {
        return predicate::test;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> Predicate1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (Predicate1<V>) AbstractPredicate1.super.compose(before);
    }

    @Override
    default <V> Predicate1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    @Override
    default Predicate1<T1> not() {
        return (final T1 t1) -> !this.test(t1);
    }

    @Override
    default Predicate1<T1> and(final Predicate1<T1> other) {
        return (final T1 t1) -> this.test(t1) && other.test(t1);
    }

    @Override
    default Predicate1<T1> or(final Predicate1<T1> other) {
        return (final T1 t1) -> this.test(t1) || other.test(t1);
    }

    @Override
    default Predicate1<T1> xor(final Predicate1<T1> other) {
        return (Predicate1<T1>)  AbstractPredicate1.super.xor(other);
    }

    @Override
    default Predicate1<T1> sub(final Predicate1<T1> other) {
        return (Predicate1<T1>)  AbstractPredicate1.super.sub(other);
    }

    @Override
    default Predicate1<T1> nand(final Predicate1<T1> other) {
        return (Predicate1<T1>)  AbstractPredicate1.super.nand(other);
    }

    @Override
    default Predicate1<T1> nor(final Predicate1<T1> other) {
        return (Predicate1<T1>)  AbstractPredicate1.super.nor(other);
    }

    @Override
    default Predicate1<T1> xnor(final Predicate1<T1> other) {
        return (Predicate1<T1>)  AbstractPredicate1.super.xnor(other);
    }

    @Override
    default Predicate1<T1> orNot(final Predicate1<T1> other) {
        return (Predicate1<T1>)  AbstractPredicate1.super.orNot(other);
    }
}
