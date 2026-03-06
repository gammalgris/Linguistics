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

package jmul.common.serialization;


import java.nio.charset.Charset;

import java.util.ResourceBundle;


/**
 * A utility class which provides access to configurable settings.
 *
 * @author Kristian Kutin
 */
public final class SerializationProperties {

    /**
     * The name of a resource bundle (i.e. properties file).
     */
    private static final String BUNDLE_NAME;

    /**
     * A property key.
     */
    private static final String FILE_ENCODING_KEY;

    /**
     * A property key.
     */
    private static final String LINE_SEPARATOR_KEY;

    /*
     * The static initializer.
     */
    static {

        BUNDLE_NAME = SerializationProperties.class.getName();

        FILE_ENCODING_KEY = "file.encoding";
        LINE_SEPARATOR_KEY = "line.separator";
    }

    /**
     * The default constructor.
     */
    public SerializationProperties() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the configured file encoding.
     *
     * @return a file encoding
     */
    public static Charset getFileEncoding() {

        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
        String value = bundle.getString(FILE_ENCODING_KEY);

        Charset charset = Charset.forName(value);

        return charset;
    }

    /**
     * Returns the configured line separator.
     *
     * @return a line separator
     */
    public static String getLineSeparator() {

        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
        String value = bundle.getString(LINE_SEPARATOR_KEY);

        return value;
    }

}
