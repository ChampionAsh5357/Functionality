/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.util;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * A program element annotated with {@link InheritOnly} indicates that this type
 * should not be used for implementation directly, typically because the type was
 * not made to be called as is or has a better alternative. The type should only
 * be used for inheritance.
 *
 * This has no effect on the underlying code and is just used as an identifier
 * within the source.
 *
 * @since 3.0.0
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({CONSTRUCTOR, METHOD, TYPE})
public @interface InheritOnly {
}
