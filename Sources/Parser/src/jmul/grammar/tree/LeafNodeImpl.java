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


import java.util.Collections;
import java.util.List;


/**
 * An implementation of a leaf node for grammar trees (or parse trees, see
 * <a href="https://en.wikipedia.org/wiki/Parse_tree">parse tree</a>).
 *
 * @author Kristian Kutin
 */
public class LeafNodeImpl extends AbstractNode {

    /**
     * A node label.
     */
    private final String label;

    /**
     * Creates a new instance accordig to the specified parameters.
     *
     * @param type
     *        a node type
     * @param label
     *        a node label
     */
    public LeafNodeImpl(NodeType type, String label) {

        super(type);

        if (label == null) {

            throw new IllegalArgumentException("No label (null) was specified!");
        }

        this.label = label;
    }

    /**
     * Returns a list of all child nodes. A leaf node has no children.
     *
     * @return a list of all child nodes
     */
    @Override
    public List<Node> children() {

        return Collections.emptyList();
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

        throw new UnsupportedOperationException("A leaf node has no child nodes!");
    }

    /**
     * Returns the label of this node.
     *
     * @return the label of this ndoe
     */
    @Override
    public String label() {

        return label;
    }

    /**
     * Returns a string representation for this node.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("%s (%s)", type(), label());
    }

}
