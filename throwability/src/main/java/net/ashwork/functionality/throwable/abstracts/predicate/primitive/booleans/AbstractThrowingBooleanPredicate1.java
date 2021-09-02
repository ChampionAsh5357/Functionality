package net.ashwork.functionality.throwable.abstracts.predicate.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.predicate.abstracts.primitive.booleans.AbstractBooleanPredicate1;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.booleans.AbstractThrowingBooleanOperator1;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicateN;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingBooleanFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts a {@code boolean}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link AbstractThrowingPredicateN}.
 * This is the predicate specialization for {@link AbstractThrowingBooleanOperator1}.
 * This is the {@code boolean}-consuming primitive specialization of {@link AbstractThrowingPredicate1}.
 * This is the throwing variation of {@link AbstractBooleanPredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractThrowingPredicateN
 * @see AbstractThrowingBooleanOperator1
 * @see AbstractThrowingPredicate1
 * @see AbstractBooleanPredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingBooleanPredicate1<H extends AbstractThrowingBooleanPredicate1.Handler, P extends AbstractThrowingBooleanPredicate1<H, P>> extends AbstractThrowingPredicateN<H, P>, InputChainableInput<Boolean>, FunctionVariant<Boolean, AbstractThrowingBooleanOperator1<?>>, UnboxedInput<AbstractThrowingPredicate1<Boolean, ?, ?>> {

    /**
     * Evaluates this predicate on the given argument or throws a throwable.
     *
     * @param value the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test(final boolean value) throws Throwable;

    @Override
    default boolean testAllUnchecked(final Object... args) throws Throwable {
        return this.test((boolean) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingBooleanOperator1
     */
    @Override
    AbstractThrowingBooleanOperator1<?> toFunctionVariant();

    /**
     * @see AbstractThrowingPredicate1
     */
    @Override
    AbstractThrowingPredicate1<Boolean, ?, ?> boxInput();

    /**
     * @see AbstractBooleanPredicate1
     */
    @Override
    AbstractBooleanPredicate1<?> handle(final H handler);

    /**
     * @see AbstractBooleanPredicate1
     */
    @Override
    AbstractBooleanPredicate1<?> swallow();

    /**
     * @see AbstractThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingPredicate1<V, ?, ?> compose(final Function1<? super V, ? extends Boolean> before) {
        return (AbstractThrowingPredicate1<V, ?, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingPredicate1
     */
    @Override
    <V> AbstractThrowingPredicate1<V, ?, ?> composeUnchecked(final Function1<? super V, ? extends Boolean> before);

    /**
     * @see AbstractThrowingBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingBooleanFunction1<V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingBooleanFunction1<V, ?>) AbstractThrowingPredicateN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingBooleanFunction1
     */
    @Override
    <V> AbstractThrowingBooleanFunction1<V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    @Override
    AbstractThrowingBooleanPredicate1<H, P> not();

    @Override
    AbstractThrowingBooleanPredicate1<H, P> and(final P other);

    @Override
    AbstractThrowingBooleanPredicate1<H, P> or(final P other);

    @Override
    default AbstractThrowingBooleanPredicate1<H, P> xor(final P other) {
        return (AbstractThrowingBooleanPredicate1<H, P>) AbstractThrowingPredicateN.super.xor(other);
    }

    @Override
    default AbstractThrowingBooleanPredicate1<H, P> sub(final P other) {
        return (AbstractThrowingBooleanPredicate1<H, P>) AbstractThrowingPredicateN.super.sub(other);
    }

    @Override
    default AbstractThrowingBooleanPredicate1<H, P> nand(final P other) {
        return (AbstractThrowingBooleanPredicate1<H, P>) AbstractThrowingPredicateN.super.nand(other);
    }

    @Override
    default AbstractThrowingBooleanPredicate1<H, P> nor(final P other) {
        return (AbstractThrowingBooleanPredicate1<H, P>) AbstractThrowingPredicateN.super.nor(other);
    }

    @Override
    default AbstractThrowingBooleanPredicate1<H, P> xnor(final P other) {
        return (AbstractThrowingBooleanPredicate1<H, P>) AbstractThrowingPredicateN.super.xnor(other);
    }

    @Override
    default AbstractThrowingBooleanPredicate1<H, P> orNot(final P other) {
        return (AbstractThrowingBooleanPredicate1<H, P>) AbstractThrowingPredicateN.super.orNot(other);
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
        boolean testThrown(final Throwable t, final boolean value);

        @Override
        default boolean testThrownUnchecked(final Throwable t, final Object... args) {
            return this.testThrown(t, (boolean) args[0]);
        }
    }
}
