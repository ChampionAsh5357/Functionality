package net.ashwork.functionality.throwable.operator;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.Operator1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator1;

/**
 * Represents an operation that accepts an operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingOperatorN}.
 * This is a specialization of {@link ThrowingFunction1} where the operand and result are of the same type.
 * This is the throwing variation of {@link Operator1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object)}.
 *
 * @param <T> the type of the operand and result of the operator
 *
 * @see ThrowingOperatorN
 * @see ThrowingFunction1
 * @see Operator1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingOperator1<T> extends AbstractThrowingOperator1<T, AbstractThrowingFunction1.Handler<T, T>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @param <T> the type of the operand and result of the operator
     * @return a throwing instance of the original type
     *
     * @see Operator1
     */
    static <T> ThrowingOperator1<T> from(final Operator1<T> operator) {
        return operator::apply;
    }

    @Override
    default Operator1<T> swallow() {
        return this.handle((t, t1) -> null);
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<V, T> compose(final Function1<? super V, ? extends T> before) {
        return (ThrowingFunction1<V, T>) AbstractThrowingOperator1.super.compose(before);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<V, T> composeUnchecked(final Function1<? super V, ? extends T> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<T, V> andThen(final Function1<? super T, ? extends V> after) {
        return (ThrowingFunction1<T, V>) AbstractThrowingOperator1.super.andThen(after);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<T, V> andThenUnchecked(final Function1<? super T, ? extends V> after) {
        return (final T t1) -> after.apply(this.apply(t1));
    }
}
