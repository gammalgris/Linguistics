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


import java.util.List;

import jmul.neural.cells.Neuron;
import jmul.neural.cells.Synapse;


/**
 * This interface defines a neural network with it's various layers.
 *
 * @author Kristian Kutin
 */
public interface Network {

    /**
     * The total number of neuron layers.
     *
     * @return the toal number of neuron layers
     */
    int neuronLayers();

    /**
     * The total number of synapse layers.
     *
     * @return the toal number of synapse layers
     */
    int synapseLayers();

    /**
     * Access the neurons of the specified network layer.
     *
     * @param ordinal
     *        an ordinal number representing the neuron layer
     *
     * @return a list of neurons
     */
    List<Neuron> neuronsInLayer(int ordinal);

    /**
     * Access the synapses of the specified network layer.
     *
     * @param ordinal
     *        an ordinal number representing the synapse layer
     *
     * @return a list of synapses
     */
    List<Synapse> synapsesInLayer(int ordinal);

    /**
     * Returns all neurons of the input layer.
     *
     * @return all neurons of the input layer
     */
    List<Neuron> inputLayer();

    /**
     * Returns all neurons of the output layer.
     *
     * @return all neurons of the output layer
     */
    List<Neuron> outputLayer();

    /**
     * Returns informations about the specified layer.
     *
     * @param ordinal
     *        an ordinal number representing the neuron layer
     *
     * @return informations about a layer
     */
    Layer layer(int ordinal);

    /**
     * Returns a number base.
     *
     * @return a number base
     */
    int base();

    /**
     * Returns the network layout.
     *
     * @return the network layout
     */
    public Layout layout();

}
