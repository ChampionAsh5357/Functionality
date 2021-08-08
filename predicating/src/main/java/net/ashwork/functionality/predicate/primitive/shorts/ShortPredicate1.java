/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.primitive.shorts;

import net.ashwork.functionality.predicate.Predicate1;
import net.ashwork.functionality.predicate.PredicateN;
import net.ashwork.functionality.predicate.abstracts.AbstractShortPredicate1;
import net.ashwork.functionality.primitive.combined.ShortToBooleanFunction1;

/**
 * Represents a predicate that accepts a {@code short}-valued argument and produces a {@code short}-valued result.
 * This is the one-arity specialization for {@link PredicateN}.
 * This is the predicate specialization for {@link ShortToBooleanFunction1}.
 * This is the {@code short}-consuming primitive specialization of {@link Predicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(short)}.
 *
 * @see PredicateN
 * @see ShortToBooleanFunction1
 * @see Predicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ShortPredicate1 extends AbstractShortPredicate1<Predicate1<Short>, ShortPredicate1> {

    /**
     * Creates an instance of this object from its {@link ShortToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ShortToBooleanFunction1
     */
    static ShortPredicate1 fromFunctionVariant(final ShortToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see Predicate1
     */
    @Override
    default Predicate1<Short> boxInput() {
        return this::test;
    }

    @Override
    default ShortPredicate1 not() {
        return (final short value) -> !this.test(value);
    }

    @Override
    default ShortPredicate1 and(final ShortPredicate1 other) {
        return (final short value) -> this.test(value) && other.test(value);
    }

    @Override
    default ShortPredicate1 or(final ShortPredicate1 other) {
        return (final short value) -> this.test(value) || other.test(value);
    }

    @Override
    default ShortPredicate1 xor(final ShortPredicate1 other) {
        return (ShortPredicate1) AbstractShortPredicate1.super.xor(other);
    }

    @Override
    default ShortPredicate1 sub(final ShortPredicate1 other) {
        return (ShortPredicate1) AbstractShortPredicate1.super.sub(other);
    }

    @Override
    default ShortPredicate1 nand(final ShortPredicate1 other) {
        return (ShortPredicate1) AbstractShortPredicate1.super.nand(other);
    }

    @Override
    default ShortPredicate1 nor(final ShortPredicate1 other) {
        return (ShortPredicate1) AbstractShortPredicate1.super.nor(other);
    }

    @Override
    default ShortPredicate1 xnor(final ShortPredicate1 other) {
        return (ShortPredicate1) AbstractShortPredicate1.super.xnor(other);
    }

    @Override
    default ShortPredicate1 orNot(final ShortPredicate1 other) {
        return (ShortPredicate1) AbstractShortPredicate1.super.orNot(other);
    }
}