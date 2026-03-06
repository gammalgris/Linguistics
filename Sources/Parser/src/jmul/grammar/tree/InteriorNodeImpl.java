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


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * An implementation of an interior node for grammar trees (or parse trees, see
 * <a href="https://en.wikipedia.org/wiki/Parse_tree">parse tree</a>).
 *
 * @author Kristian Kutin
 */
public class InteriorNodeImpl extends AbstractNode {

    /**
     * A list of all child nodes.
     */
    private final List<Node> children;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param type
     *        a node type
     * @param children
     *        all child nodes
     */
    public InteriorNodeImpl(NodeType type, Node... children) {

        super(type);

        if (children == null) {

            throw new IllegalArgumentException("No child nodes (null) were specified!");
        }

        this.children = Collections.unmodifiableList(Arrays.asList(children));
    }

    /**
     * Returns a list of all child nodes.
     *
     * @return a list of all child nodes
     */
    @Override
    public List<Node> children() {

        return children;
    }

    /**
     * Returns the child at the specified index.
     *
     * @param index
     *        an index
     *
     * @return a child node
     */
    @Override
    public Node child(int index) {

        return children.get(index);
    }

    /**
     * Returns the label of this node.
     *
     * @return the label of this ndoe
     */
    @Override
    public String label() {

        return "";
    }

    /**
     * Returns a string representation for this node.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("%s", type());
    }

}
