/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.predicate.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.predicate.abstracts.primitive.bytes.AbstractBytePredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicateN;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingByteFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingByteToBooleanFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts a {@code byte}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link AbstractThrowingPredicateN}.
 * This is the predicate specialization for {@link AbstractThrowingByteToBooleanFunction1}.
 * This is the {@code byte}-consuming primitive specialization of {@link AbstractThrowingPredicate1}.
 * This is the throwing variation of {@link AbstractBytePredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractThrowingPredicateN
 * @see AbstractThrowingByteToBooleanFunction1
 * @see AbstractThrowingPredicate1
 * @see AbstractBytePredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingBytePredicate1<H extends AbstractThrowingBytePredicate1.Handler, P extends AbstractThrowingBytePredicate1<H, P>> extends AbstractThrowingPredicateN<H, P>, InputChainableInput<Byte>, FunctionVariant<Boolean, AbstractThrowingByteToBooleanFunction1<?>>, UnboxedInput<AbstractThrowingPredicate1<Byte, ?, ?>> {

    /**
     * Evaluates this predicate on the given argument or throws a throwable.
     *
     * @param value the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     * @throws Throwable if the predicate cannot be evaluated
     */
    boolean test(final byte value) throws Throwable;

    @Override
    default boolean testAllUnchecked(final Object... args) throws Throwable {
        return this.test((byte) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingByteToBooleanFunction1
     */
    @Override
    AbstractThrowingByteToBooleanFunction1<?> toFunctionVariant();

    /**
     * @see AbstractThrowingPredicate1
     */
    @Override
    AbstractThrowingPredicate1<Byte, ?, ?> boxInput();

    /**
     * @see AbstractBytePredicate1
     */
    @Override
    AbstractBytePredicate1<?> handle(final H handler);

    /**
     * @see AbstractBytePredicate1
     */
    @Override
    AbstractBytePredicate1<?> swallow();

    /**
     * @see AbstractThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingPredicate1<V, ?, ?> compose(final Function1<? super V, ? extends Byte> before) {
        return (AbstractThrowingPredicate1<V, ?, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingPredicate1
     */
    @Override
    <V> AbstractThrowingPredicate1<V, ?, ?> composeUnchecked(final Function1<? super V, ? extends Byte> before);

    /**
     * @see AbstractThrowingByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingByteFunction1<V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingByteFunction1<V, ?>) AbstractThrowingPredicateN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingByteFunction1
     */
    @Override
    <V> AbstractThrowingByteFunction1<V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    @Override
    AbstractThrowingBytePredicate1<H, P> not();

    @Override
    AbstractThrowingBytePredicate1<H, P> and(final P other);

    @Override
    AbstractThrowingBytePredicate1<H, P> or(final P other);

    @Override
    default AbstractThrowingBytePredicate1<H, P> xor(final P other) {
        return (AbstractThrowingBytePredicate1<H, P>) AbstractThrowingPredicateN.super.xor(other);
    }

    @Override
    default AbstractThrowingBytePredicate1<H, P> sub(final P other) {
        return (AbstractThrowingBytePredicate1<H, P>) AbstractThrowingPredicateN.super.sub(other);
    }

    @Override
    default AbstractThrowingBytePredicate1<H, P> nand(final P other) {
        return (AbstractThrowingBytePredicate1<H, P>) AbstractThrowingPredicateN.super.nand(other);
    }

    @Override
    default AbstractThrowingBytePredicate1<H, P> nor(final P other) {
        return (AbstractThrowingBytePredicate1<H, P>) AbstractThrowingPredicateN.super.nor(other);
    }

    @Override
    default AbstractThrowingBytePredicate1<H, P> xnor(final P other) {
        return (AbstractThrowingBytePredicate1<H, P>) AbstractThrowingPredicateN.super.xnor(other);
    }

    @Override
    default AbstractThrowingBytePredicate1<H, P> orNot(final P other) {
        return (AbstractThrowingBytePredicate1<H, P>) AbstractThrowingPredicateN.super.orNot(other);
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
        boolean testThrown(final Throwable t, final byte value);

        @Override
        default boolean testThrownUnchecked(final Throwable t, final Object... args) {
            return this.testThrown(t, (byte) args[0]);
        }
    }
}
