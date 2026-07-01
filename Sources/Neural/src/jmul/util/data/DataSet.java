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


import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * This class represents a data set. A data set contains one or more data containers with classified data (i.e. input
 * data, etc.).<br>
 * <br>
 * This data structure contains data which can be fed to a neural network.
 *
 * @author Kristian Kutin
 */
public final class DataSet implements Iterable<Data> {

    /**
     * The actual data container.
     */
    private final List<Data> list;

    /**
     * Creates a new instance accordign to the specified parameters.
     *
     * @param data
     *        some data
     */
    public DataSet(Data... data) {

        super();

        checkAndReturnParameter(data);

        this.list = arrayToList(data);
    }

    /**
     * Creates a new instance accordign to the specified parameters.
     *
     * @param data
     *        some data
     */
    public DataSet(List<Data> data) {

        super();

        checkAndReturnParameter(data);

        this.list = Collections.unmodifiableList(data);
    }

    /**
     * Checks the specified parameter and returns a reference.
     *
     * @param data
     *        some data
     *
     * @return the specified parameter
     */
    private static Data[] checkAndReturnParameter(Data[] data) {

        if (data == null) {

            throw new IllegalArgumentException("No data (null) was specified!");
        }

        if (data.length == 0) {

            throw new IllegalArgumentException("No data (empty array) was specified!");
        }

        return data;
    }

    /**
     * Checks the specified parameter and returns a reference.
     *
     * @param data
     *        some data
     *
     * @return the specified parameter
     */
    private static List<Data> checkAndReturnParameter(List<Data> data) {

        if (data == null) {

            throw new IllegalArgumentException("No data (null) was specified!");
        }

        if (data.size() == 0) {

            throw new IllegalArgumentException("No data (empty list) was specified!");
        }

        return data;
    }

    /**
     * Rewraps the specified array into a list.
     *
     * @param data
     *        some data
     *
     * @return a list
     */
    private static List<Data> arrayToList(Data[] data) {

        return Collections.unmodifiableList(Arrays.asList(data));
    }

    /**
     * Returns an interator.
     *
     * @return an interator
     */
    @Override
    public Iterator<Data> iterator() {

        return list.iterator();
    }

    /**
     * Returns a string representation for this data entry.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return list.toString();
    }

    /**
     * Returns a number base.
     *
     * @return a number base
     */
    public int base() {

        return list.iterator().next().base();
    }

}
