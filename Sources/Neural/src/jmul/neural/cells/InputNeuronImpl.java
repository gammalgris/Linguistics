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


import java.util.ArrayList;
import java.util.List;

import jmul.math.functions.Function;
import jmul.math.numbers.Number;

import jmul.neural.ConfigurationException;
import jmul.neural.network.Layer;
import jmul.neural.signals.Signal;


/**
 * An implementation of an input neuron. An input neuron doesn't receive signals.
 *
 * @author Kristian Kutin
 */
class InputNeuronImpl implements Neuron {

    /**
     * A number base.
     */
    private final int base;

    /**
     * A reference to common network layer informations.
     */
    private final Layer layer;

    /**
     * A list of all known transmitters (i.e. to transmit incoming signals).
     */
    private List<Synapse> transmitters;

    /**
     * The activation fucntion of this neuron.
     */
    private final Function activationFunction;

    /**
     * Stores the signal which is ready to transmit. Initially there is no signal.
     */
    private Signal signalToTransmit;

    /**
     * Creates a new neuron according to the specified parameters.
     *
     * @param layer
     *        a reference to common network layer informations
     * @param activationFunction
     *        an activation function
     */
    public InputNeuronImpl(Layer layer, Function activationFunction) {

        super();

        // Check some potential error causes beforehand. These should not go unnoticed.

        if (activationFunction == null) {

            throw new IllegalArgumentException("No activation function (null) was specified!");
        }

        this.base = activationFunction.base();
        this.layer = layer;
        this.activationFunction = activationFunction;

        this.transmitters = new ArrayList<>();

        this.signalToTransmit = null;
    }

    /**
     * Returns a reference to the network layer associated with this neuron.
     *
     * @return a reference to a network layer
     */
    @Override
    public Layer layer() {

        return layer;
    }

    /**
     * Transmits the specified signal via several synapses. The signal is amplified/ dampened and rewrapped.
     *
     * @param signal
     *        a signal
     */
    @Override
    public void transmitSignal(Signal signal) {

        if (transmitters.isEmpty()) {

            throw new ConfigurationException("There are no transmitters yet!");
        }

        for (Synapse synapse : transmitters) {

            synapse.transmitSignal(signalToTransmit);
        }
    }

    /**
     * Processes all signals and returns a new signal.
     *
     * @param signal
     *        a signal
     *
     * @return returns a processed signal
     */
    private Signal processSignals(Signal signal) {

        Number result = signal.value;

        Number bias = layer().bias();
        result = result.add(bias);
        result = activationFunction.calculate(result);

        Signal newSignal = new Signal(this, result);

        return newSignal;
    }

    /**
     * Adds the specified synapse as transmitter for signals.
     *
     * @param synapse
     *        a synapse
     */
    @Override
    public void addTransmitter(Synapse synapse) {

        // Check some potential error causes beforehand. These should not go unnoticed.

        if (synapse == null) {

            throw new IllegalArgumentException("No synapse (null) was specified!");
        }

        if (transmitters.contains(synapse)) {

            throw new IllegalArgumentException("The synapse is already known!");
        }

        transmitters.add(synapse);
    }

    /**
     * Removes the specified synapse as transmitter for signals.
     *
     * @param synapse
     *        a synapse
     */
    @Override
    public void removeTransmitter(Synapse synapse) {

        // Check some potential error causes beforehand. These should not go unnoticed.

        if (synapse == null) {

            throw new IllegalArgumentException("No synapse (null) was specified!");
        }

        if (!transmitters.contains(synapse)) {

            throw new IllegalArgumentException("The synapse is not known!");
        }

        transmitters.remove(synapse);
    }

    /**
     * Receives the specified signal. The signal needs to be amplified/ dampened and rewrapped.
     *
     * @param signal
     *        a signal
     */
    @Override
    public void receiveSignal(Signal signal) {

        // Check some potential error causes beforehand. These should not go unnoticed.

        if (signal == null) {

            throw new IllegalArgumentException("No signal (null) was specified!");
        }

        signalToTransmit = processSignals(signal);
        transmitSignal(signalToTransmit);
    }

    /**
     * Makes known the specified signal source to this neuron.
     *
     * @param synapse
     *        a signal source
     */
    @Override
    public void addSignalSource(Synapse synapse) {

        throw new UnsupportedOperationException("This neuron is not supposed to receive signals!");
    }

    /**
     * Removes the specified signal source.
     *
     * @param synapse
     *        a signal source
     */
    @Override
    public void removeSignalSource(Synapse synapse) {

        throw new UnsupportedOperationException("This neuron is not supposed to receive signals!");
    }

    /**
     * Returns the last signal that is ready to be transmitted.
     *
     * @return a signal
     */
    @Override
    public Signal signal() {

        return signalToTransmit;
    }

    /**
     * Returns a string representation for this neuron.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("Neuron@%d", hashCode());
    }

}
