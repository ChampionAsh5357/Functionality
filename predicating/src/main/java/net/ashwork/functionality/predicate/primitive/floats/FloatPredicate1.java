/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.primitive.floats;

import net.ashwork.functionality.predicate.Predicate1;
import net.ashwork.functionality.predicate.PredicateN;
import net.ashwork.functionality.predicate.abstracts.floats.AbstractFloatPredicate1;
import net.ashwork.functionality.primitive.combined.FloatToBooleanFunction1;

/**
 * Represents a predicate that accepts a {@code float}-valued argument and produces a {@code float}-valued result.
 * This is the one-arity specialization for {@link PredicateN}.
 * This is the predicate specialization for {@link FloatToBooleanFunction1}.
 * This is the {@code float}-consuming primitive specialization of {@link Predicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(float)}.
 *
 * @see PredicateN
 * @see FloatToBooleanFunction1
 * @see Predicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface FloatPredicate1 extends AbstractFloatPredicate1<Predicate1<Float>, FloatPredicate1> {

    /**
     * Creates an instance of this object from its {@link FloatToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see FloatToBooleanFunction1
     */
    static FloatPredicate1 fromFunctionVariant(final FloatToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see Predicate1
     */
    @Override
    default Predicate1<Float> boxInput() {
        return this::test;
    }

    @Override
    default FloatPredicate1 not() {
        return (final float value) -> !this.test(value);
    }

    @Override
    default FloatPredicate1 and(final FloatPredicate1 other) {
        return (final float value) -> this.test(value) && other.test(value);
    }

    @Override
    default FloatPredicate1 or(final FloatPredicate1 other) {
        return (final float value) -> this.test(value) || other.test(value);
    }

    @Override
    default FloatPredicate1 xor(final FloatPredicate1 other) {
        return (FloatPredicate1) AbstractFloatPredicate1.super.xor(other);
    }

    @Override
    default FloatPredicate1 sub(final FloatPredicate1 other) {
        return (FloatPredicate1) AbstractFloatPredicate1.super.sub(other);
    }

    @Override
    default FloatPredicate1 nand(final FloatPredicate1 other) {
        return (FloatPredicate1) AbstractFloatPredicate1.super.nand(other);
    }

    @Override
    default FloatPredicate1 nor(final FloatPredicate1 other) {
        return (FloatPredicate1) AbstractFloatPredicate1.super.nor(other);
    }

    @Override
    default FloatPredicate1 xnor(final FloatPredicate1 other) {
        return (FloatPredicate1) AbstractFloatPredicate1.super.xnor(other);
    }

    @Override
    default FloatPredicate1 orNot(final FloatPredicate1 other) {
        return (FloatPredicate1) AbstractFloatPredicate1.super.orNot(other);
    }
}
