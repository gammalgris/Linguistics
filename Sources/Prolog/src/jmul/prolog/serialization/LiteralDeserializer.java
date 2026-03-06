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

package jmul.prolog.serialization;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;

import jmul.common.serialization.DeserializationException;
import jmul.common.serialization.Deserializer;
import jmul.common.serialization.SerializationProperties;

import jmul.prolog.literals.Literal;
import jmul.prolog.parsing.LiteralParser;
import jmul.prolog.parsing.Parser;


/**
 * A deserializer for literals.
 *
 * @author Kristian Kutin
 */
public class LiteralDeserializer implements Deserializer<List<Literal>> {

    /**
     * A constant value representing 'end of file'
     */
    private static final int EOF;

    /*
     * The static initializer.
     */
    static {

        EOF = -1;
    }

    /**
     * The default constructor.
     */
    public LiteralDeserializer() {

        super();
    }

    /**
     * Reads the literals from the specified file and returns a list of literals.
     *
     * @param filename
     *        a file name or file path
     *
     * @return a list of literals
     */
    @Override
    public List<Literal> readFrom(String filename) {

        if (filename == null) {

            throw new IllegalArgumentException("No filename (null) was specified!");
        }

        Charset charset = SerializationProperties.getFileEncoding();
        String lineSeparator = SerializationProperties.getLineSeparator();

        List<Literal> newLiterals = new ArrayList<>();

        Parser parser = new LiteralParser();
        StringBuilder line = new StringBuilder();
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(filename), charset)) {

            while (true) {

                int i = in.read();

                if (i != EOF) {

                    char currentChar = (char) i;
                    line.append(currentChar);
                }

                if (StringHelper.endsWith(line, lineSeparator)) {

                    String input = StringHelper.removeEnd(line, lineSeparator.length()).toString();
                    Literal literal = parser.parse(input);

                    if (literal != null) {

                        newLiterals.add(literal);
                    }

                    // Cleans the buffer without resizing or changing the array behind it. The array will
                    // be overridden with every subsequent append.
                    line.setLength(0);
                }

                if (i == EOF) {

                    break;
                }
            }

        } catch (IOException e) {

            String message = String.format("Unable to import literals from file (\"%s\")!", filename);
            throw new DeserializationException(message, e);
        }

        return newLiterals;
    }

}
