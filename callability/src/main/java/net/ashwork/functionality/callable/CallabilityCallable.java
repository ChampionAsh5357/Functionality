/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file:
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package net.ashwork.functionality.callable;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * An extension interface to provide library features for {@link Callable}.
 *
 * <p>This is a functional interface whose functional method is {@link #call()}.
 *
 * @param <V> The type of the result of the callable
 *
 * @see Callable
 * @since 2.1.0
 */
@FunctionalInterface
public interface CallabilityCallable<V> extends Callable<V> {

    /**
     * Returns a supplier that handles the exception thrown if this
     * callable was unable to compute a result.
     *
     * @param onException The function to apply if an exception was
     *                    thrown which returns a result
     * @return A supplier which handles any exception thrown by this
     *         callable
     * @see Supplier
     */
    default Supplier<V> handle(final ExceptionHandler<? extends V> onException) {
        return () -> {
            try {
                return this.call();
            } catch (final Exception e) {
                return onException.handle(e);
            }
        };
    }

    /**
     * Returns a supplier that swallows the exception thrown if this
     * callable was unable to compute a result by returning null.
     *
     * @return A supplier which swallows any exception thrown by this
     *         callable by returning null
     * @see Supplier
     */
    default Supplier<V> swallow() { return this.handle(e -> null); }

    /**
     * Wraps a callable instance to provide library features for {@link Callable}.
     *
     * @param callable The callable instance to be wrapped
     * @param <V> The type of the result of the callable
     * @return The wrapped callable as {@link CallabilityCallable}
     */
    static <V> CallabilityCallable<V> wrap(final Callable<V> callable) {
        return callable::call;
    }

    /**
     * Represents a handler that takes in the outer callable's parameters and
     * the thrown exception and returns a result safely.
     *
     * @param <V> The type of the result of the callable
     */
    @FunctionalInterface
    interface ExceptionHandler<V> {

        /**
         * Handles an exception thrown by the outer callable and returns safely.
         * This should never throw an exception.
         *
         * @param e The thrown exception
         * @return The handled callable result
         */
        V handle(final Exception e);
    }
}
