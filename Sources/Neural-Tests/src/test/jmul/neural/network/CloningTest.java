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

package test.jmul.neural.network;


import java.util.List;

import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.neural.network.Layout;
import jmul.neural.network.Network;
import jmul.neural.network.NetworkHelper;
import jmul.neural.processing.DataEntryProcessorImpl;
import jmul.neural.processing.Processor;

import jmul.test.classification.UnitTest;

import jmul.util.data.DataEntry;
import jmul.util.data.DataHelper;
import jmul.util.data.Descriptors;

import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suit tests cloning an eural network.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CloningTest {

    /**
     * Tests cloning a neural network.
     */
    @Test
    public void testCloneNeuralNetwork() {

        // prepare the test

        int base = 10;

        Layout layout = new Layout(3, 5, 6, 4);
        Network network = NetworkHelper.createNetwork(base, layout);


        // clone the network

        Network clone = NetworkHelper.cloneNetwork(network);


        // check the network and the clone

        List<String> differences = NetworkHelper.compareNetworks(network, clone);
        assertTrue(differences.toString(), differences.isEmpty());
    }

    /**
     * Tests signal processing on a neural network and a cloned neural network.
     */
    @Test
    public void testProcessSignal() {

        // prepare the test

        int base = 10;

        Layout layout = new Layout(3, 5, 6, 4);
        Network network = NetworkHelper.createNetwork(base, layout);


        // clone the network

        Network clone = NetworkHelper.cloneNetwork(network);


        // pepare the data

        DataEntry input =
            new DataEntry(Descriptors.INPUT, createNumber(base, "0.5"), createNumber(base, "0.5"),
                          createNumber(base, "0.5"));


        // signal processing

        Processor<DataEntry> processor1 = new DataEntryProcessorImpl(null, null, network);
        Processor<DataEntry> processor2 = new DataEntryProcessorImpl(null, null, clone);

        DataEntry actualOutput1 = processor1.process(input);
        DataEntry actualOutput2 = processor2.process(input);


        // check the results

        List<String> differences = DataHelper.checkData(actualOutput1, actualOutput2);
        assertTrue(differences.toString(), differences.isEmpty());
    }

}
