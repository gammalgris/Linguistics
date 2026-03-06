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

package jmul.prolog.evaluation;

import jmul.prolog.questions.YesNoQuestion;


/**
 * This interface describes an entity which evaluates a yes/ no question.<br>
 * <br>
 * <i>Note:<br>
 * Maybe this article will help implement a suitable algorithm, see
 * <a href="https://en.wikipedia.org/wiki/Backtracking">Backtracking</a>.</i>
 *
 * @author Kristian Kutin
 */
public interface YesNoQuestionEvaluator {

    /**
     * Evaluates the specified question. A question consists of a function symbol (i.e. for looking up a fact or rule)
     * and a constant symbol (i.e. to check facts).
     *
     * @param question
     *        a question
     *
     * @return <code>true</code> or <code>false</code> depending on the evaluation of the question
     */
    boolean evaluate(YesNoQuestion question);

}
