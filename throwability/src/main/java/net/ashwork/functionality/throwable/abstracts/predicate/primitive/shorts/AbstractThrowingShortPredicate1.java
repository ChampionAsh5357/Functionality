/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.predicate.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.predicate.abstracts.primitive.shorts.AbstractShortPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicateN;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingShortFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingShortToBooleanFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts a {@code short}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link AbstractThrowingPredicateN}.
 * This is the predicate specialization for {@link AbstractThrowingShortToBooleanFunction1}.
 * This is the {@code short}-consuming primitive specialization of {@link AbstractThrowingPredicate1}.
 * This is the throwing variation of {@link AbstractShortPredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractThrowingPredicateN
 * @see AbstractThrowingShortToBooleanFunction1
 * @see AbstractThrowingPredicate1
 * @see AbstractShortPredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingShortPredicate1<H extends AbstractThrowingShortPredicate1.Handler, P extends AbstractThrowingShortPredicate1<H, P>> extends AbstractThrowingPredicateN<H, P>, InputChainableInput<Short>, FunctionVariant<Boolean, AbstractThrowingShortToBooleanFunction1<?>>, UnboxedInput<AbstractThrowingPredicate1<Short, ?, ?>> {

    /**
     * Evaluates this predicate on the given argument or throws a throwable.
     *
     * @param value the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     * @throws Throwable if the predicate cannot be evaluated
     */
    boolean test(final short value) throws Throwable;

    @Override
    default boolean testAllUnchecked(final Object... args) throws Throwable {
        return this.test((short) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingShortToBooleanFunction1
     */
    @Override
    AbstractThrowingShortToBooleanFunction1<?> toFunctionVariant();

    /**
     * @see AbstractThrowingPredicate1
     */
    @Override
    AbstractThrowingPredicate1<Short, ?, ?> boxInput();

    /**
     * @see AbstractShortPredicate1
     */
    @Override
    AbstractShortPredicate1<?> handle(final H handler);

    /**
     * @see AbstractShortPredicate1
     */
    @Override
    AbstractShortPredicate1<?> swallow();

    /**
     * @see AbstractThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingPredicate1<V, ?, ?> compose(final Function1<? super V, ? extends Short> before) {
        return (AbstractThrowingPredicate1<V, ?, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingPredicate1
     */
    @Override
    <V> AbstractThrowingPredicate1<V, ?, ?> composeUnchecked(final Function1<? super V, ? extends Short> before);

    /**
     * @see AbstractThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingShortFunction1<V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingShortFunction1<V, ?>) AbstractThrowingPredicateN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingShortFunction1
     */
    @Override
    <V> AbstractThrowingShortFunction1<V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    @Override
    AbstractThrowingShortPredicate1<H, P> not();

    @Override
    AbstractThrowingShortPredicate1<H, P> and(final P other);

    @Override
    AbstractThrowingShortPredicate1<H, P> or(final P other);

    @Override
    default AbstractThrowingShortPredicate1<H, P> xor(final P other) {
        return (AbstractThrowingShortPredicate1<H, P>) AbstractThrowingPredicateN.super.xor(other);
    }

    @Override
    default AbstractThrowingShortPredicate1<H, P> sub(final P other) {
        return (AbstractThrowingShortPredicate1<H, P>) AbstractThrowingPredicateN.super.sub(other);
    }

    @Override
    default AbstractThrowingShortPredicate1<H, P> nand(final P other) {
        return (AbstractThrowingShortPredicate1<H, P>) AbstractThrowingPredicateN.super.nand(other);
    }

    @Override
    default AbstractThrowingShortPredicate1<H, P> nor(final P other) {
        return (AbstractThrowingShortPredicate1<H, P>) AbstractThrowingPredicateN.super.nor(other);
    }

    @Override
    default AbstractThrowingShortPredicate1<H, P> xnor(final P other) {
        return (AbstractThrowingShortPredicate1<H, P>) AbstractThrowingPredicateN.super.xnor(other);
    }

    @Override
    default AbstractThrowingShortPredicate1<H, P> orNot(final P other) {
        return (AbstractThrowingShortPredicate1<H, P>) AbstractThrowingPredicateN.super.orNot(other);
    }

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingPredicateN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the input argument
         * @return the handled result
         */
        boolean testThrown(final Throwable t, final short value);

        @Override
        default boolean testThrownUnchecked(final Throwable t, final Object... args) {
            return this.testThrown(t, (short) args[0]);
        }
    }
}
