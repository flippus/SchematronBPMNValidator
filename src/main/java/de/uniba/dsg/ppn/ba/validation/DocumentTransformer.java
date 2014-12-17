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
package de.uniba.dsg.ppn.ba.validation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * This class handles the transformation from documents to input streams which
 * is required to apply the schematron validation
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class DocumentTransformer {

    /**
     * transforms the given headFileDocument to an inputstream, so that it can
     * be used for the schematron validation process
     *
     * @param headFileDocument
     * @return input stream with the head file document
     * @throws UnsupportedEncodingException
     *             if the encoding isn't supported
     * @throws TransformerException
     *             if anything fails during transformation process
     */
    public static ByteArrayInputStream transformToInputStream(
            Document headFileDocument) throws UnsupportedEncodingException,
            TransformerException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer
        .transform(new DOMSource(headFileDocument), new StreamResult(
                new OutputStreamWriter(outputStream, "UTF-8")));

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
