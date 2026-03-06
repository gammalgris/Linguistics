/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2025  Kristian Kutin
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

package jmul.linguistics.tokens;


import jmul.math.numbers.Number;


/**
 * This interface describes a generic token. Implementations should be immutable (or at least the public API should
 * prevent modifications).
 *
 * @author Kristian Kutin
 */
public interface Token extends CharSequence {

    /**
     * Checks if this token a property with the specified name.
     *
     * @param name
     *        the name of a property
     *
     * @return <code>true</code> if the specified property ecists, else <code>false</code>
     */
    boolean hasProperty(CharSequence name);

    /**
     * Returns the value which is associated with the specified property.
     *
     * @param name
     *        the name of a property
     *
     * @return the associated value
     */
    TokenProperty property(CharSequence name);

    /**
     * Returns a string representation for this token.
     *
     * @return a string representation
     */
    String toString();

    /**
     * Returns a token with the added property.
     *
     * @param newProperty
     *        a new property
     *
     * @return an updated token
     */
    Token addProperty(TokenProperty newProperty);

    /**
     * Returns a token with the removed property.
     *
     * @param name
     *        the name of the property which is to be removed
     *
     * @return an updated token
     */
    Token removeProperty(CharSequence name);

}


/**
 * This interface descibes a token property. Implementations should be immutable (or at least the public API should
 * prevent modifications).
 *
 * @author Kristian Kutin
 */
interface TokenProperty {

    /**
     * Returns the name of this token property.
     *
     * @return a name
     */
    String name();

    /**
     * Returns the value which is associated with this property.
     *
     * @return a value
     */
    Object value();

    /**
     * Tests if the value associated with this property is a char sequence.
     *
     * @return <code>true</code> if the vlaue is a char sequence, else <code>false</code>
     */
    boolean isCharSequence();

    /**
     * Casts the value associated with this property to a char sequence.
     *
     * @return a char sequence
     */
    CharSequence toCharSequence();

    /**
     * Tests if the value associated with this property consists of multiple char sequences (i.e. which can be iterated).
     *
     * @return <code>true</code> if the value associated with this property consists of multiple char sequences, else <code>false</code>
     */
    boolean isMultipleCharSequences();

    /**
     * Casts the value associated with this property to a sequence of iterable char sequences.
     *
     * @return a sequence of iterable char sequences
     */
    Iterable<CharSequence> toMultipleCharSequences();

    /**
     * Tests if the value associated with this property is a number.
     *
     * @return <code>true</code> if the value associated with this property is a number, else <code>false</code>
     */
    boolean isNumber();

    /**
     * Casts the value associated with this proeprty to a number.
     *
     * @return a number
     */
    Number toNumber();

}
