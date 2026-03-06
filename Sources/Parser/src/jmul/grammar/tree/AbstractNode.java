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


import java.util.Iterator;


/**
 * A base implementation for nodes (see
 * <a href="https://en.wikipedia.org/wiki/Parse_tree">parse tree</a>).
 *
 * @author Kristian Kutin
 */
abstract class AbstractNode implements Node {

    /**
     * The node type.
     */
    private final NodeType type;

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param type
     *        a node type
     */
    public AbstractNode(NodeType type) {

        super();

        if (type == null) {

            throw new IllegalArgumentException("No node type (null) wasspecified!");
        }

        this.type = type;
    }

    /**
     * Returns the node type of this node.
     *
     * @return a node type
     */
    @Override
    public NodeType type() {

        return type;
    }

    /**
     * Returns the number of children.
     *
     * @return the number of children.
     */
    @Override
    public int childCount() {

        return children().size();
    }

    /**
     * Returns an iterator that iterates over all children.
     *
     * @return a iterator
     */
    @Override
    public Iterator<Node> iterator() {

        return children().iterator();
    }

}
