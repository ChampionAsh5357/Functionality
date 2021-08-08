/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.abstracts;

import net.ashwork.functionality.predicate.partial.LogicalOperator;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts {@code n} arguments and produces a {@code boolean}-valued result.
 * This is the predicate specialization for {@link ToBooleanFunctionN}.
 * All {@code boolean}-producing predicates are derived from this {@code n}-arity specialization.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <P> the type of this predicate
 *
 * @see ToBooleanFunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractPredicateN<P extends AbstractPredicateN<P>> extends ToBooleanFunctionN, LogicalOperator<P> {

    /**
     * Evaluates this predicate on the given arguments. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the input arguments
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean testAllUnchecked(final Object... args);

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) {
        return this.testAllUnchecked(args);
    }

    @Override
    default AbstractPredicateN<P> not() {
        return (final Object[] args) -> !this.applyAllUnchecked(args);
    }

    @Override
    default AbstractPredicateN<P> and(final P other) {
        return (final Object[] args) -> this.applyAllUnchecked(args) && other.applyAllUnchecked(args);
    }

    @Override
    default AbstractPredicateN<P> or(final P other) {
        return (final Object[] args) -> this.applyAllUnchecked(args) || other.applyAllUnchecked(args);
    }

    @Override
    default AbstractPredicateN<P> xor(final P other) {
        return (AbstractPredicateN<P>) LogicalOperator.super.xor(other);
    }

    @Override
    default AbstractPredicateN<P> sub(final P other) {
        return (AbstractPredicateN<P>) LogicalOperator.super.sub(other);
    }

    @Override
    default AbstractPredicateN<P> nand(final P other) {
        return (AbstractPredicateN<P>) LogicalOperator.super.nand(other);
    }

    @Override
    default AbstractPredicateN<P> nor(final P other) {
        return (AbstractPredicateN<P>) LogicalOperator.super.nor(other);
    }

    @Override
    default AbstractPredicateN<P> xnor(final P other) {
        return (AbstractPredicateN<P>) LogicalOperator.super.xnor(other);
    }

    @Override
    default AbstractPredicateN<P> orNot(final P other) {
        return (AbstractPredicateN<P>) LogicalOperator.super.orNot(other);
    }
}
