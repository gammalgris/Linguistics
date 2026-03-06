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

package jmul.prolog.questions;


/**
 * An implementation of a yes-no question. Evaluating a yes-no question should return either yes or no
 * (i.e. <code>true</code> or <code>false</code>).
 *
 * @author Kristian Kutin
 */
public class YesNoQuestion extends AbstractQuestion {

    /**
     * A constant symbol.
     */
    public final String constantSymbol;

    /**
     * Creates a new question according to the specified parameters.
     *
     * @param functionSymbol
     *        a function symbol
     * @param constantSymbol
     *        a constant symbol
     */
    public YesNoQuestion(String functionSymbol, String constantSymbol) {

        super(functionSymbol);

        if (constantSymbol == null) {

            throw new IllegalArgumentException("No constant symbol (null) was specified!");
        }

        if (constantSymbol.trim().isEmpty()) {

            throw new IllegalArgumentException("No constant symbol (empty string) was specified!");
        }

        this.constantSymbol = constantSymbol;
    }

    /**
     * Returns a prolog notation of this question.
     *
     * @return a prolog notation
     */
    @Override
    public String toString() {

        return String.format("%s(%s).", functionSymbol, constantSymbol);
    }

}
