/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.predicate;

import net.ashwork.functionality.predicate.abstracts.AbstractPredicateN;
import net.ashwork.functionality.predicate.partial.LogicalOperator;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts {@code n} arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the predicate specialization for {@link AbstractThrowingToBooleanFunctionN}.
 * All {@code boolean}-producing predicates are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link AbstractPredicateN}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractThrowingToBooleanFunctionN
 * @see AbstractPredicateN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingPredicateN<H extends AbstractThrowingPredicateN.Handler, P extends AbstractThrowingPredicateN<H, P>> extends AbstractThrowingToBooleanFunctionN<H>, LogicalOperator<P> {

    /**
     * Evaluates this predicate on the given arguments or throws a throwable. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the input arguments
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean testAllUnchecked(final Object... args) throws Throwable;

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) throws Throwable {
        return this.testAllUnchecked(args);
    }

    /**
     * @see AbstractPredicateN
     */
    @Override
    AbstractPredicateN<?> handle(final H handler);

    /**
     * @see AbstractPredicateN
     */
    @Override
    AbstractPredicateN<?> swallow();

    @Override
    AbstractThrowingPredicateN<H, P> not();

    @Override
    AbstractThrowingPredicateN<H, P> and(final P other);

    @Override
    AbstractThrowingPredicateN<H, P> or(final P other);

    @Override
    default AbstractThrowingPredicateN<H, P> xor(final P other) {
        return (AbstractThrowingPredicateN<H, P>) LogicalOperator.super.xor(other);
    }

    @Override
    default AbstractThrowingPredicateN<H, P> sub(final P other) {
        return (AbstractThrowingPredicateN<H, P>) LogicalOperator.super.sub(other);
    }

    @Override
    default AbstractThrowingPredicateN<H, P> nand(final P other) {
        return (AbstractThrowingPredicateN<H, P>) LogicalOperator.super.nand(other);
    }

    @Override
    default AbstractThrowingPredicateN<H, P> nor(final P other) {
        return (AbstractThrowingPredicateN<H, P>) LogicalOperator.super.nor(other);
    }

    @Override
    default AbstractThrowingPredicateN<H, P> xnor(final P other) {
        return (AbstractThrowingPredicateN<H, P>) LogicalOperator.super.xnor(other);
    }

    @Override
    default AbstractThrowingPredicateN<H, P> orNot(final P other) {
        return (AbstractThrowingPredicateN<H, P>) LogicalOperator.super.orNot(other);
    }

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToBooleanFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * Makes no assumptions of the arguments passed in and whether
         * a value will compute successfully.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param args the predicate arguments
         * @return the handled result
         */
        boolean testThrownUnchecked(final Throwable t, final Object... args);

        @Override
        default boolean onThrownAsBooleanUnchecked(final Throwable t, final Object... args) {
            return this.testThrownUnchecked(t, args);
        }
    }
}
