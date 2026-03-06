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

package test.jmul.prolog.serialization;


import jmul.prolog.LiteralRepository;
import jmul.prolog.LiteralRepositoryImpl;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests deserializing literals.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class DeSerialization3Test {

    /**
     * A file path to a set of test data.
     */
    private static final String TESTDATA_PATH;

    /*
     * The static initializer.
     */
    static {

        TESTDATA_PATH = "./testdata/test-repository.pl";
    }

    /**
     * Tests deserializing literals from a manually formatted file.
     */
    @Test
    public void testDeserialization() {

        LiteralRepository literalRepository = new LiteralRepositoryImpl();

        literalRepository.importLiterals(TESTDATA_PATH);

        assertEquals("#size", 11, literalRepository.size());
    }

}
