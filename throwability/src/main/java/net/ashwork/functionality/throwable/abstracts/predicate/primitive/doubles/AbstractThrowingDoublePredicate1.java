package net.ashwork.functionality.throwable.abstracts.predicate.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.FunctionVariant;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.predicate.abstracts.primitive.doubles.AbstractDoublePredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicateN;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingDoubleFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingDoubleToBooleanFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a predicate that accepts a {@code double}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link AbstractThrowingPredicateN}.
 * This is the predicate specialization for {@link AbstractThrowingDoubleToBooleanFunction1}.
 * This is the {@code double}-consuming primitive specialization of {@link AbstractThrowingPredicate1}.
 * This is the throwing variation of {@link AbstractDoublePredicate1}.
 *
 * @apiNote
 * This is an abstract predicate and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the predicate
 * @param <P> the type of this predicate
 *
 * @see AbstractThrowingPredicateN
 * @see AbstractThrowingDoubleToBooleanFunction1
 * @see AbstractThrowingPredicate1
 * @see AbstractDoublePredicate1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingDoublePredicate1<H extends AbstractThrowingDoublePredicate1.Handler, P extends AbstractThrowingDoublePredicate1<H, P>> extends AbstractThrowingPredicateN<H, P>, InputChainableInput<Double>, FunctionVariant<Boolean, AbstractThrowingDoubleToBooleanFunction1<?>>, UnboxedInput<AbstractThrowingPredicate1<Double, ?, ?>> {

    /**
     * Evaluates this predicate on the given argument or throws a throwable.
     *
     * @param value the input argument
     * @return {@code true} if the input arguments match the predicate, otherwise
     *         {@code false}
     */
    boolean test(final double value) throws Throwable;

    @Override
    default boolean testAllUnchecked(final Object... args) throws Throwable {
        return this.test((double) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingDoubleToBooleanFunction1
     */
    @Override
    AbstractThrowingDoubleToBooleanFunction1<?> toFunctionVariant();

    /**
     * @see AbstractThrowingPredicate1
     */
    @Override
    AbstractThrowingPredicate1<Double, ?, ?> boxInput();

    /**
     * @see AbstractDoublePredicate1
     */
    @Override
    AbstractDoublePredicate1<?> handle(final H handler);

    /**
     * @see AbstractDoublePredicate1
     */
    @Override
    AbstractDoublePredicate1<?> swallow();

    /**
     * @see AbstractThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingPredicate1<V, ?, ?> compose(final Function1<? super V, ? extends Double> before) {
        return (AbstractThrowingPredicate1<V, ?, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingPredicate1
     */
    @Override
    <V> AbstractThrowingPredicate1<V, ?, ?> composeUnchecked(final Function1<? super V, ? extends Double> before);

    /**
     * @see AbstractThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingDoubleFunction1<V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingDoubleFunction1<V, ?>) AbstractThrowingPredicateN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingDoubleFunction1
     */
    @Override
    <V> AbstractThrowingDoubleFunction1<V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    @Override
    AbstractThrowingDoublePredicate1<H, P> not();

    @Override
    AbstractThrowingDoublePredicate1<H, P> and(final P other);

    @Override
    AbstractThrowingDoublePredicate1<H, P> or(final P other);

    @Override
    default AbstractThrowingDoublePredicate1<H, P> xor(final P other) {
        return (AbstractThrowingDoublePredicate1<H, P>) AbstractThrowingPredicateN.super.xor(other);
    }

    @Override
    default AbstractThrowingDoublePredicate1<H, P> sub(final P other) {
        return (AbstractThrowingDoublePredicate1<H, P>) AbstractThrowingPredicateN.super.sub(other);
    }

    @Override
    default AbstractThrowingDoublePredicate1<H, P> nand(final P other) {
        return (AbstractThrowingDoublePredicate1<H, P>) AbstractThrowingPredicateN.super.nand(other);
    }

    @Override
    default AbstractThrowingDoublePredicate1<H, P> nor(final P other) {
        return (AbstractThrowingDoublePredicate1<H, P>) AbstractThrowingPredicateN.super.nor(other);
    }

    @Override
    default AbstractThrowingDoublePredicate1<H, P> xnor(final P other) {
        return (AbstractThrowingDoublePredicate1<H, P>) AbstractThrowingPredicateN.super.xnor(other);
    }

    @Override
    default AbstractThrowingDoublePredicate1<H, P> orNot(final P other) {
        return (AbstractThrowingDoublePredicate1<H, P>) AbstractThrowingPredicateN.super.orNot(other);
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
        boolean testThrown(final Throwable t, final double value);

        @Override
        default boolean testThrownUnchecked(final Throwable t, final Object... args) {
            return this.testThrown(t, (double) args[0]);
        }
    }
}
