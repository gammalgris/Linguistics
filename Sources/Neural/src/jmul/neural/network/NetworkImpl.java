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


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jmul.math.functions.Function;
import jmul.math.functions.FunctionHelper;

import jmul.neural.cells.CellHelper;
import jmul.neural.cells.Neuron;
import jmul.neural.cells.Synapse;


/**
 * An implementation of a neural network.
 *
 * @author Kristian Kutin
 */
class NetworkImpl implements Network {

    /**
     * An index.
     */
    private static final int DEFAULT_INPUT_LAYER_INDEX;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_INPUT_LAYER_INDEX = 0;
    }

    /**
     * A number base.
     */
    private final int base;

    /**
     * The network layout.
     */
    private final Layout layout;

    /**
     * All neuron layers.
     */
    private final List<Layer> layers;

    /**
     * All neurons by layer.
     */
    private final List<List<Neuron>> neurons;

    /**
     * All synapses by layer.
     */
    private final List<List<Synapse>> synapses;

    /**
     * The index for the output layer.
     */
    private final int outputLayerIndex;

    /**
     * Creates a new neural netwqork accordign to the specified parameters.
     *
     * @param base
     *        a number base
     * @param layout
     *        a network layout
     */
    public NetworkImpl(int base, Layout layout) {

        super();

        if (layout == null) {

            throw new IllegalArgumentException("No network layout (null) was specified!");
        }

        this.base = base;
        this.layout = layout;
        this.layers = initializeLayers(base, layout);
        this.neurons = initializeNeurons(base, layout, layers);
        this.synapses = initializeSynapses(base, neurons);

        this.outputLayerIndex = layout.layers() - 1;
    }

    /**
     * Initializes layer informations according to the specified layout.
     *
     * @param base
     *        a number base
     * @param layout
     *        a network layout
     *
     * @return layer informations
     */
    private static List<Layer> initializeLayers(int base, Layout layout) {

        List<Layer> layers = new ArrayList<>();

        int inputLayerIndex = 0;
        int outputLayerIndex = layout.layers() - 1;
        for (int index = 0; index < layout.layers(); index++) {

            Layer layer;
            if (index == inputLayerIndex) {

                layer = NetworkHelper.createInputLayer(base);

            } else if (index == outputLayerIndex) {

                layer = NetworkHelper.createOutputLayer(base);

            } else {

                String name = String.format("layer %d", index);
                layer = NetworkHelper.createLayer(base, name);
            }

            layers.add(layer);
        }

        return Collections.unmodifiableList(layers);
    }

    /**
     * Initializes all neuron layers according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param layout
     *        a network layout
     * @param layers
     *        informations about all network layers
     *
     * @return all neuron layers
     */
    private static List<List<Neuron>> initializeNeurons(int base, Layout layout, List<Layer> layers) {

        List<List<Neuron>> allLayers = new ArrayList<>();

        for (int index = 0; index < layout.layers(); index++) {

            int neurons = layout.neuronsInLayer(index);
            Layer layer = layers.get(index);

            List<Neuron> singleLayer = new ArrayList<>();

            for (int index2 = 0; index2 < neurons; index2++) {

                Function activationFunction;
                if (layer.isInputLayer() || layer.isOutputLayer()) {

                    activationFunction = createLinearFunction(base);

                } else {

                    activationFunction = createSigmoidFunction(base);
                }

                Neuron neuron = CellHelper.createNeuron(layer, activationFunction);

                singleLayer.add(neuron);
            }

            allLayers.add(Collections.unmodifiableList(singleLayer));
        }

        return Collections.unmodifiableList(allLayers);
    }

    /**
     * Creates a linear function.
     *
     * @param base
     *        a number base
     *
     * @return a function
     */
    private static Function createLinearFunction(int base) {

        return FunctionHelper.createMonomialFunction(base, "1", "1");
    }

    /**
     * Creates a sigmoid function.
     *
     * @param base
     *        a number base
     *
     * @return a function
     */
    private static Function createSigmoidFunction(int base) {

        return FunctionHelper.createSigmoidLinearAppoximationFunction(base);
    }

    /**
     * Initializes all synapse layers according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param neurons
     *        all neuron layers
     *
     * @return all synapse layers
     */
    private static List<List<Synapse>> initializeSynapses(int base, List<List<Neuron>> neurons) {

        List<List<Synapse>> allSynapseLayers = new ArrayList<>();

        for (int index = 0; index < neurons.size() - 1; index++) {

            List<Neuron> neuronLayerA = neurons.get(index);

            int index2 = index + 1;
            List<Neuron> neuronLayerB = neurons.get(index2);

            List<Synapse> singleSynapseLayer = new ArrayList<>();

            for (Neuron neuronA : neuronLayerA) {
                for (Neuron neuronB : neuronLayerB) {

                    Synapse synapse = CellHelper.createSynapse(base);

                    neuronA.addTransmitter(synapse);
                    synapse.connectNeuron(neuronB);
                    neuronB.addSignalSource(synapse);

                    singleSynapseLayer.add(synapse);
                }
            }

            allSynapseLayers.add(Collections.unmodifiableList(singleSynapseLayer));
        }

        return Collections.unmodifiableList(allSynapseLayers);
    }

    /**
     * The total number of neuron layers.
     *
     * @return the toal number of neuron layers
     */
    @Override
    public int neuronLayers() {

        return neurons.size();
    }

    /**
     * The total number of synapse layers.
     *
     * @return the toal number of synapse layers
     */
    @Override
    public int synapseLayers() {

        return synapses.size();
    }

    /**
     * Access the neurons of the specified network layer.
     *
     * @param ordinal
     *        an ordinal number representing the neuron layer
     *
     * @return a list of neurons
     */
    @Override
    public List<Neuron> neuronsInLayer(int ordinal) {

        return neurons.get(ordinal);
    }

    /**
     * Access the synapses of the specified network layer.
     *
     * @param ordinal
     *        an ordinal number representing the synapse layer
     *
     * @return a list of synapses
     */
    @Override
    public List<Synapse> synapsesInLayer(int ordinal) {

        return synapses.get(ordinal);
    }

    /**
     * Returns all neurons of the input layer.
     *
     * @return all neurons of the input layer
     */
    @Override
    public List<Neuron> inputLayer() {

        return neurons.get(DEFAULT_INPUT_LAYER_INDEX);
    }

    /**
     * Returns all neurons of the output layer.
     *
     * @return all neurons of the output layer
     */
    @Override
    public List<Neuron> outputLayer() {

        return neurons.get(outputLayerIndex);
    }

    /**
     * Returns informations about the specified layer.
     *
     * @param ordinal
     *        an ordinal number representing the neuron layer
     *
     * @return informations about a layer
     */
    @Override
    public Layer layer(int ordinal) {

        return layers.get(ordinal);
    }

    /**
     * Returns a number base.
     *
     * @return a number base
     */
    @Override
    public int base() {

        return base;
    }

    /**
     * Returns the network layout.
     *
     * @return the network layout
     */
    @Override
    public Layout layout() {

        return layout;
    }

}
