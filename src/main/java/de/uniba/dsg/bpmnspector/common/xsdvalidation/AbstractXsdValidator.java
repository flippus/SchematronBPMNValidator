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
package de.uniba.dsg.bpmnspector.common.xsdvalidation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

import de.uniba.dsg.bpmnspector.common.ValidationResult;
import de.uniba.dsg.ppn.ba.helper.BpmnValidationException;

/**
 * Super class for all validators to avoid code redundance
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public abstract class AbstractXsdValidator {

    /**
     * Validates the given xmlFile with the xsd files and writes violations to
     * the given validation result
     *
     * @param xmlFile
     *            the xml file which should be validated
     * @param validationResult
     *            the result object of the validation
     * @throws IOException
     *             when xmlFile can't be read
     * @throws SAXException
     *             when validation process fails somehow
     */
    public abstract void validateAgainstXsd(File xmlFile,
            ValidationResult validationResult) throws IOException,
            SAXException, BpmnValidationException;

    /**
     *
     * The method simplifies the search for a resource and returns the
     * streamsource with the searched source
     *
     * @param resourceName
     *            the name of the resource file
     * @return the streamsource of the file to be load
     * @throws FileNotFoundException
     *             if the resource file doesn't exist there
     */
    protected StreamSource resolveResourcePaths(String resourceName)
            throws FileNotFoundException {
        return new StreamSource(this.getClass().getResourceAsStream(
                "/" + resourceName));
    }
}
