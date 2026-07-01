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


/**
 * This interface describes a network layout.
 *
 * @author Kristian Kutin
 */
public final class Layout {

    /**
     * An array which contains the network layout (i.e. neurons per layer).
     */
    private final int[] neuronsPerLayer;

    /**
     * Creates a new layout according to the specified parameters.
     *
     * @param neuronsPerLayer
     *        the number of neurons per layer
     */
    public Layout(int... neuronsPerLayer) {

        super();

        this.neuronsPerLayer = checkAndReturnParameter(neuronsPerLayer);
    }

    /**
     * Checks the specified parameter and returns it.
     *
     * @param neuronsPerLayer
     *        the number of neurons per layer
     *
     * @return the specified parameter
     */
    private static int[] checkAndReturnParameter(int[] neuronsPerLayer) {

        if (neuronsPerLayer == null) {

            throw new IllegalArgumentException("No neuron layout (null) was specified!");
        }

        if (neuronsPerLayer.length <= 2) {

            throw new IllegalArgumentException("The layout must at least consist of two layers!");
        }

        for (int index = 0; index < neuronsPerLayer.length; index++) {

            if (neuronsPerLayer[index] <= 0) {

                throw new IllegalArgumentException("A layer must contain at least one neuron!");
            }
        }

        return neuronsPerLayer;
    }

    /**
     * Returns the number of layers which are specified.
     *
     * @return the number of layers
     */
    public int layers() {

        return neuronsPerLayer.length;
    }

    /**
     * Returns the number of neurons for the specified layer.
     *
     * @param ordinal
     *        the ordinal number associated with a network layer
     *
     * @return the number of neurons for the speicifed network layer
     */
    public int neuronsInLayer(int ordinal) {

        return neuronsPerLayer[ordinal];
    }

    /**
     * Returns a string representation for this network layout.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("(");

        boolean first = true;
        for (int neurons : neuronsPerLayer) {

            if (first) {

                first = false;

            } else {

                buffer.append("; ");
            }

            buffer.append(neurons);
        }

        buffer.append(")");

        return buffer.toString();
    }

}
