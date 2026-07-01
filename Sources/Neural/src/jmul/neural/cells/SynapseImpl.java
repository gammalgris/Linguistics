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


import jmul.math.numbers.Number;
import jmul.math.Math;

import jmul.neural.ConfigurationException;
import jmul.neural.signals.Signal;


/**
 * An implementation of a synapse.
 *
 * @author Kristian Kutin
 */
class SynapseImpl implements Synapse {

    /**
     * A number base.
     */
    private final int base;

    /**
     * A neuron this snypase is connected to and transmits signals to. Initially a
     * synapse is not connected to a neuron.
     */
    private Neuron neuron;

    /**
     * A weight to amplify or dampen a signal. The weight can be changed later.
     */
    private Number weight;

    /**
     * Creates a new synapse with a random weight and which is not connected to a neuron.
     *
     * @param base
     *        a number base
     */
    public SynapseImpl(int base) {

        super();

        this.base = base;
        this.neuron = null;
        this.weight = Math.random(base);
    }

    /**
     * Returns a string representation for this synapse.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        if (neuron == null) {

            return String.format("%d -(weight = %s)-> unconnected", "Synapse@" + hashCode(), weight);

        } else {

            return String.format("%d -(weight = %s)-> %d", "Synapse@" + hashCode(), weight, neuron);
        }
    }

    /**
     * The weight modyfiying signals.
     *
     * @return a weight
     */
    @Override
    public Number weight() {

        return weight;
    }

    /**
     * Updates the weight of this synapse.
     *
     * @param newWeight
     *        a new weight
     */
    @Override
    public void updateWeight(Number newWeight) {

        // Check some potential error causes beforehand. These should not go unnoticed.

        if (newWeight == null) {

            throw new IllegalArgumentException("No weight (null) was specified!");
        }

        if (newWeight.base() != base) {

            throw new IllegalArgumentException("The new weight is of a different number base!");
        }

        weight = newWeight;
    }

    /**
     * Transmits the specified signal to a neuron. The signal is amplified/ dampened and rewrapped.
     *
     * @param signal
     *        a signal
     */
    @Override
    public void transmitSignal(Signal signal) {

        if (signal == null) {

            throw new IllegalArgumentException("No signal (null) was specified!");
        }

        if (neuron == null) {

            throw new ConfigurationException("No neuron (null) was specified!");
        }

        Signal newSignal = modifyAndRewrapSignal(signal);
        neuron.receiveSignal(newSignal);
    }

    /**
     * Modifies (i.e. amplifies or dampens) and rewraps the specified signal.
     *
     * @param signal
     *        a signal
     *
     * @return a modified and rewrapped signal
     */
    private Signal modifyAndRewrapSignal(Signal signal) {

        Number newValue = signal.value.multiply(weight);
        Signal newSignal = new Signal(this, newValue);

        return newSignal;
    }

    /**
     * Connect this synapse with a neuron.
     *
     * @param neuron
     *        a neuron
     */
    @Override
    public void connectNeuron(Neuron neuron) {

        this.neuron = neuron;
    }

}
