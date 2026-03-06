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

package test.jmul.prolog.literals;


import jmul.prolog.literals.Fact;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests creating facts.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CreateFactTest {

    /**
     * Tests creating a fact.
     */
    @Test
    public void createFact() {

        String functionSymbol = "is_animal";
        String constantSymbol = "dog";
        boolean result = true;

        Fact fact = new Fact(functionSymbol, constantSymbol, result);

        assertEquals("#function symbol", functionSymbol, fact.functionSymbol);
        assertEquals("#function symbol", functionSymbol, fact.functionSymbol());
        assertEquals("#constant symbol", constantSymbol, fact.constantSymbol);
        assertEquals("#result", result, fact.functionResult);
        assertEquals("#string", "is_animal(dog) :- true.", fact.toString());
    }

    /**
     * Tests creating a fact.
     */
    @Test
    public void createFact_Variant2() {

        String functionSymbol = "is_animal";
        String constantSymbol = "dog";
        boolean result = false;

        Fact fact = new Fact(functionSymbol, constantSymbol, result);

        assertEquals("#function symbol", functionSymbol, fact.functionSymbol);
        assertEquals("#function symbol", functionSymbol, fact.functionSymbol());
        assertEquals("#constant symbol", constantSymbol, fact.constantSymbol);
        assertEquals("#result", result, fact.functionResult);
        assertEquals("#string", "is_animal(dog) :- false.", fact.toString());
    }

    /**
     * Tests creating a fact.
     */
    @Test
    public void createFact_Variant3() {

        String functionSymbol = "is_animal";
        String constantSymbol = "dog";
        boolean result = true;

        Fact fact = new Fact(functionSymbol, constantSymbol);

        assertEquals("#function symbol", functionSymbol, fact.functionSymbol);
        assertEquals("#function symbol", functionSymbol, fact.functionSymbol());
        assertEquals("#constant symbol", constantSymbol, fact.constantSymbol);
        assertEquals("#result", result, fact.functionResult);
        assertEquals("#string", "is_animal(dog) :- true.", fact.toString());
    }

    /**
     * Tests creating a fact with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createFact_withNullFunctionSymbol() {

        String functionSymbol = null;
        String constantSymbol = "dog";
        boolean result = true;

        Fact fact = new Fact(functionSymbol, constantSymbol, result);

        assertEquals("#function symbol", functionSymbol, fact.functionSymbol);
        assertEquals("#function symbol", functionSymbol, fact.functionSymbol());
        assertEquals("#constant symbol", constantSymbol, fact.constantSymbol);
        assertEquals("#result", result, fact.functionResult);
        assertEquals("#string", "is_animal(dog) :- true.", fact.toString());
    }

    /**
     * Tests creating a fact with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createFact_withEmptyFunctionSymbol() {

        String functionSymbol = " ";
        String constantSymbol = "dog";
        boolean result = true;

        Fact fact = new Fact(functionSymbol, constantSymbol, result);

        assertEquals("#function symbol", functionSymbol, fact.functionSymbol);
        assertEquals("#function symbol", functionSymbol, fact.functionSymbol());
        assertEquals("#constant symbol", constantSymbol, fact.constantSymbol);
        assertEquals("#result", result, fact.functionResult);
        assertEquals("#string", "is_animal(dog) :- true.", fact.toString());
    }

    /**
     * Tests creating a fact with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createFactWithNullConstantSymbol() {

        String functionSymbol = "is_animal";
        String constantSymbol = null;
        boolean result = true;

        Fact fact = new Fact(functionSymbol, constantSymbol, result);

        assertEquals("#function symbol", functionSymbol, fact.functionSymbol);
        assertEquals("#function symbol", functionSymbol, fact.functionSymbol());
        assertEquals("#constant symbol", constantSymbol, fact.constantSymbol);
        assertEquals("#result", result, fact.functionResult);
        assertEquals("#string", "is_animal(dog) :- true.", fact.toString());
    }

    /**
     * Tests creating a fact with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createFactWithEmptyConstantSymbol() {

        String functionSymbol = "is_animal";
        String constantSymbol = " ";
        boolean result = true;

        Fact fact = new Fact(functionSymbol, constantSymbol, result);

        assertEquals("#function symbol", functionSymbol, fact.functionSymbol);
        assertEquals("#function symbol", functionSymbol, fact.functionSymbol());
        assertEquals("#constant symbol", constantSymbol, fact.constantSymbol);
        assertEquals("#result", result, fact.functionResult);
        assertEquals("#string", "is_animal(dog) :- true.", fact.toString());
    }

}
