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

package test.jmul.prolog;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jmul.prolog.LiteralRepository;
import jmul.prolog.LiteralRepositoryImpl;
import jmul.prolog.literals.Fact;
import jmul.prolog.literals.Literal;
import jmul.prolog.literals.Operation;
import jmul.prolog.literals.Operations;
import jmul.prolog.literals.Rule;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests repositories for literals.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class LiteralRepositoryTest {

    /**
     * Tests creating a new repository.
     */
    @Test
    public void testCreateRepository() {

        LiteralRepository repository = new LiteralRepositoryImpl();

        assertEquals("#size", 0, repository.size());
    }

    /**
     * Tests adding a literal (i.e. fact).
     */
    @Test
    public void testAddLiteral() {

        LiteralRepository repository = new LiteralRepositoryImpl();

        Literal literal = createFact();
        repository.addLiteral(literal);

        assertEquals("#size", 1, repository.size());
    }

    /**
     * Creates a fact.
     *
     * @return a fact
     */
    private Literal createFact() {

        String functionSymbol = "is_animal";
        String constantSymbol = "dog";
        boolean result = true;

        Fact fact = new Fact(functionSymbol, constantSymbol, result);

        return fact;
    }

    /**
     * Creates a fact.
     *
     * @return a fact
     */
    private Literal createFact2() {

        String functionSymbol = "is_animal";
        String constantSymbol = "cat";
        boolean result = true;

        Fact fact = new Fact(functionSymbol, constantSymbol, result);

        return fact;
    }

    /**
     * Creates a rule.
     *
     * @return a rule
     */
    private Literal createRule() {

        String functionSymbol = "is_animal";
        Operation operation = Operations.DISJUNCTION;
        String referenceSymbol1 = "is_dog";
        String referenceSymbol2 = "is_cat";

        List<String> referenceSymbols = new ArrayList<>();
        referenceSymbols.add(referenceSymbol1);
        referenceSymbols.add(referenceSymbol2);

        Rule rule = new Rule(functionSymbol, operation, referenceSymbol1, referenceSymbol2);

        return rule;
    }

    /**
     * Tests adding a literal (i.e. rule).
     */
    @Test
    public void testAddLiteral_Variant2() {

        LiteralRepository repository = new LiteralRepositoryImpl();

        Literal literal = createRule();
        repository.addLiteral(literal);

        assertEquals("#size", 1, repository.size());
    }

    /**
     * Tests adding a literal (i.e. <code>null</code>).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddLiteral_Variant3() {

        LiteralRepository repository = new LiteralRepositoryImpl();

        Literal literal = null;
        repository.addLiteral(literal);
    }

    /**
     * Tests adding two identical literals (i.e. rules).
     */
    @Test
    public void testAddLiteral_Variant4() {

        LiteralRepository repository = new LiteralRepositoryImpl();

        Literal literal;

        literal = createRule();
        repository.addLiteral(literal);

        literal = createRule();
        repository.addLiteral(literal);

        assertEquals("#size", 1, repository.size());
    }

    /**
     * Tests adding two different literals (i.e. rule and fact).
     */
    @Test
    public void testAddLiteral_Variant5() {

        LiteralRepository repository = new LiteralRepositoryImpl();

        Literal literal;

        literal = createFact();
        repository.addLiteral(literal);

        literal = createRule();
        repository.addLiteral(literal);

        assertEquals("#size", 2, repository.size());
    }

    /**
     * Tests adding two different literals (i.e. facts).
     */
    @Test
    public void testAddLiteral_Variant6() {

        LiteralRepository repository = new LiteralRepositoryImpl();

        Literal literal;

        literal = createFact();
        repository.addLiteral(literal);

        literal = createFact2();
        repository.addLiteral(literal);

        assertEquals("#size", 2, repository.size());
    }

    /**
     * Tests performing a lookup.
     */
    @Test
    public void testLookup() {

        LiteralRepository repository = new LiteralRepositoryImpl();

        Literal literal1 = createFact();
        Literal literal2 = createRule();

        repository.addLiteral(literal1);
        repository.addLiteral(literal2);

        assertEquals("#size", 2, repository.size());

        String functionSymbol = literal1.functionSymbol();
        Set<Literal> foundLiterals = repository.lookUp(functionSymbol);

        assertEquals("#matches", 2, foundLiterals.size());
    }

    /**
     * Tests performing a lookup.
     */
    @Test
    public void testLookup_Variant2() {

        LiteralRepository repository = new LiteralRepositoryImpl();

        Literal literal1 = createFact();
        Literal literal2 = createRule();

        repository.addLiteral(literal1);
        repository.addLiteral(literal2);

        assertEquals("#size", 2, repository.size());

        String functionSymbol = "dunno";
        Set<Literal> foundLiterals = repository.lookUp(functionSymbol);

        assertEquals("#matches", 0, foundLiterals.size());
    }

}
