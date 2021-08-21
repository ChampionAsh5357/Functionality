/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.abstracts.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicate1;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicateN;
import net.ashwork.functionality.primitive.combined.FloatToBooleanFunction1;
import net.ashwork.functionality.primitive.floats.FloatFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts a {@code float}-valued argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link AbstractPredicateN}.
 * This is the predicate specialization for {@link FloatToBooleanFunction1}.
 * This is the {@code float}-consuming primitive specialization of {@link AbstractPredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <B> the type of the boxed predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractPredicateN
 * @see FloatToBooleanFunction1
 * @see AbstractPredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractFloatPredicate1<B extends AbstractPredicate1<Float, B>, P extends AbstractFloatPredicate1<B, P>> extends AbstractPredicateN<P>, InputChainableInput<Float>, FunctionVariant<Boolean, FloatToBooleanFunction1>, UnboxedInput<AbstractPredicate1<Float, B>> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test(final float value);

    @Override
    default boolean testAllUnchecked(final Object... args) {
        return this.test((float) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see FloatToBooleanFunction1
     */
    @Override
    default FloatToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see AbstractPredicate1
     */
    @Override
    AbstractPredicate1<Float, B> boxInput();

    /**
     * @see AbstractPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractPredicate1<V, ?> compose(final Function1<? super V, ? extends Float> before) {
        return (AbstractPredicate1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractPredicate1
     */
    @Override
    <V> AbstractPredicate1<V, ?> composeUnchecked(final Function1<? super V, ? extends Float> before);

    /**
     * @see FloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> FloatFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (FloatFunction1<V>) AbstractPredicateN.super.andThen(after);
    }

    /**
     * @see FloatFunction1
     */
    @Override
    default <V> FloatFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final float value) -> after.apply(this.test(value));
    }

    @Override
    AbstractFloatPredicate1<B, P> not();

    @Override
    AbstractFloatPredicate1<B, P> and(final P other);

    @Override
    AbstractFloatPredicate1<B, P> or(final P other);

    @Override
    default AbstractFloatPredicate1<B, P> xor(final P other) {
        return (AbstractFloatPredicate1<B, P>) AbstractPredicateN.super.xor(other);
    }

    @Override
    default AbstractFloatPredicate1<B, P> sub(final P other) {
        return (AbstractFloatPredicate1<B, P>) AbstractPredicateN.super.sub(other);
    }

    @Override
    default AbstractFloatPredicate1<B, P> nand(final P other) {
        return (AbstractFloatPredicate1<B, P>) AbstractPredicateN.super.nand(other);
    }

    @Override
    default AbstractFloatPredicate1<B, P> nor(final P other) {
        return (AbstractFloatPredicate1<B, P>) AbstractPredicateN.super.nor(other);
    }

    @Override
    default AbstractFloatPredicate1<B, P> xnor(final P other) {
        return (AbstractFloatPredicate1<B, P>) AbstractPredicateN.super.xnor(other);
    }

    @Override
    default AbstractFloatPredicate1<B, P> orNot(final P other) {
        return (AbstractFloatPredicate1<B, P>) AbstractPredicateN.super.orNot(other);
    }
}
