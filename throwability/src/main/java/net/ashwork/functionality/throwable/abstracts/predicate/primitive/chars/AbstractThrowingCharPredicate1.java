/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.predicate.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.predicate.abstracts.primitive.chars.AbstractCharPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicateN;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingCharFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingCharToBooleanFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts a {@code char}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link AbstractThrowingPredicateN}.
 * This is the predicate specialization for {@link AbstractThrowingCharToBooleanFunction1}.
 * This is the {@code char}-consuming primitive specialization of {@link AbstractThrowingPredicate1}.
 * This is the throwing variation of {@link AbstractCharPredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractThrowingPredicateN
 * @see AbstractThrowingCharToBooleanFunction1
 * @see AbstractThrowingPredicate1
 * @see AbstractCharPredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingCharPredicate1<H extends AbstractThrowingCharPredicate1.Handler, P extends AbstractThrowingCharPredicate1<H, P>> extends AbstractThrowingPredicateN<H, P>, InputChainableInput<Character>, FunctionVariant<Boolean, AbstractThrowingCharToBooleanFunction1<?>>, UnboxedInput<AbstractThrowingPredicate1<Character, ?, ?>> {

    /**
     * Evaluates this predicate on the given argument or throws a throwable.
     *
     * @param value the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     * @throws Throwable if the predicate cannot be evaluated
     */
    boolean test(final char value) throws Throwable;

    @Override
    default boolean testAllUnchecked(final Object... args) throws Throwable {
        return this.test((char) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingCharToBooleanFunction1
     */
    @Override
    AbstractThrowingCharToBooleanFunction1<?> toFunctionVariant();

    /**
     * @see AbstractThrowingPredicate1
     */
    @Override
    AbstractThrowingPredicate1<Character, ?, ?> boxInput();

    /**
     * @see AbstractCharPredicate1
     */
    @Override
    AbstractCharPredicate1<?> handle(final H handler);

    /**
     * @see AbstractCharPredicate1
     */
    @Override
    AbstractCharPredicate1<?> swallow();

    /**
     * @see AbstractThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingPredicate1<V, ?, ?> compose(final Function1<? super V, ? extends Character> before) {
        return (AbstractThrowingPredicate1<V, ?, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingPredicate1
     */
    @Override
    <V> AbstractThrowingPredicate1<V, ?, ?> composeUnchecked(final Function1<? super V, ? extends Character> before);

    /**
     * @see AbstractThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingCharFunction1<V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingCharFunction1<V, ?>) AbstractThrowingPredicateN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingCharFunction1
     */
    @Override
    <V> AbstractThrowingCharFunction1<V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    @Override
    AbstractThrowingCharPredicate1<H, P> not();

    @Override
    AbstractThrowingCharPredicate1<H, P> and(final P other);

    @Override
    AbstractThrowingCharPredicate1<H, P> or(final P other);

    @Override
    default AbstractThrowingCharPredicate1<H, P> xor(final P other) {
        return (AbstractThrowingCharPredicate1<H, P>) AbstractThrowingPredicateN.super.xor(other);
    }

    @Override
    default AbstractThrowingCharPredicate1<H, P> sub(final P other) {
        return (AbstractThrowingCharPredicate1<H, P>) AbstractThrowingPredicateN.super.sub(other);
    }

    @Override
    default AbstractThrowingCharPredicate1<H, P> nand(final P other) {
        return (AbstractThrowingCharPredicate1<H, P>) AbstractThrowingPredicateN.super.nand(other);
    }

    @Override
    default AbstractThrowingCharPredicate1<H, P> nor(final P other) {
        return (AbstractThrowingCharPredicate1<H, P>) AbstractThrowingPredicateN.super.nor(other);
    }

    @Override
    default AbstractThrowingCharPredicate1<H, P> xnor(final P other) {
        return (AbstractThrowingCharPredicate1<H, P>) AbstractThrowingPredicateN.super.xnor(other);
    }

    @Override
    default AbstractThrowingCharPredicate1<H, P> orNot(final P other) {
        return (AbstractThrowingCharPredicate1<H, P>) AbstractThrowingPredicateN.super.orNot(other);
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
        boolean testThrown(final Throwable t, final char value);

        @Override
        default boolean testThrownUnchecked(final Throwable t, final Object... args) {
            return this.testThrown(t, (char) args[0]);
        }
    }
}
