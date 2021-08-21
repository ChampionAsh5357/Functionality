/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.Predicate1;
import net.ashwork.functionality.predicate.PredicateN;
import net.ashwork.functionality.predicate.abstracts.chars.AbstractCharPredicate1;
import net.ashwork.functionality.primitive.combined.CharToBooleanFunction1;

/**
 * Represents a predicate that accepts a {@code char}-valued argument and produces a {@code char}-valued result.
 * This is the one-arity specialization for {@link PredicateN}.
 * This is the predicate specialization for {@link CharToBooleanFunction1}.
 * This is the {@code char}-consuming primitive specialization of {@link Predicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(char)}.
 *
 * @see PredicateN
 * @see CharToBooleanFunction1
 * @see Predicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface CharPredicate1 extends AbstractCharPredicate1<Predicate1<Character>, CharPredicate1> {

    /**
     * Creates an instance of this object from its {@link CharToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see CharToBooleanFunction1
     */
    static CharPredicate1 fromFunctionVariant(final CharToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see Predicate1
     */
    @Override
    default Predicate1<Character> boxInput() {
        return this::test;
    }

    /**
     * @see Predicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Predicate1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (Predicate1<V>) AbstractCharPredicate1.super.compose(before);
    }

    /**
     * @see Predicate1
     */
    @Override
    default <V> Predicate1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    @Override
    default CharPredicate1 not() {
        return (final char value) -> !this.test(value);
    }

    @Override
    default CharPredicate1 and(final CharPredicate1 other) {
        return (final char value) -> this.test(value) && other.test(value);
    }

    @Override
    default CharPredicate1 or(final CharPredicate1 other) {
        return (final char value) -> this.test(value) || other.test(value);
    }

    @Override
    default CharPredicate1 xor(final CharPredicate1 other) {
        return (CharPredicate1) AbstractCharPredicate1.super.xor(other);
    }

    @Override
    default CharPredicate1 sub(final CharPredicate1 other) {
        return (CharPredicate1) AbstractCharPredicate1.super.sub(other);
    }

    @Override
    default CharPredicate1 nand(final CharPredicate1 other) {
        return (CharPredicate1) AbstractCharPredicate1.super.nand(other);
    }

    @Override
    default CharPredicate1 nor(final CharPredicate1 other) {
        return (CharPredicate1) AbstractCharPredicate1.super.nor(other);
    }

    @Override
    default CharPredicate1 xnor(final CharPredicate1 other) {
        return (CharPredicate1) AbstractCharPredicate1.super.xnor(other);
    }

    @Override
    default CharPredicate1 orNot(final CharPredicate1 other) {
        return (CharPredicate1) AbstractCharPredicate1.super.orNot(other);
    }
}
