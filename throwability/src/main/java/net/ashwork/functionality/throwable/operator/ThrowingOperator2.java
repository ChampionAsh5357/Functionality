package net.ashwork.functionality.throwable.operator;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.Operator2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator2;

/**
 * Represents an operation that accepts two operands and produces a result of the same type as its operands.
 * This is the two-arity specialization of {@link ThrowingOperatorN}.
 * This is a specialization of {@link ThrowingFunction2} where the operands and result are of the same type.
 * This is the throwing variation of {@link Operator2}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T> the type of the operands and result of the operator
 *
 * @see ThrowingOperatorN
 * @see ThrowingFunction2
 * @see Operator2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingOperator2<T> extends AbstractThrowingOperator2<T, AbstractThrowingFunction2.Handler<T, T, T>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @param <T> the type of the operands and result of the operator
     * @return a throwing instance of the original type
     *
     * @see Operator2
     */
    static <T> ThrowingOperator2<T> from(final Operator2<T> operator) {
        return operator::apply;
    }

    /**
     * @see Operator2
     */
    @Override
    default Operator2<T> swallow() {
        return this.handle((t, t1, t2) -> null);
    }

    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<T, T, V> andThen(final Function1<? super T, ? extends V> after) {
        return (ThrowingFunction2<T, T, V>) AbstractThrowingOperator2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<T, T, V> andThenUnchecked(final Function1<? super T, ? extends V> after) {
        return (final T t1, final T t2) -> after.apply(this.apply(t1, t2));
    }
}
