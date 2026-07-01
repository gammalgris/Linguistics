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

package test.jmul.neural.cells;


import jmul.math.functions.Function;
import jmul.math.functions.FunctionHelper;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;

import jmul.neural.ConfigurationException;
import jmul.neural.cells.CellHelper;
import jmul.neural.cells.Neuron;
import jmul.neural.cells.Synapse;
import jmul.neural.network.Layer;
import jmul.neural.network.NetworkHelper;
import jmul.neural.signals.Signal;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Test;


/**
 * This test suite tests creating various cells.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CreateCellsTest {

    /**
     * Tests creating a synapse.
     */
    @Test
    public void testCreateSynapse() {

        int base = 10;

        Synapse synapse = CellHelper.createSynapse(base);
        assertNotNull("#weight", synapse.weight());

        Number number = NumberHelper.createNumber(base, "1");
        Signal signal = new Signal(null, number);

        try {

            synapse.transmitSignal(signal);
            fail();

        } catch (ConfigurationException e) {

            // ignore as it's supposed to throw the exception
        }
    }

    /**
     * Tests creating an input neuron.
     */
    @Test
    public void testCreateInputNeuron() {

        int base = 10;
        Layer layer = NetworkHelper.createInputLayer(base);
        Function function = FunctionHelper.createPolynomialFunction(base, "1");

        Neuron neuron = CellHelper.createNeuron(layer, function);

        assertEquals("#layer", layer, neuron.layer());
        assertNull("#signal", neuron.signal());


        Synapse synapse = CellHelper.createSynapse(base);

        try {

            neuron.addSignalSource(synapse);
            fail();

        } catch (UnsupportedOperationException e) {

        }

        try {

            neuron.removeSignalSource(synapse);
            fail();

        } catch (UnsupportedOperationException e) {

        }

        Number number = NumberHelper.createNumber(base, "1");
        Signal signal = new Signal(null, number);
        try {

            neuron.receiveSignal(signal);
            fail();

        } catch (ConfigurationException e) {

        }

        try {

            neuron.transmitSignal(signal);
            fail();

        } catch (ConfigurationException e) {

        }
    }

    /**
     * Tests creating an output neuron.
     */
    @Test
    public void testCreateOutputNeuron() {

        int base = 10;
        Layer layer = NetworkHelper.createOutputLayer(base);
        Function function = FunctionHelper.createPolynomialFunction(base, "1");

        Neuron neuron = CellHelper.createNeuron(layer, function);

        assertEquals("#layer", layer, neuron.layer());
        assertNull("#signal", neuron.signal());


        Synapse synapse = CellHelper.createSynapse(base);

        try {

            neuron.addTransmitter(synapse);
            fail();

        } catch (UnsupportedOperationException e) {

        }

        try {

            neuron.removeTransmitter(synapse);
            fail();

        } catch (UnsupportedOperationException e) {

        }

        Number number = NumberHelper.createNumber(base, "1");
        Signal signal = new Signal(null, number);
        try {

            neuron.transmitSignal(signal);
            fail();

        } catch (UnsupportedOperationException e) {

        }

        try {

            neuron.receiveSignal(signal);
            fail();

        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Tests creating an inner neuron.
     */
    @Test
    public void testCreateInnerNeuron() {

        int base = 10;
        Layer layer = NetworkHelper.createLayer(base, "inner layer");
        Function function = FunctionHelper.createPolynomialFunction(base, "1");

        Neuron neuron = CellHelper.createNeuron(layer, function);

        assertEquals("#layer", layer, neuron.layer());
        assertNull("#signal", neuron.signal());


        Number number = NumberHelper.createNumber(base, "1");
        Signal signal = new Signal(null, number);
        try {

            neuron.receiveSignal(signal);
            fail();

        } catch (IllegalArgumentException e) {

        }

        try {

            neuron.transmitSignal(signal);
            fail();

        } catch (ConfigurationException e) {

        }
    }

}
