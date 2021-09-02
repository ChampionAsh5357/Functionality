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
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.predicate.abstracts.AbstractPredicate1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts one argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link AbstractThrowingPredicateN}.
 * This is the predicate specialization for {@link AbstractThrowingToBooleanFunction1}.
 * This is the throwing variation of {@link AbstractPredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the input to the predicate
 * @param <H> the type of the handler to safely call the predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractThrowingPredicateN
 * @see AbstractThrowingToBooleanFunction1
 * @see AbstractPredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingPredicate1<T1, H extends AbstractThrowingPredicate1.Handler<T1>, P extends AbstractThrowingPredicate1<T1, H, P>> extends AbstractThrowingPredicateN<H, P>, InputChainableInput<T1>, FunctionVariant<Boolean, AbstractThrowingToBooleanFunction1<T1, ?>> {

    /**
     * Evaluates this predicate on the given argument or throws a throwable.
     *
     * @param t1 the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test(final T1 t1) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default boolean testAllUnchecked(final Object... args) throws Throwable {
        return this.test((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingToBooleanFunction1
     */
    @Override
    AbstractThrowingToBooleanFunction1<T1, ?> toFunctionVariant();

    /**
     * @see AbstractPredicate1
     */
    @Override
    AbstractPredicate1<T1, ?> handle(final H handler);

    /**
     * @see AbstractPredicate1
     */
    @Override
    AbstractPredicate1<T1, ?> swallow();

    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingPredicate1<V, ?, ?> compose(final Function1<? super V, ? extends T1> before) {
        return (AbstractThrowingPredicate1<V, ?, ?>) InputChainableInput.super.compose(before);
    }

    @Override
    <V> AbstractThrowingPredicate1<V, ?, ?> composeUnchecked(final Function1<? super V, ? extends T1> before);

    /**
     * @see AbstractThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction1<T1, V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingFunction1<T1, V, ?>) AbstractThrowingPredicateN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    <V> AbstractThrowingFunction1<T1, V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    @Override
    AbstractThrowingPredicate1<T1, H, P> not();

    @Override
    AbstractThrowingPredicate1<T1, H, P> and(final P other);

    @Override
    AbstractThrowingPredicate1<T1, H, P> or(final P other);

    @Override
    default AbstractThrowingPredicate1<T1, H, P> xor(final P other) {
        return (AbstractThrowingPredicate1<T1, H, P>) AbstractThrowingPredicateN.super.xor(other);
    }

    @Override
    default AbstractThrowingPredicate1<T1, H, P> sub(final P other) {
        return (AbstractThrowingPredicate1<T1, H, P>) AbstractThrowingPredicateN.super.sub(other);
    }

    @Override
    default AbstractThrowingPredicate1<T1, H, P> nand(final P other) {
        return (AbstractThrowingPredicate1<T1, H, P>) AbstractThrowingPredicateN.super.nand(other);
    }

    @Override
    default AbstractThrowingPredicate1<T1, H, P> nor(final P other) {
        return (AbstractThrowingPredicate1<T1, H, P>) AbstractThrowingPredicateN.super.nor(other);
    }

    @Override
    default AbstractThrowingPredicate1<T1, H, P> xnor(final P other) {
        return (AbstractThrowingPredicate1<T1, H, P>) AbstractThrowingPredicateN.super.xnor(other);
    }

    @Override
    default AbstractThrowingPredicate1<T1, H, P> orNot(final P other) {
        return (AbstractThrowingPredicate1<T1, H, P>) AbstractThrowingPredicateN.super.orNot(other);
    }

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <T1> the type of the input to the predicate
     */
    @FunctionalInterface
    interface Handler<T1> extends AbstractThrowingPredicateN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the input argument
         * @return the handled result
         */
        boolean testThrown(final Throwable t, final T1 t1);

        @SuppressWarnings("unchecked")
        @Override
        default boolean testThrownUnchecked(final Throwable t, final Object... args) {
            return this.testThrown(t, (T1) args[0]);
        }
    }
}
