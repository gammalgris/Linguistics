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

package test.jmul.prolog.parsing;


import jmul.prolog.literals.Literal;
import jmul.prolog.parsing.FactParser;
import jmul.prolog.parsing.Parser;
import jmul.prolog.parsing.ParsingException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


/**
 * This test suite tests pasing a fact from a string.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class FactParserTest {

    /**
     * Parses a fact from a line.
     */
    @Test
    public void parseFact() {

        String line = "is_animal(dog) :- true.";

        Parser parser = new FactParser();

        Literal literal = parser.parse(line);

        assertNotNull("#null", literal);
        assertEquals("#toString", line, literal.toString());
    }

    /**
     * Parses a <code>null</code> parameter and throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void parseNull() {

        String line = null;

        Parser parser = new FactParser();

        parser.parse(line);
    }

    /**
     * Parses a line that doesn't contain a fact literal and throws an exception.
     */
    @Test(expected = ParsingException.class)
    public void parseNoLiteral() {

        String line = "hello wordl!";

        Parser parser = new FactParser();

        parser.parse(line);
    }

}
