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
import java.util.List;

import jmul.neural.cells.Synapse;


/**
 * A helper class for networks and network components.
 *
 * @author Kristian Kutin
 */
public final class NetworkHelper {

    /**
     * The default constructor.
     */
    private NetworkHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Create a new network accordign to the specified parameters.
     *
     * @param base
     *        a number base
     * @param layout
     *        a network layout
     *
     * @return a neural network
     */
    public static Network createNetwork(int base, Layout layout) {

        return new NetworkImpl(base, layout);
    }

    /**
     * Creates a new network layer according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param name
     *        the name for the network layer
     *
     * @return a network layer
     */
    public static Layer createLayer(int base, String name) {

        return new LayerImpl(base, name);
    }

    /**
     * Creates a new network layer according to the specified parameters.
     *
     * @param base
     *        a number base
     *
     * @return a network layer
     */
    public static Layer createInputLayer(int base) {

        return new InputLayerImpl(base);
    }

    /**
     * Creates a new network layer according to the specified parameters.
     *
     * @param base
     *        a number base
     *
     * @return a network layer
     */
    public static Layer createOutputLayer(int base) {

        return new OutputLayerImpl(base);
    }

    /**
     * Clones the specified network.
     *
     * @param network
     *        a neural network
     *
     * @return a cloned neural network (i.e. with the identical layout, weights and biases)
     */
    public static Network cloneNetwork(Network network) {

        if (network == null) {

            throw new IllegalArgumentException("No network (null) has been speciied!");
        }

        Network clonedNetwork = createNetwork(network.base(), network.layout());

        for (int indexLayer = 0; indexLayer < network.synapseLayers(); indexLayer++) {

            for (int indexSynapse = 0; indexSynapse < network.synapsesInLayer(indexLayer).size(); indexSynapse++) {

                Synapse synapse = network.synapsesInLayer(indexLayer).get(indexSynapse);
                Synapse clonedSynapse = clonedNetwork.synapsesInLayer(indexLayer).get(indexSynapse);

                clonedSynapse.updateWeight(synapse.weight());
            }
        }

        for (int indexLayer = 0; indexLayer < network.neuronLayers(); indexLayer++) {

            Layer layer = network.layer(indexLayer);
            Layer clonedLayer = clonedNetwork.layer(indexLayer);

            if (layer.isInputLayer() && clonedLayer.isInputLayer()) {

                continue;

            } else if (layer.isOutputLayer() && clonedLayer.isOutputLayer()) {

                continue;

            } else if (layer.isInputLayer() != clonedLayer.isInputLayer()) {

                String message = "Input layers are not correctly marked!";
                throw new CloningException(message);

            } else if (layer.isOutputLayer() != clonedLayer.isOutputLayer()) {

                String message = "Output layers are not correctly marked!";
                throw new CloningException(message);
            }

            clonedLayer.updateBias(layer.bias());
        }

        return clonedNetwork;
    }

    /**
     * Compares the specified networks. Collects all differences and returns a list of differences.
     *
     * @param network1
     *        a neural network
     * @param network2
     *        a neural network
     *
     * @return a list of differences
     */
    public static List<String> compareNetworks(Network network1, Network network2) {

        if (network1 == null) {

            throw new IllegalArgumentException("No neural network (null) was specified!");
        }

        if (network2 == null) {

            throw new IllegalArgumentException("No neural network (null) was specified!");
        }

        List<String> differences = new ArrayList<>();

        if (network1.synapseLayers() != network2.synapseLayers()) {

            String message =
                String.format("The specified networks have a different number of synapse layers (%d != %d)!",
                              network1.synapseLayers(), network2.synapseLayers());
            differences.add(message);

            return differences;
        }

        for (int indexLayer = 0; indexLayer < network1.synapseLayers(); indexLayer++) {

            if (network1.synapsesInLayer(indexLayer).size() != network2.synapsesInLayer(indexLayer).size()) {

                String message =
                    String.format("The specified networks have a different number of synapses in layer %s (%d != %d)!",
                                  indexLayer, network1.synapsesInLayer(indexLayer).size(),
                                  network2.synapsesInLayer(indexLayer).size());
                differences.add(message);

                return differences;
            }

            for (int indexSynapse = 0; indexSynapse < network1.synapsesInLayer(indexLayer).size(); indexSynapse++) {

                Synapse synapse1 = network1.synapsesInLayer(indexLayer).get(indexSynapse);
                Synapse synapse2 = network2.synapsesInLayer(indexLayer).get(indexSynapse);

                if (!synapse1.weight().equals(synapse2.weight())) {

                    String message =
                        String.format("Synapse weights don't match (%s != %s)!", synapse1.weight(), synapse2.weight());
                    differences.add(message);
                }
            }
        }

        if (network1.neuronLayers() != network2.neuronLayers()) {

            String message =
                String.format("The specified networks have a different number of neuron layers (%d != %d)!",
                              network1.synapseLayers(), network2.synapseLayers());
            differences.add(message);

            return differences;
        }

        for (int indexLayer = 0; indexLayer < network1.neuronLayers(); indexLayer++) {

            Layer layer1 = network1.layer(indexLayer);
            Layer layer2 = network2.layer(indexLayer);

            if (!layer1.bias().equals(layer2.bias())) {

                String message = String.format("Layer bias don't match (%s != %s)!", layer1.bias(), layer2.bias());
                differences.add(message);
            }
        }

        return differences;
    }

}
