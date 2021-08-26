/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.Predicate1;
import net.ashwork.functionality.predicate.PredicateN;
import net.ashwork.functionality.predicate.abstracts.primitive.ints.AbstractIntPredicate1;
import net.ashwork.functionality.primitive.combined.IntToBooleanFunction1;

import java.util.function.IntPredicate;

/**
 * Represents a predicate that accepts an {@code int}-valued argument and produces a {@code int}-valued result.
 * This is the one-arity specialization for {@link PredicateN}.
 * This is the predicate specialization for {@link IntToBooleanFunction1}.
 * This is the {@code int}-consuming primitive specialization of {@link Predicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(int)}.
 *
 * @see PredicateN
 * @see IntToBooleanFunction1
 * @see Predicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface IntPredicate1 extends AbstractIntPredicate1<IntPredicate1> {

    /**
     * Creates an instance of this object from its {@link IntToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see IntToBooleanFunction1
     */
    static IntPredicate1 fromFunctionVariant(final IntToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * Creates an instance of this object from its {@link IntPredicate} variant.
     *
     * @param predicate the variant of this object
     * @return an instance of this object
     *
     * @see IntPredicate
     */
    static Predicate1<Integer> fromVariant(final IntPredicate predicate) {
        return predicate::test;
    }

    /**
     * @see Predicate1
     */
    @Override
    default Predicate1<Integer> boxInput() {
        return this::test;
    }

    /**
     * @see Predicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Predicate1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (Predicate1<V>) AbstractIntPredicate1.super.compose(before);
    }

    /**
     * @see Predicate1
     */
    @Override
    default <V> Predicate1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    @Override
    default IntPredicate1 not() {
        return (final int value) -> !this.test(value);
    }

    @Override
    default IntPredicate1 and(final IntPredicate1 other) {
        return (final int value) -> this.test(value) && other.test(value);
    }

    @Override
    default IntPredicate1 or(final IntPredicate1 other) {
        return (final int value) -> this.test(value) || other.test(value);
    }

    @Override
    default IntPredicate1 xor(final IntPredicate1 other) {
        return (IntPredicate1) AbstractIntPredicate1.super.xor(other);
    }

    @Override
    default IntPredicate1 sub(final IntPredicate1 other) {
        return (IntPredicate1) AbstractIntPredicate1.super.sub(other);
    }

    @Override
    default IntPredicate1 nand(final IntPredicate1 other) {
        return (IntPredicate1) AbstractIntPredicate1.super.nand(other);
    }

    @Override
    default IntPredicate1 nor(final IntPredicate1 other) {
        return (IntPredicate1) AbstractIntPredicate1.super.nor(other);
    }

    @Override
    default IntPredicate1 xnor(final IntPredicate1 other) {
        return (IntPredicate1) AbstractIntPredicate1.super.xnor(other);
    }

    @Override
    default IntPredicate1 orNot(final IntPredicate1 other) {
        return (IntPredicate1) AbstractIntPredicate1.super.orNot(other);
    }
}
