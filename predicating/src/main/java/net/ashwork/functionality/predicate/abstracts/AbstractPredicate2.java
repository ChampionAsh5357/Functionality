/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.abstracts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction2;
import net.ashwork.functionality.util.InheritOnly;

import java.util.function.BiPredicate;

/**
 * Represents a predicate that accepts two arguments and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link AbstractPredicateN}.
 * This is the predicate specialization for {@link ToBooleanFunction2}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the first argument to the predicate
 * @param <T2> the type of the second argument to the predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractPredicateN
 * @see ToBooleanFunction2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractPredicate2<T1, T2, P extends AbstractPredicate2<T1, T2, P>> extends AbstractPredicateN<P>, Variant<BiPredicate<T1, T2>>, FunctionVariant<Boolean, ToBooleanFunction2<T1, T2>> {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t1 the first input argument
     * @param t2 the second input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test(final T1 t1, final T2 t2);

    @SuppressWarnings("unchecked")
    @Override
    default boolean testAllUnchecked(final Object... args) {
        return this.test((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see ToBooleanFunction2
     */
    @Override
    default ToBooleanFunction2<T1, T2> toFunctionVariant() {
        return this::test;
    }

    /**
     * @see BiPredicate
     */
    @Override
    default BiPredicate<T1, T2> toVariant() {
        return this::test;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<T1, T2, V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (Function2<T1, T2, V>) AbstractPredicateN.super.andThen(after);
    }

    @Override
    default <V> Function2<T1, T2, V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.test(t1, t2));
    }

    @Override
    default AbstractPredicate2<T1, T2, P> not() {
        return (final T1 t1, final T2 t2) -> !this.test(t1, t2);
    }

    @Override
    default AbstractPredicate2<T1, T2, P> and(final P other) {
        return (final T1 t1, final T2 t2) -> this.test(t1, t2) && other.test(t1, t2);
    }

    @Override
    default AbstractPredicate2<T1, T2, P> or(final P other) {
        return (final T1 t1, final T2 t2) -> this.test(t1, t2) || other.test(t1, t2);
    }

    @Override
    default AbstractPredicate2<T1, T2, P> xor(final P other) {
        return (AbstractPredicate2<T1, T2, P>) AbstractPredicateN.super.xor(other);
    }

    @Override
    default AbstractPredicate2<T1, T2, P> sub(final P other) {
        return (AbstractPredicate2<T1, T2, P>) AbstractPredicateN.super.sub(other);
    }

    @Override
    default AbstractPredicate2<T1, T2, P> nand(final P other) {
        return (AbstractPredicate2<T1, T2, P>) AbstractPredicateN.super.nand(other);
    }

    @Override
    default AbstractPredicate2<T1, T2, P> nor(final P other) {
        return (AbstractPredicate2<T1, T2, P>) AbstractPredicateN.super.nor(other);
    }

    @Override
    default AbstractPredicate2<T1, T2, P> xnor(final P other) {
        return (AbstractPredicate2<T1, T2, P>) AbstractPredicateN.super.xnor(other);
    }

    @Override
    default AbstractPredicate2<T1, T2, P> orNot(final P other) {
        return (AbstractPredicate2<T1, T2, P>) AbstractPredicateN.super.orNot(other);
    }
}
