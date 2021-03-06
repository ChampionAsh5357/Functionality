/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.abstracts.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicate1;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicateN;
import net.ashwork.functionality.primitive.combined.LongToBooleanFunction1;
import net.ashwork.functionality.primitive.longs.LongFunction1;
import net.ashwork.functionality.util.InheritOnly;

import java.util.function.LongPredicate;

/**
 * Represents a predicate that accepts a {@code long}-valued argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link AbstractPredicateN}.
 * This is the predicate specialization for {@link LongToBooleanFunction1}.
 * This is the {@code long}-consuming primitive specialization of {@link AbstractPredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <P> the type of this predicate
 *
 * @see AbstractPredicateN
 * @see LongToBooleanFunction1
 * @see AbstractPredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractLongPredicate1<P extends AbstractLongPredicate1<P>> extends AbstractPredicateN<P>, InputChainableInput<Long>, Variant<LongPredicate>, FunctionVariant<Boolean, LongToBooleanFunction1>, UnboxedInput<AbstractPredicate1<Long, ?>> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test(final long value);

    @Override
    default boolean testAllUnchecked(final Object... args) {
        return this.test((long) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see LongToBooleanFunction1
     */
    @Override
    default LongToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see LongPredicate
     */
    @Override
    default LongPredicate toVariant() {
        return this::test;
    }

    /**
     * @see AbstractPredicate1
     */
    @Override
    AbstractPredicate1<Long, ?> boxInput();

    /**
     * @see AbstractPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractPredicate1<V, ?> compose(final Function1<? super V, ? extends Long> before) {
        return (AbstractPredicate1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractPredicate1
     */
    @Override
    <V> AbstractPredicate1<V, ?> composeUnchecked(final Function1<? super V, ? extends Long> before);

    /**
     * @see LongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> LongFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (LongFunction1<V>) AbstractPredicateN.super.andThen(after);
    }

    /**
     * @see LongFunction1
     */
    @Override
    default <V> LongFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final long value) -> after.apply(this.test(value));
    }

    @Override
    AbstractLongPredicate1<P> not();

    @Override
    AbstractLongPredicate1<P> and(final P other);

    @Override
    AbstractLongPredicate1<P> or(final P other);

    @Override
    default AbstractLongPredicate1<P> xor(final P other) {
        return (AbstractLongPredicate1<P>) AbstractPredicateN.super.xor(other);
    }

    @Override
    default AbstractLongPredicate1<P> sub(final P other) {
        return (AbstractLongPredicate1<P>) AbstractPredicateN.super.sub(other);
    }

    @Override
    default AbstractLongPredicate1<P> nand(final P other) {
        return (AbstractLongPredicate1<P>) AbstractPredicateN.super.nand(other);
    }

    @Override
    default AbstractLongPredicate1<P> nor(final P other) {
        return (AbstractLongPredicate1<P>) AbstractPredicateN.super.nor(other);
    }

    @Override
    default AbstractLongPredicate1<P> xnor(final P other) {
        return (AbstractLongPredicate1<P>) AbstractPredicateN.super.xnor(other);
    }

    @Override
    default AbstractLongPredicate1<P> orNot(final P other) {
        return (AbstractLongPredicate1<P>) AbstractPredicateN.super.orNot(other);
    }
}
