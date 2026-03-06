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

package jmul.grammar.tree;


import java.util.List;


/**
 * This interface defines a node in a syntax tree (or parse tree, see
 * see <a href="https://en.wikipedia.org/wiki/Parse_tree">parse tree</a>).
 *
 * @author Kristian Kutin
 */
public interface Node extends Iterable<Node> {

    /**
     * Returns a list of all child nodes.
     *
     * @return a list of all child nodes
     */
    List<Node> children();

    /**
     * Returns the number of children.
     *
     * @return the number of children.
     */
    int childCount();

    /**
     * Returns the child at the specified index.
     *
     * @param index
     *        an index
     *
     * @return a child node
     */
    Node child(int index);

    /**
     * Returns the node type of this node.
     *
     * @return a node type
     */
    NodeType type();

    /**
     * Returns the label of this node.
     *
     * @return the label of this ndoe
     */
    String label();

}
