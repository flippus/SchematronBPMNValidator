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
package de.uniba.dsg.ppn.ba.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.uniba.dsg.bpmnspector.common.ValidationResult;

/**
 * This class is used for writing xml files via jaxb
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class XmlWriter {

    private Marshaller marshaller;
    private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(XmlWriter.class.getSimpleName());
    }

    {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(ValidationResult.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            LOGGER.debug("jaxb writer initialization failed: {}", e);
        }
    }

    /**
     * writes the result to the given file
     *
     * @param result
     *            the validation result, which should be writen to a file
     * @param file
     *            the file, where the validation result should be written to
     * @throws JAXBException
     *             if an error occurs during xml writing process
     */
    public void writeResult(ValidationResult result, File file)
            throws JAXBException {
        marshaller.marshal(result, file);
        LOGGER.info("XML Result written: {}", file.getName());
    }
}