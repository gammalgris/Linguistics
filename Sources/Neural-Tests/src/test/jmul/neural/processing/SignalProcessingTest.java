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

package test.jmul.neural.processing;


import java.util.List;

import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.neural.cells.Neuron;
import jmul.neural.cells.Synapse;
import jmul.neural.network.Layer;
import jmul.neural.network.Layout;
import jmul.neural.network.Network;
import jmul.neural.network.NetworkHelper;
import jmul.neural.processing.DataEntryProcessorImpl;
import jmul.neural.processing.DataProcessorImpl;
import jmul.neural.processing.DataSetProcessorImpl;
import jmul.neural.processing.Processor;
import jmul.neural.signals.Signal;

import jmul.test.classification.UnitTest;

import jmul.util.data.Data;
import jmul.util.data.DataEntry;
import jmul.util.data.DataHelper;
import jmul.util.data.DataSet;
import jmul.util.data.Descriptors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite test the signal processing in a neural network (input layer -&gt; hidden layer -&gt; output layer).
 *
 * @author Kristian Kutin
 */
@UnitTest
public class SignalProcessingTest {

    /**
     * Tests signal processing in a specific neural network (i.e. a small sized network with a single neuron
     * in the input and output layer).<br>
     * <br>
     * <i>Note:<br>
     * This test uses a small neural network where it's possible to calculate the signals and signal
     * modifications by hand at every neuron and synapse it is passed to.</i>
     */
    @Test
    public void testSignalProcessing() {

        // Prepare network.

        int base = 10;
        Layout layout = new Layout(1, 2, 1);
        Network network = NetworkHelper.createNetwork(base, layout);

        // Override the random weights and biases.

        Number defaultWeight = createNumber(base, "0.5");
        Number defaultBias = createNumber(base, "1");

        for (int layer = 0; layer < network.synapseLayers(); layer++) {

            for (Synapse synapse : network.synapsesInLayer(layer)) {

                synapse.updateWeight(defaultWeight);
            }
        }

        for (int ordinal = 1; ordinal < network.neuronLayers() - 1; ordinal++) {

            Layer layer = network.layer(ordinal);
            layer.updateBias(defaultBias);
        }

        // Prepare signal.

        Number number = NumberHelper.createNumber(base, "1");
        Signal signal = new Signal(null, number);

        // Send signal.

        for (Neuron neuron : network.inputLayer()) {

            neuron.receiveSignal(signal);
        }

        // Receive result signal.

        Neuron outputNeuron = network.outputLayer().get(0);
        Signal outputSignal = outputNeuron.signal();
        Number actualResult = outputSignal.value;

        // Compare result.

        Number expectedResult = createNumber(base, "0.825");
        assertEquals("#signal", expectedResult, actualResult);
    }

    /**
     * Tests signal processing in a specific neural network (i.e. a medium sized network with two neurons in the
     * input layer and one neuron in the output layer).
     */
    @Test
    public void testSignalProcessing2() {

        // Prepare network.

        int base = 10;
        Layout layout = new Layout(2, 4, 3, 2, 1);
        Network network = NetworkHelper.createNetwork(base, layout);

        // Override the random weights and biases.

        Number defaultWeight = createNumber(base, "0.5");
        Number defaultBias = createNumber(base, "1");

        for (int layer = 0; layer < network.synapseLayers(); layer++) {

            for (Synapse synapse : network.synapsesInLayer(layer)) {

                synapse.updateWeight(defaultWeight);
            }
        }

        for (int ordinal = 1; ordinal < network.neuronLayers() - 1; ordinal++) {

            Layer layer = network.layer(ordinal);
            layer.updateBias(defaultBias);
        }

        // Prepare signal.

        Number number = NumberHelper.createNumber(base, "1");
        Signal signal = new Signal(null, number);

        // Send signal.

        for (Neuron neuron : network.inputLayer()) {

            neuron.receiveSignal(signal);
        }

        // Receive result signal.

        Neuron outputNeuron = network.outputLayer().get(0);
        Signal outputSignal = outputNeuron.signal();
        Number actualResult = outputSignal.value;

        // Compare result.

        Number expectedResult = createNumber(base, "0.9205");
        assertEquals("#signal", expectedResult, actualResult);
    }

    /**
     * Tests signal processing in a specific neural network (i.e. a medium sized network with 4 neurons in the
     * input layer and 1 neuron in the output layer).
     */
    @Test
    public void testSignalProcessing3() {

        // Prepare network.

        int base = 10;
        Layout layout = new Layout(4, 20, 10, 5, 2, 1);
        Network network = NetworkHelper.createNetwork(base, layout);

        // Override the random weights and biases.

        Number defaultWeight = createNumber(base, "0.5");
        Number defaultBias = createNumber(base, "1");

        for (int layer = 0; layer < network.synapseLayers(); layer++) {

            for (Synapse synapse : network.synapsesInLayer(layer)) {

                synapse.updateWeight(defaultWeight);
            }
        }

        for (int ordinal = 1; ordinal < network.neuronLayers() - 1; ordinal++) {

            Layer layer = network.layer(ordinal);
            layer.updateBias(defaultBias);
        }

        // Prepare signal.

        Number number = NumberHelper.createNumber(base, "1");
        Signal signal = new Signal(null, number);

        // Send signal.

        for (Neuron neuron : network.inputLayer()) {

            neuron.receiveSignal(signal);
        }

        // Receive result signal.

        Neuron outputNeuron = network.outputLayer().get(0);
        Signal outputSignal = outputNeuron.signal();
        Number actualResult = outputSignal.value;

        // Compare result.

        Number expectedResult = createNumber(base, "0.975");
        assertEquals("#signal", expectedResult, actualResult);
    }

