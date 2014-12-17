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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * This class is a setup helper class for the document builder and xpath
 * handling
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class SetupHelper {

    /**
     * sets up the documentbuilder with the required namespace-awareness
     */
    public static DocumentBuilder setupDocumentBuilder() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            // ignore
        }
        return documentBuilder;
    }

    /**
     * sets up the xpath with the custom namespace context of bpmn
     */
    public static XPath setupXPath() {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        xpath.setNamespaceContext(new BpmnNamespaceContext());
        return xpath;
    }

    /**
     * sets up the xpath expression to search for all ids in the bpmn namespace
     */
    public static XPathExpression setupXPathExpression() {
        XPath xpath = setupXPath();
        XPathExpression xPathExpression = null;
        try {
            xPathExpression = xpath.compile("//bpmn:*/@id");
        } catch (XPathExpressionException e) {
            // ignore
        }
        return xPathExpression;
    }
}
