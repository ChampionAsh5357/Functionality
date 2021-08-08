/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.abstracts;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction0;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts no arguments and produces a {@code boolean}-valued result.
 * This is the zero-arity specialization for {@link AbstractPredicateN}.
 * This is the predicate specialization for {@link ToBooleanFunction0}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <P> the type of this predicate
 *
 * @see AbstractPredicateN
 * @see ToBooleanFunction0
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractPredicate0<P extends AbstractPredicate0<P>> extends AbstractPredicateN<P>, FunctionVariant<Boolean, ToBooleanFunction0> {

    /**
     * Evaluates this predicate.
     *
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test();

    @Override
    default boolean testAllUnchecked(final Object... args) {
        return this.test();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see ToBooleanFunction0
     */
    @Override
    default ToBooleanFunction0 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see Function0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function0<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (Function0<V>) AbstractPredicateN.super.andThen(after);
    }

    /**
     * @see Function0
     */
    @Override
    default <V> Function0<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return () -> after.apply(this.test());
    }

    @Override
    default AbstractPredicate0<P> not() {
        return () -> !this.test();
    }

    @Override
    default AbstractPredicate0<P> and(final P other) {
        return () -> this.test() && other.test();
    }

    @Override
    default AbstractPredicate0<P> or(final P other) {
        return () -> this.test() || other.test();
    }

    @Override
    default AbstractPredicate0<P> xor(final P other) {
        return (AbstractPredicate0<P>) AbstractPredicateN.super.xor(other);
    }

    @Override
    default AbstractPredicate0<P> sub(final P other) {
        return (AbstractPredicate0<P>) AbstractPredicateN.super.sub(other);
    }

    @Override
    default AbstractPredicate0<P> nand(final P other) {
        return (AbstractPredicate0<P>) AbstractPredicateN.super.nand(other);
    }

    @Override
    default AbstractPredicate0<P> nor(final P other) {
        return (AbstractPredicate0<P>) AbstractPredicateN.super.nor(other);
    }

    @Override
    default AbstractPredicate0<P> xnor(final P other) {
        return (AbstractPredicate0<P>) AbstractPredicateN.super.xnor(other);
    }

    @Override
    default AbstractPredicate0<P> orNot(final P other) {
        return (AbstractPredicate0<P>) AbstractPredicateN.super.orNot(other);
    }
}
