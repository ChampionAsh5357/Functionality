/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.Predicate1;
import net.ashwork.functionality.predicate.PredicateN;
import net.ashwork.functionality.predicate.abstracts.primitive.longs.AbstractLongPredicate1;
import net.ashwork.functionality.primitive.combined.LongToBooleanFunction1;

import java.util.function.LongPredicate;

/**
 * Represents a predicate that accepts a {@code long}-valued argument and produces a {@code long}-valued result.
 * This is the one-arity specialization for {@link PredicateN}.
 * This is the predicate specialization for {@link LongToBooleanFunction1}.
 * This is the {@code long}-consuming primitive specialization of {@link Predicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(long)}.
 *
 * @see PredicateN
 * @see LongToBooleanFunction1
 * @see Predicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface LongPredicate1 extends AbstractLongPredicate1<LongPredicate1> {

    /**
     * Creates an instance of this object from its {@link LongToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see LongToBooleanFunction1
     */
    static LongPredicate1 fromFunctionVariant(final LongToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * Creates an instance of this object from its {@link LongPredicate} variant.
     *
     * @param predicate the variant of this object
     * @return an instance of this object
     *
     * @see LongPredicate
     */
    static Predicate1<Long> fromVariant(final LongPredicate predicate) {
        return predicate::test;
    }

    /**
     * @see Predicate1
     */
    @Override
    default Predicate1<Long> boxInput() {
        return this::test;
    }

    /**
     * @see Predicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Predicate1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (Predicate1<V>) AbstractLongPredicate1.super.compose(before);
    }

    /**
     * @see Predicate1
     */
    @Override
    default <V> Predicate1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    @Override
    default LongPredicate1 not() {
        return (final long value) -> !this.test(value);
    }

    @Override
    default LongPredicate1 and(final LongPredicate1 other) {
        return (final long value) -> this.test(value) && other.test(value);
    }

    @Override
    default LongPredicate1 or(final LongPredicate1 other) {
        return (final long value) -> this.test(value) || other.test(value);
    }

    @Override
    default LongPredicate1 xor(final LongPredicate1 other) {
        return (LongPredicate1) AbstractLongPredicate1.super.xor(other);
    }

    @Override
    default LongPredicate1 sub(final LongPredicate1 other) {
        return (LongPredicate1) AbstractLongPredicate1.super.sub(other);
    }

    @Override
    default LongPredicate1 nand(final LongPredicate1 other) {
        return (LongPredicate1) AbstractLongPredicate1.super.nand(other);
    }

    @Override
    default LongPredicate1 nor(final LongPredicate1 other) {
        return (LongPredicate1) AbstractLongPredicate1.super.nor(other);
    }

    @Override
    default LongPredicate1 xnor(final LongPredicate1 other) {
        return (LongPredicate1) AbstractLongPredicate1.super.xnor(other);
    }

    @Override
    default LongPredicate1 orNot(final LongPredicate1 other) {
        return (LongPredicate1) AbstractLongPredicate1.super.orNot(other);
    }
}
