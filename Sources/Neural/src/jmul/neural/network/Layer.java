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


import jmul.math.numbers.Number;


/**
 * This interface describes a network layer.
 *
 * @author Kristian Kutin
 */
public interface Layer {

    /**
     * A name for this network layer.
     *
     * @return a name
     */
    String name();

    /**
     * A bias for this network layer.
     *
     * @return a bias
     */
    Number bias();

    /**
     * Updates the bias for this network layer.
     *
     * @param newBias
     *        a new bias forthis network layer
     */
    void updateBias(Number newBias);

    /**
     * Checks if this layer is an input layer.
     *
     * @return <code>true</code> if this layer is an input layer, else <code>false</code>
     */
    boolean isInputLayer();

    /**
     * Checks if this layer is an output layer.
     *
     * @return <code>true</code> if this layer is an output layer, else <code>false</code>
     */
    boolean isOutputLayer();

}