    /**
     * Tests signal processing with custom data types for input and output (i.e. DataEntry).
     */
    @Test
    public void testSignalProcessing4() {

        // Prepare network.

        int base = 10;
        Layout layout = new Layout(1, 2, 1);
        Network network = NetworkHelper.createNetwork(base, layout);

        // Override the random weights and biases.

        Number defaultWeight = createNumber(base, "0.5");
        Number defaultBias = createNumber(base, "1");

        for (int layer = 0; layer < network.synapseLayers(); layer++) {

            for (Synapse synapse : network.synapsesInLayer(layer)) {

                synapse.updateWeight(defaultWeight);
            }
        }

        for (int ordinal = 1; ordinal < network.neuronLayers() - 1; ordinal++) {

            Layer layer = network.layer(ordinal);
            layer.updateBias(defaultBias);
        }

        // Prepare data

        DataEntry inputData = new DataEntry(Descriptors.INPUT, createNumber(base, "0.15"));

        DataEntry expectedResult = new DataEntry(Descriptors.OUTPUT, createNumber(base, "0.76125"));

        // Prepare the processor

        Processor<DataEntry> processor = new DataEntryProcessorImpl(null, null, network);

        // Send signal.

        DataEntry actualResult = processor.process(inputData);

        // Check results

        List<String> list = DataHelper.checkData(expectedResult, actualResult);
        assertTrue(list.toString(), list.isEmpty());
        assertTrue(list.toString(), DataHelper.compareData(expectedResult, actualResult));
    }

    /**
     * Tests signal processing with custom data types for input and output (i.e. Data).
     */
    @Test
    public void testSignalProcessing5() {

        // Prepare network.

        int base = 10;
        Layout layout = new Layout(2, 4, 3);
        Network network = NetworkHelper.createNetwork(base, layout);

        // Override the random weights and biases.

        Number defaultWeight = createNumber(base, "0.5");
        Number defaultBias = createNumber(base, "1");

        for (int layer = 0; layer < network.synapseLayers(); layer++) {

            for (Synapse synapse : network.synapsesInLayer(layer)) {

                synapse.updateWeight(defaultWeight);
            }
        }

        for (int ordinal = 1; ordinal < network.neuronLayers() - 1; ordinal++) {

            Layer layer = network.layer(ordinal);
            layer.updateBias(defaultBias);
        }

        // Prepare data

        Data inputData =
            new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.15"), createNumber(base, "0.15")));

        Data expectedResult =
            new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.15"), createNumber(base, "0.15")),
                     new DataEntry(Descriptors.OUTPUT, createNumber(base, "1.545"), createNumber(base, "1.545"),
                                   createNumber(base, "1.545")));

        // Prepare the processor

        Processor<Data> processor = new DataProcessorImpl(null, null, network);

        // Send signal.

        Data actualResult = processor.process(inputData);

        // Check results

        List<String> list = DataHelper.checkData(expectedResult, actualResult);
        assertTrue(list.toString(), list.isEmpty());
        assertTrue(list.toString(), DataHelper.compareData(expectedResult, actualResult));
    }

    /**
     * Tests signal processing with custom data types for input and output (i.e. DataSet).
     */
    @Test
    public void testSignalProcessing6() {

        // Prepare network.

        int base = 10;
        Layout layout = new Layout(2, 4, 3);
        Network network = NetworkHelper.createNetwork(base, layout);

        // Override the random weights and biases.

        Number defaultWeight = createNumber(base, "0.5");
        Number defaultBias = createNumber(base, "1");

        for (int layer = 0; layer < network.synapseLayers(); layer++) {

            for (Synapse synapse : network.synapsesInLayer(layer)) {

                synapse.updateWeight(defaultWeight);
            }
        }

        for (int ordinal = 1; ordinal < network.neuronLayers() - 1; ordinal++) {

            Layer layer = network.layer(ordinal);
            layer.updateBias(defaultBias);
        }

        // Prepare data

        DataSet inputData =
            new DataSet(new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.15"),
                                               createNumber(base, "0.15"))),
                        new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.15"),
                                               createNumber(base, "0.15"))));

        DataSet expectedResult =
            new DataSet(new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.15"),
                                               createNumber(base, "0.15")),
                                 new DataEntry(Descriptors.OUTPUT, createNumber(base, "1.545"),
                                               createNumber(base, "1.545"), createNumber(base, "1.545"))),
                        new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.15"),
                                               createNumber(base, "0.15")),
                                 new DataEntry(Descriptors.OUTPUT, createNumber(base, "1.545"),
                                               createNumber(base, "1.545"), createNumber(base, "1.545"))));

        // Prepare the processor

        Processor<DataSet> processor = new DataSetProcessorImpl(null, null, network);

        // Send signal.

        DataSet actualResult = processor.process(inputData);

        // Check results

        List<String> list = DataHelper.checkData(expectedResult, actualResult);
        assertTrue(list.toString(), list.isEmpty());
        assertTrue(list.toString(), DataHelper.compareData(expectedResult, actualResult));
    }

}
