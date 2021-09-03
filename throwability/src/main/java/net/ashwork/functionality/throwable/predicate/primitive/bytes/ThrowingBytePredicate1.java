/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.predicate.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.primitive.bytes.BytePredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.primitive.bytes.AbstractThrowingBytePredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicateN;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingByteFunction1;
import net.ashwork.functionality.throwable.primitive.combined.ThrowingByteToBooleanFunction1;

/**
 * Represents a predicate that accepts a {@code byte}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link ThrowingPredicateN}.
 * This is the predicate specialization for {@link ThrowingByteToBooleanFunction1}.
 * This is the {@code byte}-consuming primitive specialization of {@link ThrowingPredicate1}.
 * This is the throwing variation of {@link BytePredicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(byte)}.
 *
 * @see ThrowingPredicateN
 * @see ThrowingByteToBooleanFunction1
 * @see ThrowingPredicate1
 * @see BytePredicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingBytePredicate1 extends AbstractThrowingBytePredicate1<AbstractThrowingBytePredicate1.Handler, ThrowingBytePredicate1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see BytePredicate1
     */
    static ThrowingBytePredicate1 from(final BytePredicate1 predicate) {
        return predicate::test;
    }

    /**
     * Creates an instance of this object from its {@link ThrowingByteToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ThrowingByteToBooleanFunction1
     */
    static ThrowingBytePredicate1 fromFunctionVariant(final ThrowingByteToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingByteToBooleanFunction1
     */
    @Override
    default ThrowingByteToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default ThrowingPredicate1<Byte> boxInput() {
        return this::test;
    }

    /**
     * @see BytePredicate1
     */
    @Override
    default BytePredicate1 handle(final Handler handler) {
        return (final byte value) -> {
            try {
                return this.test(value);
            } catch (final Throwable t) {
                return handler.testThrown(t, value);
            }
        };
    }

    /**
     * @see BytePredicate1
     */
    @Override
    default BytePredicate1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingPredicate1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ThrowingPredicate1<V>) AbstractThrowingBytePredicate1.super.compose(before);
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default <V> ThrowingPredicate1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    /**
     * @see ThrowingByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingByteFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingByteFunction1<V>) AbstractThrowingBytePredicate1.super.andThen(after);
    }

    /**
     * @see ThrowingByteFunction1
     */
    @Override
    default <V> ThrowingByteFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final byte value) -> after.apply(this.test(value));
    }

    @Override
    default ThrowingBytePredicate1 not() {
        return (final byte value) -> !this.test(value);
    }

    @Override
    default ThrowingBytePredicate1 and(final ThrowingBytePredicate1 other) {
        return (final byte value) -> this.test(value) && other.test(value);
    }

    @Override
    default ThrowingBytePredicate1 or(final ThrowingBytePredicate1 other) {
        return (final byte value) -> this.test(value) || other.test(value);
    }

    @Override
    default ThrowingBytePredicate1 xor(final ThrowingBytePredicate1 other) {
        return (ThrowingBytePredicate1) AbstractThrowingBytePredicate1.super.xor(other);
    }

    @Override
    default ThrowingBytePredicate1 sub(final ThrowingBytePredicate1 other) {
        return (ThrowingBytePredicate1) AbstractThrowingBytePredicate1.super.sub(other);
    }

    @Override
    default ThrowingBytePredicate1 nand(final ThrowingBytePredicate1 other) {
        return (ThrowingBytePredicate1) AbstractThrowingBytePredicate1.super.nand(other);
    }

    @Override
    default ThrowingBytePredicate1 nor(final ThrowingBytePredicate1 other) {
        return (ThrowingBytePredicate1) AbstractThrowingBytePredicate1.super.nor(other);
    }

    @Override
    default ThrowingBytePredicate1 xnor(final ThrowingBytePredicate1 other) {
        return (ThrowingBytePredicate1) AbstractThrowingBytePredicate1.super.xnor(other);
    }

    @Override
    default ThrowingBytePredicate1 orNot(final ThrowingBytePredicate1 other) {
        return (ThrowingBytePredicate1) AbstractThrowingBytePredicate1.super.orNot(other);
    }
}
