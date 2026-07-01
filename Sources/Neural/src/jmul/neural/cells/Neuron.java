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


import jmul.neural.network.Layer;
import jmul.neural.signals.Signal;


/**
 * This interface describes a neuron.
 *
 * @author Kristian Kutin
 */
public interface Neuron extends Cell, Transmitter {

    /**
     * Returns a reference to the network layer associated with this neuron.
     *
     * @return a reference to a network layer
     */
    Layer layer();

    /**
     * Adds the specified synapse as transmitter for signals.
     *
     * @param synapse
     *        a synapse
     */
    void addTransmitter(Synapse synapse);

    /**
     * Removes the specified synapse as transmitter for signals.
     *
     * @param synapse
     *        a synapse
     */
    void removeTransmitter(Synapse synapse);

    /**
     * Receives the specified signal.  The signal needs to be amplified/ dampened and rewrapped.
     *
     * @param signal
     *        a signal
     */
    void receiveSignal(Signal signal);

    /**
     * Makes known the specified signal source to this neuron.
     *
     * @param synapse
     *        a signal source
     */
    void addSignalSource(Synapse synapse);

    /**
     * Removes the specified signal source.
     *
     * @param synapse
     *        a signal source
     */
    void removeSignalSource(Synapse synapse);

    /**
     * Returns the last signal that is ready to be transmitted.
     *
     * @return a signal
     */
    Signal signal();

}
