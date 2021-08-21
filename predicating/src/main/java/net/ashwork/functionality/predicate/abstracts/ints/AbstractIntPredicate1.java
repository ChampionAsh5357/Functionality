/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.abstracts.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicate1;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicateN;
import net.ashwork.functionality.primitive.combined.IntToBooleanFunction1;
import net.ashwork.functionality.primitive.ints.IntFunction1;
import net.ashwork.functionality.util.InheritOnly;

import java.util.function.IntPredicate;

/**
 * Represents a predicate that accepts an {@code int}-valued argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link AbstractPredicateN}.
 * This is the predicate specialization for {@link IntToBooleanFunction1}.
 * This is the {@code int}-consuming primitive specialization of {@link AbstractPredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <B> the type of the boxed predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractPredicateN
 * @see IntToBooleanFunction1
 * @see AbstractPredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractIntPredicate1<B extends AbstractPredicate1<Integer, B>, P extends AbstractIntPredicate1<B, P>> extends AbstractPredicateN<P>, InputChainableInput<Integer>, Variant<IntPredicate>, FunctionVariant<Boolean, IntToBooleanFunction1>, UnboxedInput<AbstractPredicate1<Integer, B>> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test(final int value);

    @Override
    default boolean testAllUnchecked(final Object... args) {
        return this.test((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see IntToBooleanFunction1
     */
    @Override
    default IntToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see IntPredicate
     */
    @Override
    default IntPredicate toVariant() {
        return this::test;
    }

    /**
     * @see AbstractPredicate1
     */
    @Override
    AbstractPredicate1<Integer, B> boxInput();

    /**
     * @see AbstractPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractPredicate1<V, ?> compose(final Function1<? super V, ? extends Integer> before) {
        return (AbstractPredicate1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractPredicate1
     */
    @Override
    <V> AbstractPredicate1<V, ?> composeUnchecked(final Function1<? super V, ? extends Integer> before);

    /**
     * @see IntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> IntFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (IntFunction1<V>) AbstractPredicateN.super.andThen(after);
    }

    /**
     * @see IntFunction1
     */
    @Override
    default <V> IntFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final int value) -> after.apply(this.test(value));
    }

    @Override
    AbstractIntPredicate1<B, P> not();

    @Override
    AbstractIntPredicate1<B, P> and(final P other);

    @Override
    AbstractIntPredicate1<B, P> or(final P other);

    @Override
    default AbstractIntPredicate1<B, P> xor(final P other) {
        return (AbstractIntPredicate1<B, P>) AbstractPredicateN.super.xor(other);
    }

    @Override
    default AbstractIntPredicate1<B, P> sub(final P other) {
        return (AbstractIntPredicate1<B, P>) AbstractPredicateN.super.sub(other);
    }

    @Override
    default AbstractIntPredicate1<B, P> nand(final P other) {
        return (AbstractIntPredicate1<B, P>) AbstractPredicateN.super.nand(other);
    }

    @Override
    default AbstractIntPredicate1<B, P> nor(final P other) {
        return (AbstractIntPredicate1<B, P>) AbstractPredicateN.super.nor(other);
    }

    @Override
    default AbstractIntPredicate1<B, P> xnor(final P other) {
        return (AbstractIntPredicate1<B, P>) AbstractPredicateN.super.xnor(other);
    }

    @Override
    default AbstractIntPredicate1<B, P> orNot(final P other) {
        return (AbstractIntPredicate1<B, P>) AbstractPredicateN.super.orNot(other);
    }
}
