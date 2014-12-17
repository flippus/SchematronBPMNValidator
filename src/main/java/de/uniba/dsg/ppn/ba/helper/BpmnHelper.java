/**
 *
 * BPMN Validation Project to validate special BPMN Constraints, see \README.md
 *
 * Copyright (C) 2014 Philipp Neugebauer
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */
package de.uniba.dsg.ppn.ba.helper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * helper class for all bpmn class-across methods
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class BpmnHelper {

    /**
     * removes all BPMNDiagram Nodes from the given file
     *
     * @param headFileDocument
     *            the document, where the BPMNDiagram Nodes should be deleted
     *            from
     */
    public static void removeBPMNDINode(Document headFileDocument) {
        Element definitionsNode = headFileDocument.getDocumentElement();
        NodeList bpmnDiagramNode = headFileDocument.getElementsByTagNameNS(
                ConstantHelper.BPMNDINAMESPACE, "BPMNDiagram");
        if (bpmnDiagramNode.getLength() > 0) {
            definitionsNode.removeChild(bpmnDiagramNode.item(0));
        }
    }

    /**
     * creates a xpath expression for finding the id
     *
     * @param id
     *            the id, to which the expression should refer
     * @return the xpath expression, which refers the given id
     */
    public static String createIdBpmnExpression(String id) {
        return String.format("//bpmn:*[@id = '%s']", id);
    }
}
