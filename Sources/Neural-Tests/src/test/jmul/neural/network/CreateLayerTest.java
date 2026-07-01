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


import jmul.neural.network.Layer;
import jmul.neural.network.NetworkHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite test creating network layers.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CreateLayerTest {

    /**
     * Tests creating an input layer.
     */
    @Test
    public void testCreateInputLayer() {

        int base = 10;
        String name = "input layer";

        Layer layer = NetworkHelper.createInputLayer(base);

        assertEquals("#name", name, layer.name());
        assertNotNull("#bias", layer.bias());
        assertFalse("#layer", layer.isOutputLayer());
        assertTrue("#layer", layer.isInputLayer());
    }

    /**
     * Tests creating an output layer.
     */
    @Test
    public void testCreateOutputLayer() {

        int base = 10;
        String name = "output layer";

        Layer layer = NetworkHelper.createOutputLayer(base);

        assertEquals("#name", name, layer.name());
        assertNotNull("#bias", layer.bias());
        assertFalse("#layer", layer.isInputLayer());
        assertTrue("#layer", layer.isOutputLayer());
    }

    /**
     * Tests creating an inner layer.
     */
    @Test
    public void testCreateInnerLayer() {

        int base = 10;
        String name = "a";

        Layer layer = NetworkHelper.createLayer(base, name);

        assertEquals("#name", name, layer.name());
        assertNotNull("#bias", layer.bias());
        assertFalse("#layer", layer.isOutputLayer());
        assertFalse("#layer", layer.isInputLayer());
    }

}
