/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.primitive.booleans;

import net.ashwork.functionality.operator.primitive.booleans.BooleanOperator1;
import net.ashwork.functionality.predicate.Predicate1;
import net.ashwork.functionality.predicate.PredicateN;
import net.ashwork.functionality.predicate.abstracts.AbstractBooleanPredicate1;

/**
 * Represents a predicate that accepts a {@code boolean}-valued argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link PredicateN}.
 * This is the predicate specialization for {@link BooleanOperator1}.
 * This is the {@code boolean}-consuming primitive specialization of {@link Predicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(boolean)}.
 *
 * @see PredicateN
 * @see BooleanOperator1
 * @see Predicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface BooleanPredicate1 extends AbstractBooleanPredicate1<Predicate1<Boolean>, BooleanPredicate1> {

    /**
     * Creates an instance of this object from its {@link BooleanOperator1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see BooleanOperator1
     */
    static BooleanPredicate1 fromFunctionVariant(final BooleanOperator1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see Predicate1
     */
    @Override
    default Predicate1<Boolean> boxInput() {
        return this::test;
    }

    @Override
    default BooleanPredicate1 not() {
        return (final boolean value) -> !this.test(value);
    }

    @Override
    default BooleanPredicate1 and(final BooleanPredicate1 other) {
        return (final boolean value) -> this.test(value) && other.test(value);
    }

    @Override
    default BooleanPredicate1 or(final BooleanPredicate1 other) {
        return (final boolean value) -> this.test(value) || other.test(value);
    }

    @Override
    default BooleanPredicate1 xor(final BooleanPredicate1 other) {
        return (BooleanPredicate1) AbstractBooleanPredicate1.super.xor(other);
    }

    @Override
    default BooleanPredicate1 sub(final BooleanPredicate1 other) {
        return (BooleanPredicate1) AbstractBooleanPredicate1.super.sub(other);
    }

    @Override
    default BooleanPredicate1 nand(final BooleanPredicate1 other) {
        return (BooleanPredicate1) AbstractBooleanPredicate1.super.nand(other);
    }

    @Override
    default BooleanPredicate1 nor(final BooleanPredicate1 other) {
        return (BooleanPredicate1) AbstractBooleanPredicate1.super.nor(other);
    }

    @Override
    default BooleanPredicate1 xnor(final BooleanPredicate1 other) {
        return (BooleanPredicate1) AbstractBooleanPredicate1.super.xnor(other);
    }

    @Override
    default BooleanPredicate1 orNot(final BooleanPredicate1 other) {
        return (BooleanPredicate1) AbstractBooleanPredicate1.super.orNot(other);
    }
}
