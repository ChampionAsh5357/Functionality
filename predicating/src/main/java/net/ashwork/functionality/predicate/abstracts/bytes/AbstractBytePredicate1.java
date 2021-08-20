/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.abstracts.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicate1;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicateN;
import net.ashwork.functionality.primitive.bytes.ByteFunction1;
import net.ashwork.functionality.primitive.combined.ByteToBooleanFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts a {@code byte}-valued argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link AbstractPredicateN}.
 * This is the predicate specialization for {@link ByteToBooleanFunction1}.
 * This is the {@code byte}-consuming primitive specialization of {@link AbstractPredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <B> the type of the boxed predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractPredicateN
 * @see ByteToBooleanFunction1
 * @see AbstractPredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractBytePredicate1<B extends AbstractPredicate1<Byte, B>, P extends AbstractBytePredicate1<B, P>> extends AbstractPredicateN<P>, FunctionVariant<Boolean, ByteToBooleanFunction1>, UnboxedInput<AbstractPredicate1<Byte, B>> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test(final byte value);

    @Override
    default boolean testAllUnchecked(final Object... args) {
        return this.test((byte) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see ByteToBooleanFunction1
     */
    @Override
    default ByteToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see AbstractPredicate1
     */
    @Override
    default AbstractPredicate1<Byte, B> boxInput() {
        return this::test;
    }

    /**
     * @see ByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ByteFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ByteFunction1<V>) AbstractPredicateN.super.andThen(after);
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default <V> ByteFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final byte value) -> after.apply(this.test(value));
    }

    @Override
    default AbstractBytePredicate1<B, P> not() {
        return (final byte value) -> !this.test(value);
    }

    @Override
    default AbstractBytePredicate1<B, P> and(final P other) {
        return (final byte value) -> this.test(value) && other.test(value);
    }

    @Override
    default AbstractBytePredicate1<B, P> or(final P other) {
        return (final byte value) -> this.test(value) || other.test(value);
    }

    @Override
    default AbstractBytePredicate1<B, P> xor(final P other) {
        return (AbstractBytePredicate1<B, P>) AbstractPredicateN.super.xor(other);
    }

    @Override
    default AbstractBytePredicate1<B, P> sub(final P other) {
        return (AbstractBytePredicate1<B, P>) AbstractPredicateN.super.sub(other);
    }

    @Override
    default AbstractBytePredicate1<B, P> nand(final P other) {
        return (AbstractBytePredicate1<B, P>) AbstractPredicateN.super.nand(other);
    }

    @Override
    default AbstractBytePredicate1<B, P> nor(final P other) {
        return (AbstractBytePredicate1<B, P>) AbstractPredicateN.super.nor(other);
    }

    @Override
    default AbstractBytePredicate1<B, P> xnor(final P other) {
        return (AbstractBytePredicate1<B, P>) AbstractPredicateN.super.xnor(other);
    }

    @Override
    default AbstractBytePredicate1<B, P> orNot(final P other) {
        return (AbstractBytePredicate1<B, P>) AbstractPredicateN.super.orNot(other);
    }
}
