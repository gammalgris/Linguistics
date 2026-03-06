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

package jmul.prolog;


import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import jmul.prolog.literals.Literal;
import jmul.prolog.serialization.LiteralDeserializer;
import jmul.prolog.serialization.LiteralSerializer;


/**
 * An implementation of a literal repository.
 *
 * @author Kristian Kutin
 */
public class LiteralRepositoryImpl implements LiteralRepository {

    /**
     * A container that holds all literals. The container is initially empty.<br>
     * <br>
     * <i>Note:<br>
     * The container and embedded containers must sort the elements. When exporting the literals the order is important
     * if it should be necessary to test the literals independently (e.g. with a prolog interpreter).</i>
     */
    private SortedMap<String, SortedSet<Literal>> literalMap;

    /**
     * The default constructor.
     */
    public LiteralRepositoryImpl() {

        super();

        literalMap = new TreeMap<>();
    }

    /**
     * Returns the size of this repository (i.e. number of literals).
     *
     * @return the size of this repository (i.e. number of literals)
     */
    @Override
    public int size() {

        int size = 0;

        for (String functionSymbol : this) {

            Set<Literal> subset = lookUp(functionSymbol);

            if (subset == null) {

                continue;
            }

            size = size + subset.size();
        }

        return size;
    }

    /**
     * Clears this literal repository (i.e. deletes all literals).
     */
    @Override
    public void clear() {

        literalMap.clear();
    }

    /**
     * Exports all literals to the specified file. The literals are exported in a way to be parsable by a prolog
     * interpreter. Overrides existing files.
     *
     * @param filename
     *        a file path
     */
    @Override
    public void exportLiterals(String filename) {

        LiteralSerializer serializer = new LiteralSerializer();
        serializer.writeTo(filename, this);
    }

    /**
     * Imports literals from the specified file into this literal repository. Only a subset of the prolog language is
     * supported (i.e. rules and facts). Literals that are not currently supported will be ignored.<br>
     * <br>
     * <i>Note:<br>
     * The specified file is read and parsed first to avoid importing an inconsistent state of literals. Afterwards
     * the literals are added to this repository.</i>
     *
     * @param filename
     *        a file path
     */
    @Override
    public void importLiterals(String filename) {

        LiteralDeserializer deserializer = new LiteralDeserializer();
        List<Literal> newLiterals = deserializer.readFrom(filename);

        for (Literal literal : newLiterals) {

            addLiteral(literal);
        }
    }

    /**
     * Adds the specified literal to this literal repository.
     *
     * @param literal
     *        a literal (i.e. a rule or fact)
     */
    @Override
    public void addLiteral(Literal literal) {

        if (literal == null) {

            throw new IllegalArgumentException("No literal (null) was specified!");
        }

        String functionSymbol = literal.functionSymbol();

        SortedSet<Literal> subset = literalMap.get(functionSymbol);

        if (subset == null) {

            subset = new TreeSet<Literal>();
            literalMap.put(functionSymbol, subset);
        }

        subset.add(literal);
    }

    /**
     * Removes tthe specified literal from this literal repository.
     *
     * @param literal
     *        a literal (i.e. a rule or fact)
     */
    @Override
    public void removeLiteral(Literal literal) {

        String functionSymbol = literal.functionSymbol();

        SortedSet<Literal> subset = literalMap.get(functionSymbol);

        if (subset == null) {

            return;
        }

        subset.remove(literal);
    }

    /**
     * Looks up all literals which match the specified function symbol and returns a list of these literals.
     *
     * @param functionSymbol
     *        a function symbol
     *
     * @return list of matching literals
     */
    @Override
    public Set<Literal> lookUp(String functionSymbol) {

        SortedSet<Literal> result = literalMap.get(functionSymbol);

        if (result == null) {

            result = Collections.emptySortedSet();
        }

        return Collections.unmodifiableSet(result);
    }

    /**
     * Returns an iterator that iterates over all known function symbols.
     *
     * @return an iterator
     */
    @Override
    public Iterator<String> iterator() {

        return literalMap.keySet().iterator();
    }

}
