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

package jmul.neural.cells;


import jmul.math.functions.Function;

import jmul.neural.network.Layer;


/**
 * A hlper class for neural cells.
 *
 * @author Kristian Kutin
 */
public final class CellHelper {

    /**
     * The default constructor.
     */
    private CellHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new unconnected synapse with a random weight.
     *
     * @param base
     *        a number base
     *
     * @return a new synapse
     */
    public static Synapse createSynapse(int base) {

        return new SynapseImpl(base);
    }

    /**
     * Creates a new neuron according to the specified parameters.
     *
     * @param layer
     *        a network layer
     * @param activationFunction
     *        an activation function
     *
     * @return a new neuron
     */
    public static Neuron createNeuron(Layer layer, Function activationFunction) {

        if (layer == null) {

            throw new IllegalArgumentException("No layer (null) was specified!");
        }

        if (layer.isInputLayer()) {

            return new InputNeuronImpl(layer, activationFunction);

        } else if (layer.isOutputLayer()) {

            return new OutputNeuronImpl(layer, activationFunction);

        } else {

            return new NeuronImpl(layer, activationFunction);
        }
    }

}
