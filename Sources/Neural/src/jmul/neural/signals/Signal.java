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

package jmul.neural.signals;


import jmul.math.numbers.Number;

import jmul.neural.cells.Cell;


/**
 * This class represents a signal.
 *
 * @author Kristian Kutin
 */
public final class Signal {

    /**
     * A reference to the actual sender.
     */
    public final Cell sender;

    /**
     * The actual signal value.
     */
    public final Number value;

    /**
     * Creates a new signal according to the specified parameters.
     *
     * @param sender
     *        a reference to the actual sender.
     * @param value
     *        the actual signal value
     */
    public Signal(Cell sender, Number value) {

        super();

        this.sender = sender;
        this.value = value;
    }

    /**
     * Returns a string representation of this signal.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("%s (value = %s)", sender, value);
    }

}
