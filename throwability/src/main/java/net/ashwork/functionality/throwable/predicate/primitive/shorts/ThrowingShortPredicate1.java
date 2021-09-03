/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.predicate.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.primitive.shorts.ShortPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.primitive.shorts.AbstractThrowingShortPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicateN;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingShortFunction1;
import net.ashwork.functionality.throwable.primitive.combined.ThrowingShortToBooleanFunction1;

/**
 * Represents a predicate that accepts a {@code short}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link ThrowingPredicateN}.
 * This is the predicate specialization for {@link ThrowingShortToBooleanFunction1}.
 * This is the {@code short}-consuming primitive specialization of {@link ThrowingPredicate1}.
 * This is the throwing variation of {@link ShortPredicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(short)}.
 *
 * @see ThrowingPredicateN
 * @see ThrowingShortToBooleanFunction1
 * @see ThrowingPredicate1
 * @see ShortPredicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingShortPredicate1 extends AbstractThrowingShortPredicate1<AbstractThrowingShortPredicate1.Handler, ThrowingShortPredicate1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ShortPredicate1
     */
    static ThrowingShortPredicate1 from(final ShortPredicate1 predicate) {
        return predicate::test;
    }

    /**
     * Creates an instance of this object from its {@link ThrowingShortToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ThrowingShortToBooleanFunction1
     */
    static ThrowingShortPredicate1 fromFunctionVariant(final ThrowingShortToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingShortToBooleanFunction1
     */
    @Override
    default ThrowingShortToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default ThrowingPredicate1<Short> boxInput() {
        return this::test;
    }

    /**
     * @see ShortPredicate1
     */
    @Override
    default ShortPredicate1 handle(final Handler handler) {
        return (final short value) -> {
            try {
                return this.test(value);
            } catch (final Throwable t) {
                return handler.testThrown(t, value);
            }
        };
    }

    /**
     * @see ShortPredicate1
     */
    @Override
    default ShortPredicate1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingPredicate1<V> compose(final Function1<? super V, ? extends Short> before) {
        return (ThrowingPredicate1<V>) AbstractThrowingShortPredicate1.super.compose(before);
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default <V> ThrowingPredicate1<V> composeUnchecked(final Function1<? super V, ? extends Short> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    /**
     * @see ThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingShortFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingShortFunction1<V>) AbstractThrowingShortPredicate1.super.andThen(after);
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default <V> ThrowingShortFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final short value) -> after.apply(this.test(value));
    }

    @Override
    default ThrowingShortPredicate1 not() {
        return (final short value) -> !this.test(value);
    }

    @Override
    default ThrowingShortPredicate1 and(final ThrowingShortPredicate1 other) {
        return (final short value) -> this.test(value) && other.test(value);
    }

    @Override
    default ThrowingShortPredicate1 or(final ThrowingShortPredicate1 other) {
        return (final short value) -> this.test(value) || other.test(value);
    }

    @Override
    default ThrowingShortPredicate1 xor(final ThrowingShortPredicate1 other) {
        return (ThrowingShortPredicate1) AbstractThrowingShortPredicate1.super.xor(other);
    }

    @Override
    default ThrowingShortPredicate1 sub(final ThrowingShortPredicate1 other) {
        return (ThrowingShortPredicate1) AbstractThrowingShortPredicate1.super.sub(other);
    }

    @Override
    default ThrowingShortPredicate1 nand(final ThrowingShortPredicate1 other) {
        return (ThrowingShortPredicate1) AbstractThrowingShortPredicate1.super.nand(other);
    }

    @Override
    default ThrowingShortPredicate1 nor(final ThrowingShortPredicate1 other) {
        return (ThrowingShortPredicate1) AbstractThrowingShortPredicate1.super.nor(other);
    }

    @Override
    default ThrowingShortPredicate1 xnor(final ThrowingShortPredicate1 other) {
        return (ThrowingShortPredicate1) AbstractThrowingShortPredicate1.super.xnor(other);
    }

    @Override
    default ThrowingShortPredicate1 orNot(final ThrowingShortPredicate1 other) {
        return (ThrowingShortPredicate1) AbstractThrowingShortPredicate1.super.orNot(other);
    }
}
