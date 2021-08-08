/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.partial;

/**
 * Represents an object which can properly handle a thrown throwable within itself.
 * The throwing object call is wrapped within another object which handles a thrown
 * throwable by the call.
 *
 * @param <H> the type of the handling object
 * @param <R> the type of the result of the executed object
 *
 * @since 1.0.0
 */
public interface ThrowableHandler<H, R> {

    /**
     * Returns an object that handles the throwable thrown if this
     * throwing object was unable to finish.
     *
     * @param handler the object to handle if a throwable was thrown
     * @return an object which handles any throwable thrown by this throwing object
     */
    R handle(final H handler);

    /**
     * Returns an object that swallows the throwable thrown if this throwing
     * object was unable to finish. The value returned will be the default
     * value as defined by section 4.12.5 of <cite>The Java Language
     * Specification</cite>.
     *
     * @return an object which swallows any throwable thrown by this throwing object
     *
     * @jls 4.12.5 Initial Values of Variables
     */
    R swallow();
}
