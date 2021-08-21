/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.abstracts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction1;
import net.ashwork.functionality.util.InheritOnly;

import java.util.function.Predicate;

/**
 * Represents a predicate that accepts one argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization for {@link AbstractPredicateN}.
 * This is the predicate specialization for {@link ToBooleanFunction1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <P> the type of this predicate
 * @param <T1> the type of the input to the predicate
 * 
 * @see AbstractPredicateN
 * @see ToBooleanFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractPredicate1<T1, P extends AbstractPredicate1<T1, P>> extends AbstractPredicateN<P>, InputChainableInput<T1>, Variant<Predicate<T1>>, FunctionVariant<Boolean, ToBooleanFunction1<T1>> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t1 the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test(final T1 t1);

    @SuppressWarnings("unchecked")
    @Override
    default boolean testAllUnchecked(final Object... args) {
        return this.test((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }
    
    /**
     * @see ToBooleanFunction1
     */
    @Override
    default ToBooleanFunction1<T1> toFunctionVariant() {
        return this::test;
    }

    /**
     * @see Predicate
     */
    @Override
    default Predicate<T1> toVariant() {
        return this::test;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractPredicate1<V, ?> compose(final Function1<? super V, ? extends T1> before) {
        return (AbstractPredicate1<V, ?>) InputChainableInput.super.compose(before);
    }

    @Override
    <V> AbstractPredicate1<V, ?> composeUnchecked(final Function1<? super V, ? extends T1> before);

    /**
     * @see Function1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<T1, V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (Function1<T1, V>) AbstractPredicateN.super.andThen(after);
    }

    /**
     * @see Function1
     */
    @Override
    default <V> Function1<T1, V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.test(t1));
    }

    @Override
    AbstractPredicate1<T1, P> not();

    @Override
    AbstractPredicate1<T1, P> and(final P other);

    @Override
    AbstractPredicate1<T1, P> or(final P other);

    @Override
    default AbstractPredicate1<T1, P> xor(final P other) {
        return (AbstractPredicate1<T1, P>) AbstractPredicateN.super.xor(other);
    }

    @Override
    default AbstractPredicate1<T1, P> sub(final P other) {
        return (AbstractPredicate1<T1, P>) AbstractPredicateN.super.sub(other);
    }

    @Override
    default AbstractPredicate1<T1, P> nand(final P other) {
        return (AbstractPredicate1<T1, P>) AbstractPredicateN.super.nand(other);
    }

    @Override
    default AbstractPredicate1<T1, P> nor(final P other) {
        return (AbstractPredicate1<T1, P>) AbstractPredicateN.super.nor(other);
    }

    @Override
    default AbstractPredicate1<T1, P> xnor(final P other) {
        return (AbstractPredicate1<T1, P>) AbstractPredicateN.super.xnor(other);
    }

    @Override
    default AbstractPredicate1<T1, P> orNot(final P other) {
        return (AbstractPredicate1<T1, P>) AbstractPredicateN.super.orNot(other);
    }
}
