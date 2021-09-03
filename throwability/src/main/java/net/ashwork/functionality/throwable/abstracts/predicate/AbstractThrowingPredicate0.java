/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.predicate;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicate0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunction0;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts no arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the zero-arity specialization for {@link AbstractThrowingPredicateN}.
 * This is the predicate specialization for {@link AbstractThrowingToBooleanFunction0}.
 * This is the throwing variation of {@link AbstractPredicate0}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractThrowingPredicateN
 * @see AbstractThrowingToBooleanFunction0
 * @see AbstractPredicate0
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingPredicate0<H extends AbstractThrowingPredicate0.Handler, P extends AbstractThrowingPredicate0<H, P>> extends AbstractThrowingPredicateN<H, P>, FunctionVariant<Boolean, AbstractThrowingToBooleanFunction0<?>> {

    /**
     * Evaluates this predicate or throws a throwable.
     *
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     * @throws Throwable if the predicate cannot be evaluated
     */
    boolean test() throws Throwable;

    @Override
    default boolean testAllUnchecked(final Object... args) throws Throwable {
        return this.test();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see AbstractThrowingToBooleanFunction0
     */
    @Override
    AbstractThrowingToBooleanFunction0<?> toFunctionVariant();

    /**
     * @see AbstractPredicate0
     */
    @Override
    AbstractPredicate0<?> handle(final H handler);

    /**
     * @see AbstractPredicate0
     */
    @Override
    AbstractPredicate0<?> swallow();

    /**
     * @see AbstractThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction0<V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingFunction0<V, ?>) AbstractThrowingPredicateN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction0
     */
    @Override
    <V> AbstractThrowingFunction0<V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    @Override
    AbstractThrowingPredicate0<H, P> not();

    @Override
    AbstractThrowingPredicate0<H, P> and(final P other);

    @Override
    AbstractThrowingPredicate0<H, P> or(final P other);

    @Override
    default AbstractThrowingPredicate0<H, P> xor(final P other) {
        return (AbstractThrowingPredicate0<H, P>) AbstractThrowingPredicateN.super.xor(other);
    }

    @Override
    default AbstractThrowingPredicate0<H, P> sub(final P other) {
        return (AbstractThrowingPredicate0<H, P>) AbstractThrowingPredicateN.super.sub(other);
    }

    @Override
    default AbstractThrowingPredicate0<H, P> nand(final P other) {
        return (AbstractThrowingPredicate0<H, P>) AbstractThrowingPredicateN.super.nand(other);
    }

    @Override
    default AbstractThrowingPredicate0<H, P> nor(final P other) {
        return (AbstractThrowingPredicate0<H, P>) AbstractThrowingPredicateN.super.nor(other);
    }

    @Override
    default AbstractThrowingPredicate0<H, P> xnor(final P other) {
        return (AbstractThrowingPredicate0<H, P>) AbstractThrowingPredicateN.super.xnor(other);
    }

    @Override
    default AbstractThrowingPredicate0<H, P> orNot(final P other) {
        return (AbstractThrowingPredicate0<H, P>) AbstractThrowingPredicateN.super.orNot(other);
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
         * @return the handled result
         */
        boolean testThrown(final Throwable t);

        @Override
        default boolean testThrownUnchecked(final Throwable t, final Object... args) {
            return this.testThrown(t);
        }
    }
}
