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


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.nio.charset.Charset;

import java.util.Set;

import jmul.common.serialization.SerializationException;
import jmul.common.serialization.SerializationProperties;
import jmul.common.serialization.Serializer;

import jmul.prolog.LiteralRepository;
import jmul.prolog.literals.Literal;


/**
 * A serializer for literals (i.e. literal repositories).
 *
 * @author Kristian Kutin
 */
public class LiteralSerializer implements Serializer<LiteralRepository> {

    /**
     * The default constructor.
     */
    public LiteralSerializer() {

        super();
    }

    /**
     * Writes the literals within the specified literal repository to the specified file. If a file already exists it
     * will be overridden.
     *
     * @param filename
     *        a file name or file path
     * @param input
     *        the actual input
     */
    @Override
    public void writeTo(String filename, LiteralRepository input) {

        if (filename == null) {

            throw new IllegalArgumentException("No filename (null) was specified!");
        }

        Charset charset = SerializationProperties.getFileEncoding();
        String lineSeparator = SerializationProperties.getLineSeparator();

        try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filename), charset)) {

            for (String functionSymbol : input) {

                Set<Literal> subset = input.lookUp(functionSymbol);

                for (Literal literal : subset) {

                    String line = String.format("%s%s", literal, lineSeparator);
                    out.write(line);
                }
            }

        } catch (IOException e) {

            String message = String.format("Unable to export literals to file (\"%s\")!", filename);
            throw new SerializationException(message, e);
        }
    }

}
