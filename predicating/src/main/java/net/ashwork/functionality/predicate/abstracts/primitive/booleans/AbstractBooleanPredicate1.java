/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.abstracts.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.booleans.BooleanOperator1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicate1;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicateN;
import net.ashwork.functionality.primitive.booleans.BooleanFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts a {@code boolean}-valued argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link AbstractPredicateN}.
 * This is the predicate specialization for {@link BooleanOperator1}.
 * This is the {@code boolean}-consuming primitive specialization of {@link AbstractPredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <P> the type of this predicate
 *
 * @see AbstractPredicateN
 * @see BooleanOperator1
 * @see AbstractPredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractBooleanPredicate1<P extends AbstractBooleanPredicate1<P>> extends AbstractPredicateN<P>, InputChainableInput<Boolean>, FunctionVariant<Boolean, BooleanOperator1>, UnboxedInput<AbstractPredicate1<Boolean, ?>> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test(final boolean value);

    @Override
    default boolean testAllUnchecked(final Object... args) {
        return this.test((boolean) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see BooleanOperator1
     */
    @Override
    default BooleanOperator1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see AbstractPredicate1
     */
    @Override
    AbstractPredicate1<Boolean, ?> boxInput();

    /**
     * @see AbstractPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractPredicate1<V, ?> compose(final Function1<? super V, ? extends Boolean> before) {
        return (AbstractPredicate1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractPredicate1
     */
    @Override
    <V> AbstractPredicate1<V, ?> composeUnchecked(final Function1<? super V, ? extends Boolean> before);

    /**
     * @see BooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> BooleanFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (BooleanFunction1<V>) AbstractPredicateN.super.andThen(after);
    }

    /**
     * @see BooleanFunction1
     */
    @Override
    default <V> BooleanFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final boolean value) -> after.apply(this.test(value));
    }

    @Override
    AbstractBooleanPredicate1<P> not();

    @Override
    AbstractBooleanPredicate1<P> and(final P other);

    @Override
    AbstractBooleanPredicate1<P> or(final P other);

    @Override
    default AbstractBooleanPredicate1<P> xor(final P other) {
        return (AbstractBooleanPredicate1<P>) AbstractPredicateN.super.xor(other);
    }

    @Override
    default AbstractBooleanPredicate1<P> sub(final P other) {
        return (AbstractBooleanPredicate1<P>) AbstractPredicateN.super.sub(other);
    }

    @Override
    default AbstractBooleanPredicate1<P> nand(final P other) {
        return (AbstractBooleanPredicate1<P>) AbstractPredicateN.super.nand(other);
    }

    @Override
    default AbstractBooleanPredicate1<P> nor(final P other) {
        return (AbstractBooleanPredicate1<P>) AbstractPredicateN.super.nor(other);
    }

    @Override
    default AbstractBooleanPredicate1<P> xnor(final P other) {
        return (AbstractBooleanPredicate1<P>) AbstractPredicateN.super.xnor(other);
    }

    @Override
    default AbstractBooleanPredicate1<P> orNot(final P other) {
        return (AbstractBooleanPredicate1<P>) AbstractPredicateN.super.orNot(other);
    }
}
