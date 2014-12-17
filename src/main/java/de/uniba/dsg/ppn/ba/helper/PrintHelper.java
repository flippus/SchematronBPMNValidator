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

import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.w3c.dom.Document;

import de.uniba.dsg.bpmnspector.common.Violation;

/**
 * PrintHelper for printing some objects to system.out
 *
 * @author Philipp Neugebauer
 * @version 1.0
 */
@SuppressWarnings("PMD.SystemPrintln")
public class PrintHelper {

    private static Transformer transformer;

    static {
        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        try {
            transformer = transformerFactory.newTransformer();
            transformer
            .setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
        } catch (TransformerConfigurationException e) {
            // ignore
        }
    }

    /**
     * prints the given document to system.out
     *
     * @param document
     *            the document, which should be printed
     */
    public static void printDocument(Document document) {
        try {
            transformer.transform(new DOMSource(document), new StreamResult(
                    System.out));
        } catch (TransformerException e) {
            System.err.println("printDocument failed cause of " + e);
        }
    }

    /**
     * prints the violations list in a nice and human-readable way to system.out
     *
     * @param violations
     *            the violations list, which should be printed
     */
    public static void printViolations(List<Violation> violations) {
        System.out.println("Violations count: " + violations.size());
        System.out.println("--------------------");
        for (Violation v : violations) {
            System.out.println("Line: " + v.getLine());
            System.out.println("FileName: " + v.getFileName());
            System.out.println("Message: " + v.getMessage());
            System.out.println("XPath: " + v.getxPath());
            System.out.println("Constraint: " + v.getConstraint());
            System.out.println("--------------------");
        }
    }

    /**
     * prints the error and debug logs for file not found exceptions
     *
     * @param logger
     *            the logger who should log the message
     * @param exception
     *            the exception which should be logged
     * @param fileName
     *            the name of the file which couldn't be found
     */
    public static void printFileNotFoundLogs(Logger logger,
            Exception exception, String fileName) {
        logger.error(ConstantHelper.FILENOTFOUNDMESSAGE, fileName);
        logger.debug(ConstantHelper.FILENOTFOUNDMESSAGEWITHCAUSE, fileName,
                exception);
    }

}
