/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.abstracts.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicate1;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicateN;
import net.ashwork.functionality.primitive.chars.CharFunction1;
import net.ashwork.functionality.primitive.combined.CharToBooleanFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts a {@code char}-valued argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link AbstractPredicateN}.
 * This is the predicate specialization for {@link CharToBooleanFunction1}.
 * This is the {@code char}-consuming primitive specialization of {@link AbstractPredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <B> the type of the boxed predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractPredicateN
 * @see CharToBooleanFunction1
 * @see AbstractPredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractCharPredicate1<B extends AbstractPredicate1<Character, B>, P extends AbstractCharPredicate1<B, P>> extends AbstractPredicateN<P>, FunctionVariant<Boolean, CharToBooleanFunction1>, UnboxedInput<AbstractPredicate1<Character, B>> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test(final char value);

    @Override
    default boolean testAllUnchecked(final Object... args) {
        return this.test((char) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see CharToBooleanFunction1
     */
    @Override
    default CharToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see AbstractPredicate1
     */
    @Override
    default AbstractPredicate1<Character, B> boxInput() {
        return this::test;
    }

    /**
     * @see CharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> CharFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (CharFunction1<V>) AbstractPredicateN.super.andThen(after);
    }

    /**
     * @see CharFunction1
     */
    @Override
    default <V> CharFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final char value) -> after.apply(this.test(value));
    }

    @Override
    default AbstractCharPredicate1<B, P> not() {
        return (final char value) -> !this.test(value);
    }

    @Override
    default AbstractCharPredicate1<B, P> and(final P other) {
        return (final char value) -> this.test(value) && other.test(value);
    }

    @Override
    default AbstractCharPredicate1<B, P> or(final P other) {
        return (final char value) -> this.test(value) || other.test(value);
    }

    @Override
    default AbstractCharPredicate1<B, P> xor(final P other) {
        return (AbstractCharPredicate1<B, P>) AbstractPredicateN.super.xor(other);
    }

    @Override
    default AbstractCharPredicate1<B, P> sub(final P other) {
        return (AbstractCharPredicate1<B, P>) AbstractPredicateN.super.sub(other);
    }

    @Override
    default AbstractCharPredicate1<B, P> nand(final P other) {
        return (AbstractCharPredicate1<B, P>) AbstractPredicateN.super.nand(other);
    }

    @Override
    default AbstractCharPredicate1<B, P> nor(final P other) {
        return (AbstractCharPredicate1<B, P>) AbstractPredicateN.super.nor(other);
    }

    @Override
    default AbstractCharPredicate1<B, P> xnor(final P other) {
        return (AbstractCharPredicate1<B, P>) AbstractPredicateN.super.xnor(other);
    }

    @Override
    default AbstractCharPredicate1<B, P> orNot(final P other) {
        return (AbstractCharPredicate1<B, P>) AbstractPredicateN.super.orNot(other);
    }
}
