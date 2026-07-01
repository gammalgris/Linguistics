/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2026  Kristian Kutin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: kristian.kutin@arcor.de
 */

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.neural.network;


/**
 * A custom exception for cloning errors.
 *
 * @author Kristian Kutin
 */
public class CloningException extends RuntimeException {

    /**
     * The default constructor.
     */
    public CloningException() {

        super();
    }

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param message
     *        an exception message
     */
    public CloningException(String message) {

        super(message);
    }

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param cause
     *        a cause for this exception
     */
    public CloningException(Throwable cause) {

        super(cause);
    }

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param message
     *        an exception message
     * @param cause
     *        a cause for this exception
     */
    public CloningException(String message, Throwable cause) {

        super(message, cause);
    }

}
