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

package jmul.util.data;


import java.util.Iterator;
import java.util.List;

import jmul.math.collections.CollectionsHelper;
import jmul.math.collections.Sequence;
import jmul.math.numbers.Number;


/**
 * This class represents a data entry. A data entry contains a descriptor for purposes of classification (e.g. input
 * data, output data, etc.) and one or more numbers (i.e. sequence of numbers).<br>
 * <br>
 * This data structure contains data which can be fed to a neural network. Input data should match the input layer of a
 * neural network. Output data should match the output layer of a neural network.
 *
 * @author Kristian Kutin
 */
public final class DataEntry implements Iterable<Number> {

    /**
     * The name for this data entry.
     */
    public final Descriptor descriptor;

    /**
     * A sequence of numbers.
     */
    private final Sequence<Number> data;

    /**
     * Creates a new data entry.
     *
     * @param descriptor
     *        a data descriptor
     * @param numbers
     *        a sequence of numbers
     */
    public DataEntry(Descriptor descriptor, Number... numbers) {

        super();

        this.descriptor = checkAndReturnParameter(descriptor);
        this.data = CollectionsHelper.createNumberSequence(numbers);
    }

    /**
     * Creates a new data entry.
     *
     * @param descriptor
     *        a data descriptor
     * @param numbers
     *        a sequence of numbers
     */
    public DataEntry(Descriptor descriptor, List<Number> numbers) {

        super();

        this.descriptor = checkAndReturnParameter(descriptor);
        this.data = CollectionsHelper.createNumberSequence(numbers);
    }

    /**
     * Creates a new data entry.
     *
     * @param descriptor
     *        a data descriptor
     * @param numbers
     *        a sequence of numbers
     */
    public DataEntry(Descriptor descriptor, Iterable<Number> numbers) {

        super();

        this.descriptor = checkAndReturnParameter(descriptor);
        this.data = CollectionsHelper.createNumberSequence(numbers);
    }

    /**
     * Checks the specified parameter and returns it.
     *
     * @param descriptor
     *        a data descriptor
     *
     * @return the specified parameter
     */
    private static Descriptor checkAndReturnParameter(Descriptor descriptor) {

        if (descriptor == null) {

            throw new IllegalArgumentException("No descriptor (null) was specified!");
        }

        return descriptor;
    }

    /**
     * Returns an interator for this data entry.
     *
     * @return an interator
     */
    @Override
    public Iterator<Number> iterator() {

        return data.iterator();
    }

    /**
     * Returns a number base.
     *
     * @return a number base
     */
    public int base() {

        return data.base();
    }

    /**
     * Returns a string representation for this data entry.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("%s%s", descriptor, data);
    }

}
