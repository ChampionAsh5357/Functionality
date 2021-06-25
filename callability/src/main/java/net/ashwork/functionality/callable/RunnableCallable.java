/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.callable;

/**
 * Represents an action or task that takes no arguments, returns no result, and
 * may throw an exception.
 *
 * <p>This is a functional interface whose functional method is {@link #run()}.
 *
 * @since 1.1.0
 */
@FunctionalInterface
public interface RunnableCallable {

    /**
     * Runs the associated action, or throws an exception if unable
     * to finish.
     *
     * @throws Exception If unable to finish
     */
    void run() throws Exception;

    /**
     * Returns a runnable that handles the exception thrown if this
     * runnable was unable to finish.
     *
     * @param onException The consumer to handle if an exception was
     *                    thrown
     * @return A runnable which handles any exception thrown by this
     *         runnable
     * @see Runnable
     */
    default Runnable handle(final ExceptionHandler onException) {
        return () -> {
            try {
                this.run();
            } catch (final Exception e) {
                onException.handle(e);
            }
        };
    }

    /**
     * Returns a runnable that swallows the exception thrown if this
     * runnable was unable to finish.
     *
     * @return A runnable which swallows any exception thrown by this
     *         runnable
     * @see Runnable
     */
    default Runnable swallow() {
        return this.handle(e -> {});
    }

    /**
     * Represents a handler that takes in the outer callable's parameters and
     * the thrown exception and returns a result safely.
     */
    @FunctionalInterface
    interface ExceptionHandler {

        /**
         * Handles an exception thrown by the outer callable and returns safely.
         * This should never throw an exception.
         *
         * @param e The thrown exception
         */
        void handle(final Exception e);
    }
}
