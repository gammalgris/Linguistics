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

package jmul.neural.processing;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jmul.math.numbers.Number;

import jmul.neural.cells.Neuron;
import jmul.neural.network.Network;
import jmul.neural.signals.Signal;

import jmul.util.data.DataEntry;
import jmul.util.data.Descriptors;
import jmul.util.data.denormalization.Denormalizer;
import jmul.util.data.normalization.Normalizer;


/**
 * An implementation of a data processor for data entries.
 *
 * @author Kristian Kutin
 */
public class DataEntryProcessorImpl implements Processor<DataEntry> {

    /**
     * A data normalizer or <code>null</code> if no normalization has to be done.
     */
    private final Normalizer<DataEntry> normalizer;

    /**
     * A data denormalizer or <code>null</code> if no denormalization has to be done.
     */
    private final Denormalizer<DataEntry> denormalizer;

    /**
     * A neural network.
     */
    private final Network network;

    /**
     * Creates a new data processor according to the specified parameters.
     *
     * @param normalizer
     *        a data normalizer or <code>null</code> if no normalization has to be done
     * @param denormalizer
     *        a data denormalizer or <code>null</code> if no denormalization has to be done
     * @param network
     *        a neural network
     */
    public DataEntryProcessorImpl(Normalizer<DataEntry> normalizer, Denormalizer<DataEntry> denormalizer,
                                  Network network) {

        super();

        checkAndReturnParameter(network);

        this.normalizer = normalizer;
        this.denormalizer = denormalizer;
        this.network = network;
    }

    /**
     * Checks the specified parameter and returns it.
     *
     * @param network
     *        a neural network
     *
     * @return the specified parameter
     */
    private static Network checkAndReturnParameter(Network network) {

        if (network == null) {

            throw new IllegalArgumentException("No network (null) was specified!");
        }

        return network;
    }

    /**
     * Processes the specified data and returns the result.
     *
     * @param input
     *        some input data
     *
     * @return some output
     */
    @Override
    public DataEntry process(DataEntry input) {

        checkAndReturnParameter(input);

        // normalize data

        DataEntry normalizedInput = normalize(input);

        // process the data - feed the neural network

        Iterator<Neuron> iteratorNeurons = network.inputLayer().iterator();
        Iterator<Number> iteratorNumbers = normalizedInput.iterator();

        while (true) {

            if (iteratorNeurons.hasNext() != iteratorNumbers.hasNext()) {

                throw new DataProcessingException("The input layer of the neuron network and the data sequence don't match!");
            }

            if (!iteratorNeurons.hasNext() && !iteratorNumbers.hasNext()) {

                break;
            }

            Neuron neuron = iteratorNeurons.next();
            Number number = iteratorNumbers.next();

            Signal signal = new Signal(null, number);
            neuron.receiveSignal(signal);
        }

        iteratorNeurons = null;
        iteratorNumbers = null;

        // process the data - collect the results

        List<Number> results = new ArrayList<>();
        iteratorNeurons = network.outputLayer().iterator();

        while (iteratorNeurons.hasNext()) {

            Neuron neuron = iteratorNeurons.next();

            Signal signal = neuron.signal();
            Number number = signal.value;
            results.add(number);
        }

        iteratorNeurons = null;

        // process the data - rewrap the results

        DataEntry output = new DataEntry(Descriptors.OUTPUT, results);

        // denormalize data

        return denormalize(output);
    }

    /**
     * Checks the specified parameter and returns it.
     *
     * @param input
     *        a data entry
     *
     * @return the specified parameter
     */
    private static DataEntry checkAndReturnParameter(DataEntry input) {

        if (input == null) {

            throw new IllegalArgumentException("No data entry (null) was specified!");
        }

        if (input.descriptor != Descriptors.INPUT) {

            throw new IllegalArgumentException("The specified data entry is no input data!");
        }

        return input;
    }

    /**
     * Normalizes the specified data entry.
     *
     * @param dataEntry
     *        a data entry
     *
     * @return normalized data entry
     */
    private DataEntry normalize(DataEntry dataEntry) {

        if (normalizer == null) {

            return dataEntry;
        }

        return normalizer.normalize(dataEntry);
    }

    /**
     * Denormalizes the specified data entry.
     *
     * @param dataEntry
     *        a data entry
     *
     * @return denormalized data entry
     */
    private DataEntry denormalize(DataEntry dataEntry) {

        if (denormalizer == null) {

            return dataEntry;
        }

        return denormalizer.denormalize(dataEntry);
    }

}
