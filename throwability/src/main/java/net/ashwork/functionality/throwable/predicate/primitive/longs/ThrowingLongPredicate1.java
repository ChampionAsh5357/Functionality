/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.predicate.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.primitive.longs.LongPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.primitive.longs.AbstractThrowingLongPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicateN;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingLongFunction1;
import net.ashwork.functionality.throwable.primitive.combined.ThrowingLongToBooleanFunction1;

/**
 * Represents a predicate that accepts a {@code long}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link ThrowingPredicateN}.
 * This is the predicate specialization for {@link ThrowingLongToBooleanFunction1}.
 * This is the {@code long}-consuming primitive specialization of {@link ThrowingPredicate1}.
 * This is the throwing variation of {@link LongPredicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(long)}.
 *
 * @see ThrowingPredicateN
 * @see ThrowingLongToBooleanFunction1
 * @see ThrowingPredicate1
 * @see LongPredicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingLongPredicate1 extends AbstractThrowingLongPredicate1<AbstractThrowingLongPredicate1.Handler, ThrowingLongPredicate1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see LongPredicate1
     */
    static ThrowingLongPredicate1 from(final LongPredicate1 predicate) {
        return predicate::test;
    }

    /**
     * Creates an instance of this object from its {@link ThrowingLongToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ThrowingLongToBooleanFunction1
     */
    static ThrowingLongPredicate1 fromFunctionVariant(final ThrowingLongToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingLongToBooleanFunction1
     */
    @Override
    default ThrowingLongToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default ThrowingPredicate1<Long> boxInput() {
        return this::test;
    }

    /**
     * @see LongPredicate1
     */
    @Override
    default LongPredicate1 handle(final Handler handler) {
        return (final long value) -> {
            try {
                return this.test(value);
            } catch (final Throwable t) {
                return handler.testThrown(t, value);
            }
        };
    }

    /**
     * @see LongPredicate1
     */
    @Override
    default LongPredicate1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingPredicate1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ThrowingPredicate1<V>) AbstractThrowingLongPredicate1.super.compose(before);
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default <V> ThrowingPredicate1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    /**
     * @see ThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingLongFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingLongFunction1<V>) AbstractThrowingLongPredicate1.super.andThen(after);
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default <V> ThrowingLongFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final long value) -> after.apply(this.test(value));
    }

    @Override
    default ThrowingLongPredicate1 not() {
        return (final long value) -> !this.test(value);
    }

    @Override
    default ThrowingLongPredicate1 and(final ThrowingLongPredicate1 other) {
        return (final long value) -> this.test(value) && other.test(value);
    }

    @Override
    default ThrowingLongPredicate1 or(final ThrowingLongPredicate1 other) {
        return (final long value) -> this.test(value) || other.test(value);
    }

    @Override
    default ThrowingLongPredicate1 xor(final ThrowingLongPredicate1 other) {
        return (ThrowingLongPredicate1) AbstractThrowingLongPredicate1.super.xor(other);
    }

    @Override
    default ThrowingLongPredicate1 sub(final ThrowingLongPredicate1 other) {
        return (ThrowingLongPredicate1) AbstractThrowingLongPredicate1.super.sub(other);
    }

    @Override
    default ThrowingLongPredicate1 nand(final ThrowingLongPredicate1 other) {
        return (ThrowingLongPredicate1) AbstractThrowingLongPredicate1.super.nand(other);
    }

    @Override
    default ThrowingLongPredicate1 nor(final ThrowingLongPredicate1 other) {
        return (ThrowingLongPredicate1) AbstractThrowingLongPredicate1.super.nor(other);
    }

    @Override
    default ThrowingLongPredicate1 xnor(final ThrowingLongPredicate1 other) {
        return (ThrowingLongPredicate1) AbstractThrowingLongPredicate1.super.xnor(other);
    }

    @Override
    default ThrowingLongPredicate1 orNot(final ThrowingLongPredicate1 other) {
        return (ThrowingLongPredicate1) AbstractThrowingLongPredicate1.super.orNot(other);
    }
}
