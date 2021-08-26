/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.Predicate1;
import net.ashwork.functionality.predicate.PredicateN;
import net.ashwork.functionality.predicate.abstracts.primitive.doubles.AbstractDoublePredicate1;
import net.ashwork.functionality.primitive.combined.DoubleToBooleanFunction1;

import java.util.function.DoublePredicate;

/**
 * Represents a predicate that accepts a {@code double}-valued argument and produces a {@code double}-valued result.
 * This is the one-arity specialization for {@link PredicateN}.
 * This is the predicate specialization for {@link DoubleToBooleanFunction1}.
 * This is the {@code double}-consuming primitive specialization of {@link Predicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(double)}.
 *
 * @see PredicateN
 * @see DoubleToBooleanFunction1
 * @see Predicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface DoublePredicate1 extends AbstractDoublePredicate1<DoublePredicate1> {

    /**
     * Creates an instance of this object from its {@link DoubleToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see DoubleToBooleanFunction1
     */
    static DoublePredicate1 fromFunctionVariant(final DoubleToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * Creates an instance of this object from its {@link DoublePredicate} variant.
     *
     * @param predicate the variant of this object
     * @return an instance of this object
     *
     * @see DoublePredicate
     */
    static Predicate1<Double> fromVariant(final DoublePredicate predicate) {
        return predicate::test;
    }

    /**
     * @see Predicate1
     */
    @Override
    default Predicate1<Double> boxInput() {
        return this::test;
    }

    /**
     * @see Predicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Predicate1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (Predicate1<V>) AbstractDoublePredicate1.super.compose(before);
    }

    /**
     * @see Predicate1
     */
    @Override
    default <V> Predicate1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    @Override
    default DoublePredicate1 not() {
        return (final double value) -> !this.test(value);
    }

    @Override
    default DoublePredicate1 and(final DoublePredicate1 other) {
        return (final double value) -> this.test(value) && other.test(value);
    }

    @Override
    default DoublePredicate1 or(final DoublePredicate1 other) {
        return (final double value) -> this.test(value) || other.test(value);
    }

    @Override
    default DoublePredicate1 xor(final DoublePredicate1 other) {
        return (DoublePredicate1) AbstractDoublePredicate1.super.xor(other);
    }

    @Override
    default DoublePredicate1 sub(final DoublePredicate1 other) {
        return (DoublePredicate1) AbstractDoublePredicate1.super.sub(other);
    }

    @Override
    default DoublePredicate1 nand(final DoublePredicate1 other) {
        return (DoublePredicate1) AbstractDoublePredicate1.super.nand(other);
    }

    @Override
    default DoublePredicate1 nor(final DoublePredicate1 other) {
        return (DoublePredicate1) AbstractDoublePredicate1.super.nor(other);
    }

    @Override
    default DoublePredicate1 xnor(final DoublePredicate1 other) {
        return (DoublePredicate1) AbstractDoublePredicate1.super.xnor(other);
    }

    @Override
    default DoublePredicate1 orNot(final DoublePredicate1 other) {
        return (DoublePredicate1) AbstractDoublePredicate1.super.orNot(other);
    }
}
