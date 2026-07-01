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


import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * This class represents a data container. The data container contains one or more data entries. The data entries
 * have a classification in order to distinguish input data, output data and expected output data.<br>
 * <br>
 * This data structure contains data which can be fed to a neural network.
 *
 * @author Kristian Kutin
 */
public final class Data implements Iterable<DataEntry> {

    /**
     * The actual data container.
     */
    private final Map<Descriptor, DataEntry> entries;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param entries
     *        all data entries
     */
    public Data(DataEntry... entries) {

        super();

        this.entries = Collections.unmodifiableMap(arrayToMap(checkAndReturnParameter(entries)));
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param entries
     *        all data entries
     */
    public Data(List<DataEntry> entries) {

        this.entries = Collections.unmodifiableMap(listToMap(checkAndReturnParameter(entries)));
    }

    /**
     * Checks and returns the specified parameter.
     *
     * @param entries
     *        all data entries
     *
     * @return the specified parameter
     */
    private static DataEntry[] checkAndReturnParameter(DataEntry[] entries) {

        if (entries == null) {

            throw new IllegalArgumentException("No data entries (null) were specified!");
        }

        if (entries.length == 0) {

            throw new IllegalArgumentException("No data entries (empty array) were specified!");
        }

        for (DataEntry dataEntry : entries) {

            Descriptor forbiddenDescriptor = Descriptors.AVERAGE_MEAN_ERROR;
            if (forbiddenDescriptor.equals(dataEntry.descriptor)) {

                String message =
                    String.format("An invalid data entry with the descriptor \"%s\" has been specified!",
                                  forbiddenDescriptor);
                throw new IllegalArgumentException(message);
            }
        }

        return entries;
    }

    /**
     * Checks and returns the specified parameter.
     *
     * @param entries
     *        all data entries
     *
     * @return the specified parameter
     */
    private static List<DataEntry> checkAndReturnParameter(List<DataEntry> entries) {

        if (entries == null) {

            throw new IllegalArgumentException("No data entries (null) were specified!");
        }

        if (entries.size() == 0) {

            throw new IllegalArgumentException("No data entries (empty list) were specified!");
        }

        for (DataEntry dataEntry : entries) {

            Descriptor forbiddenDescriptor = Descriptors.AVERAGE_MEAN_ERROR;
            if (forbiddenDescriptor.equals(dataEntry.descriptor)) {

                String message =
                    String.format("An invalid data entry with the descriptor \"%s\" has been specified!",
                                  forbiddenDescriptor);
                throw new IllegalArgumentException(message);
            }
        }

        return entries;
    }

    /**
     * Translates the specified data entries to a map.
     *
     * @param entries
     *        all data entries
     *
     * @return a map
     */
    private static Map<Descriptor, DataEntry> arrayToMap(DataEntry[] entries) {

        Map<Descriptor, DataEntry> map = new HashMap<>();

        for (DataEntry entry : entries) {

            map.put(entry.descriptor, entry);
        }

        return map;
    }

    /**
     * Translates the specified data entries to a map.
     *
     * @param entries
     *        all data entries
     *
     * @return a map
     */
    private static Map<Descriptor, DataEntry> listToMap(List<DataEntry> entries) {

        Map<Descriptor, DataEntry> map = new HashMap<>();

        for (DataEntry entry : entries) {

            map.put(entry.descriptor, entry);
        }

        return map;
    }

    /**
     * Returns the data entry associated with the specified descriptor.
     *
     * @param descriptor
     *         a descriptor
     *
     * @return a data entry
     */
    public DataEntry get(Descriptor descriptor) {

        return entries.get(descriptor);
    }

    /**
     * Checks if a data entry exists which is associated with the specified descriptor.
     *
     * @param descriptor
     *        a descriptor
     *
     * @return <code>true</code>
     */
    public boolean containsDataEntry(Descriptor descriptor) {

        return entries.containsKey(descriptor);
    }

    /**
     * Returns a number base.
     *
     * @return a number base
     */
    public int base() {

        return entries.values().iterator().next().base();
    }

    /**
     * Returns an iterator for all data entries.
     *
     * @return an iterator
     */
    @Override
    public Iterator<DataEntry> iterator() {

        return entries.values().iterator();
    }

    /**
     * Returns a string representation for this data entry.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return entries.values().toString();
    }

}
