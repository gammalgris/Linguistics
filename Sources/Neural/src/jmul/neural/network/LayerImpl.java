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


import jmul.math.Math;
import jmul.math.numbers.Number;


/**
 * An implementation of a network layer.
 *
 * @author Kristian Kutin
 */
class LayerImpl implements Layer {

    /**
     * A number base.
     */
    private final int base;

    /**
     * A name for this network layer.
     */
    private final String name;

    /**
     * A bias for this network layer.
     */
    private Number bias;

    /**
     * Creates a new network layer
     *
     * @param base
     *        a number base
     * @param name
     *        a name for this network layer
     */
    public LayerImpl(int base, String name) {

        super();

        if (name == null) {

            throw new IllegalArgumentException("No name (null) was specified!");
        }

        if (name.trim().isEmpty()) {

            throw new IllegalArgumentException("No name (empty string) was specified!");
        }

        this.base = base;
        this.name = name;
        this.bias = Math.random(base);
    }

    /**
     * Returns a string representation for this network layer.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("%s (bias = %s)", name, bias);
    }

    /**
     * A name for this network layer.
     *
     * @return a name
     */
    @Override
    public String name() {

        return name;
    }

    /**
     * A bias for this network layer.
     *
     * @return a bias
     */
    @Override
    public Number bias() {

        return bias;
    }

    /**
     * Updates the bias for this network layer.
     *
     * @param newBias
     *        a new bias forthis network layer
     */
    @Override
    public void updateBias(Number newBias) {

        // Check some potential error causes beforehand. These should not go unnoticed.

        if (newBias == null) {

            throw new IllegalArgumentException("No bias (null) was specified!");
        }

        if (newBias.base() != base) {

            throw new IllegalArgumentException("The new bias is of a different number base!");
        }

        bias = newBias;
    }

    /**
     * Checks if this layer is an input layer.
     *
     * @return <code>true</code> if this layer is an input layer, else <code>false</code>
     */
    @Override
    public boolean isInputLayer() {

        return false;
    }

    /**
     * Checks if this layer is an output layer.
     *
     * @return <code>true</code> if this layer is an output layer, else <code>false</code>
     */
    @Override
    public boolean isOutputLayer() {

        return false;
    }

}
