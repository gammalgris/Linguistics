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

import jmul.neural.network.Layout;
import jmul.neural.network.Network;
import jmul.neural.network.NetworkHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertFalse;
import org.junit.Test;


/**
 * THis test suite tests comparing neural networks.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CompareNetworkTest {

    /**
     * Test comparing twi neural networks with different layouts.
     */
    @Test
    public void testComparingNetworksWithDfferentLayouts() {

        // prepare the test

        int base = 10;

        Layout layout1 = new Layout(3, 5, 6, 4);
        Network network1 = NetworkHelper.createNetwork(base, layout1);

        Layout layout2 = new Layout(3, 5, 6, 3);
        Network network2 = NetworkHelper.createNetwork(base, layout2);


        // check the networks

        List<String> differences = NetworkHelper.compareNetworks(network1, network2);
        assertFalse(differences.toString(), differences.isEmpty());
    }

    /**
     * Test comparing twi neural networks with illegal parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testComparingNetworksWithIllegalParameters() {

        // prepare the test

        int base = 10;

        Network network1 = null;

        Layout layout2 = new Layout(3, 5, 6, 3);
        Network network2 = NetworkHelper.createNetwork(base, layout2);


        // check the networks

        List<String> differences = NetworkHelper.compareNetworks(network1, network2);
        assertFalse(differences.toString(), differences.isEmpty());
    }

    /**
     * Test comparing twi neural networks with illegal parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testComparingNetworksWithIllegalParametersVariant2() {

        // prepare the test

        int base = 10;

        Layout layout1 = new Layout(3, 5, 6, 4);
        Network network1 = NetworkHelper.createNetwork(base, layout1);

        Network network2 = null;


        // check the networks

        List<String> differences = NetworkHelper.compareNetworks(network1, network2);
        assertFalse(differences.toString(), differences.isEmpty());
    }

}
