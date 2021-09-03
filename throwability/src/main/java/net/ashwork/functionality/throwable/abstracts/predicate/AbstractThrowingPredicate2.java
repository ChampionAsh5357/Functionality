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
import net.ashwork.functionality.predicate.abstracts.AbstractPredicate2;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunction2;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts two arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link AbstractThrowingPredicateN}.
 * This is the predicate specialization for {@link AbstractThrowingToBooleanFunction2}.
 * This is the throwing variation of {@link AbstractPredicate2}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the first argument to the predicate
 * @param <T2> the type of the second argument to the predicate
 * @param <H> the type of the handler to safely call the predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractThrowingPredicateN
 * @see AbstractThrowingToBooleanFunction2
 * @see AbstractPredicate2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingPredicate2<T1, T2, H extends AbstractThrowingPredicate2.Handler<T1, T2>, P extends AbstractThrowingPredicate2<T1, T2, H, P>> extends AbstractThrowingPredicateN<H, P>, FunctionVariant<Boolean, AbstractThrowingToBooleanFunction2<T1, T2, ?>> {

    /**
     * Evaluates this predicate on the given arguments or throws a throwable.
     *
     * @param t1 the first input argument
     * @param t2 the second input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     * @throws Throwable if the predicate cannot be evaluated
     */
    boolean test(final T1 t1, final T2 t2) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default boolean testAllUnchecked(final Object... args) throws Throwable {
        return this.test((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractThrowingToBooleanFunction2
     */
    @Override
    AbstractThrowingToBooleanFunction2<T1, T2, ?> toFunctionVariant();

    /**
     * @see AbstractPredicate2
     */
    @Override
    AbstractPredicate2<T1, T2, ?> handle(final H handler);

    /**
     * @see AbstractPredicate2
     */
    @Override
    AbstractPredicate2<T1, T2, ?> swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<T1, T2, V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingFunction2<T1, T2, V, ?>) AbstractThrowingPredicateN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<T1, T2, V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    @Override
    AbstractThrowingPredicate2<T1, T2, H, P> not();

    @Override
    AbstractThrowingPredicate2<T1, T2, H, P> and(final P other);

    @Override
    AbstractThrowingPredicate2<T1, T2, H, P> or(final P other);

    @Override
    default AbstractThrowingPredicate2<T1, T2, H, P> xor(final P other) {
        return (AbstractThrowingPredicate2<T1, T2, H, P>) AbstractThrowingPredicateN.super.xor(other);
    }

    @Override
    default AbstractThrowingPredicate2<T1, T2, H, P> sub(final P other) {
        return (AbstractThrowingPredicate2<T1, T2, H, P>) AbstractThrowingPredicateN.super.sub(other);
    }

    @Override
    default AbstractThrowingPredicate2<T1, T2, H, P> nand(final P other) {
        return (AbstractThrowingPredicate2<T1, T2, H, P>) AbstractThrowingPredicateN.super.nand(other);
    }

    @Override
    default AbstractThrowingPredicate2<T1, T2, H, P> nor(final P other) {
        return (AbstractThrowingPredicate2<T1, T2, H, P>) AbstractThrowingPredicateN.super.nor(other);
    }

    @Override
    default AbstractThrowingPredicate2<T1, T2, H, P> xnor(final P other) {
        return (AbstractThrowingPredicate2<T1, T2, H, P>) AbstractThrowingPredicateN.super.xnor(other);
    }

    @Override
    default AbstractThrowingPredicate2<T1, T2, H, P> orNot(final P other) {
        return (AbstractThrowingPredicate2<T1, T2, H, P>) AbstractThrowingPredicateN.super.orNot(other);
    }

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <T1> the type of the first argument to the predicate
     * @param <T2> the type of the second argument to the predicate
     */
    @FunctionalInterface
    interface Handler<T1, T2> extends AbstractThrowingPredicateN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the first input argument
         * @param t2 the second input argument
         * @return the handled result
         */
        boolean testThrown(final Throwable t, final T1 t1, final T2 t2);

        @SuppressWarnings("unchecked")
        @Override
        default boolean testThrownUnchecked(final Throwable t, final Object... args) {
            return this.testThrown(t, (T1) args[0], (T2) args[1]);
        }
    }
}
