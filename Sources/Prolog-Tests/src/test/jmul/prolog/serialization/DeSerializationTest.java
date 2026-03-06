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


import java.io.File;

import java.util.List;

import jmul.prolog.LiteralRepository;
import jmul.prolog.LiteralRepositoryImpl;
import jmul.prolog.literals.Fact;
import jmul.prolog.literals.Literal;
import jmul.prolog.literals.Operations;
import jmul.prolog.literals.Rule;
import jmul.prolog.serialization.LiteralDeserializer;
import jmul.prolog.serialization.LiteralSerializer;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.SetUpException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;


/**
 * This test suite tests serializing and deserializing literals.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class DeSerializationTest {

    /**
     * A file name for deserialization and serialization tests.
     */
    private static final String FILENAME;

    /*
     * The static initializer.
     */
    static {

        FILENAME = "./literal-serialization-test.pl";
    }

    /**
     * Prepares everything to start a clean test.
     */
    @Before
    public void setUp() {

        File file = new File(FILENAME);
        file.delete();

        if (file.exists()) {

            String message = String.format("The file \"%s\" couldn't be deleted!", FILENAME);
            throw new SetUpException(message);
        }
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        // do nothing to be able to check the created file manually
    }

    /**
     * The actual serialization and deserialization test. A literal repository is serialized. The created file
     * is read and the content deserialized into a list of literals.
     */
    @Test
    public void testSerializationAndDeserialization() {

        LiteralRepository repository = newLiteralRepository();

        LiteralSerializer serializer = new LiteralSerializer();
        LiteralDeserializer deserializer = new LiteralDeserializer();

        serializer.writeTo(FILENAME, repository);
        List<Literal> loadedLiterals = deserializer.readFrom(FILENAME);

        assertNotNull("#null", loadedLiterals);
        assertNotEquals("#empty", 0, loadedLiterals.size());
        assertEquals("#size", repository.size(), loadedLiterals.size());
    }

    /**
     * Creates a literal repository with a couple of rules and facts.
     *
     * @return a literal repository
     */
    private LiteralRepository newLiteralRepository() {

        LiteralRepository repository = new LiteralRepositoryImpl();

        Literal literal;

        literal = new Fact("is_animal", "dog", true);
        repository.addLiteral(literal);

        literal = new Fact("is_animal", "cat", true);
        repository.addLiteral(literal);

        literal = new Fact("is_plant", "tulip", true);
        repository.addLiteral(literal);

        literal = new Rule("is_alive", Operations.DISJUNCTION, "is_animal", "is_plant");
        repository.addLiteral(literal);

        literal = new Rule("is_hungry", Operations.CONJUNCTION, "is_eating");
        repository.addLiteral(literal);

        literal = new Fact("is_eating", "tom", true);
        repository.addLiteral(literal);

        return repository;
    }

}
