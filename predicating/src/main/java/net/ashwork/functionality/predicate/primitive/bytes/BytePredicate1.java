/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.Predicate1;
import net.ashwork.functionality.predicate.PredicateN;
import net.ashwork.functionality.predicate.abstracts.primitive.bytes.AbstractBytePredicate1;
import net.ashwork.functionality.primitive.combined.ByteToBooleanFunction1;

/**
 * Represents a predicate that accepts a {@code byte}-valued argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link PredicateN}.
 * This is the predicate specialization for {@link ByteToBooleanFunction1}.
 * This is the {@code byte}-consuming primitive specialization of {@link Predicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(byte)}.
 *
 * @see PredicateN
 * @see ByteToBooleanFunction1
 * @see Predicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface BytePredicate1 extends AbstractBytePredicate1<BytePredicate1> {

    /**
     * Creates an instance of this object from its {@link ByteToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ByteToBooleanFunction1
     */
    static BytePredicate1 fromFunctionVariant(final ByteToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see Predicate1
     */
    @Override
    default Predicate1<Byte> boxInput() {
        return this::test;
    }

    /**
     * @see Predicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Predicate1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (Predicate1<V>) AbstractBytePredicate1.super.compose(before);
    }

    /**
     * @see Predicate1
     */
    @Override
    default <V> Predicate1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    @Override
    default BytePredicate1 not() {
        return (final byte value) -> !this.test(value);
    }

    @Override
    default BytePredicate1 and(final BytePredicate1 other) {
        return (final byte value) -> this.test(value) && other.test(value);
    }

    @Override
    default BytePredicate1 or(final BytePredicate1 other) {
        return (final byte value) -> this.test(value) || other.test(value);
    }

    @Override
    default BytePredicate1 xor(final BytePredicate1 other) {
        return (BytePredicate1) AbstractBytePredicate1.super.xor(other);
    }

    @Override
    default BytePredicate1 sub(final BytePredicate1 other) {
        return (BytePredicate1) AbstractBytePredicate1.super.sub(other);
    }

    @Override
    default BytePredicate1 nand(final BytePredicate1 other) {
        return (BytePredicate1) AbstractBytePredicate1.super.nand(other);
    }

    @Override
    default BytePredicate1 nor(final BytePredicate1 other) {
        return (BytePredicate1) AbstractBytePredicate1.super.nor(other);
    }

    @Override
    default BytePredicate1 xnor(final BytePredicate1 other) {
        return (BytePredicate1) AbstractBytePredicate1.super.xnor(other);
    }

    @Override
    default BytePredicate1 orNot(final BytePredicate1 other) {
        return (BytePredicate1) AbstractBytePredicate1.super.orNot(other);
    }
}
