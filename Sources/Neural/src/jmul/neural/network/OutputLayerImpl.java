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
import jmul.math.numbers.NumberHelper;
import jmul.math.signs.Signs;


/**
 * An implementation of an output layer.
 *
 * @author Kristian Kutin
 */
class OutputLayerImpl implements Layer {

    /**
     * A default name for this network layer.
     */
    private static final String DEFAULT_NAME;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_NAME = "output layer";
    }

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
     */
    public OutputLayerImpl(int base) {

        super();

        this.name = DEFAULT_NAME;
        this.bias = NumberHelper.createNumber(base, Signs.POSITIVE, 0);
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
     *        a new bias for this network layer
     */
    @Override
    public void updateBias(Number newBias) {

        throw new UnsupportedOperationException("Cannot change the bias of the output layer!");
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

        return true;
    }

}
