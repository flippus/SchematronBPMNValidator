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

import java.io.File;
import java.util.List;

import ch.qos.logback.classic.Level;
import de.uniba.dsg.bpmnspector.common.ValidationResult;
import de.uniba.dsg.ppn.ba.helper.BpmnValidationException;

/**
 * Interface for the implementation of the validator. Allows the usage of the
 * validator in other projects. The loglevel is set default to info. If you need
 * another log level, change the log level before the validation process.
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public interface BpmnValidator {

    /**
     * returns the set loglevel of all loggers
     *
     * @return the set log level {@link ch.qos.logback.classic.Level}
     */
    Level getLogLevel();

    /**
     * Sets the loglevel of all loggers of the bpmn validator to the given level
     *
     * @param logLevel
     *            possible levels: {@link ch.qos.logback.classic.Level}
     */
    void setLogLevel(Level logLevel);

    /**
     * checks the given xmlFile for bpmn constraint violations
     *
     * @param xmlFile
     *            the xml file to validate
     * @return ValidationResult including all checked files and found violations
     * @throws BpmnValidationException
     *             if something fails during validation process
     */
    ValidationResult validate(File xmlFile) throws BpmnValidationException;

    /**
     * checks the given xmlFiles for bpmn constraint violations
     *
     * @param xmlFiles
     *            the list of xml files to validate
     * @return list of {@link de.uniba.dsg.bpmnspector.common.ValidationResult}
     *         including all checked files and found violations for each file
     * @throws BpmnValidationException
     *             if something fails during validation process
     */
    List<ValidationResult> validateFiles(List<File> xmlFiles)
            throws BpmnValidationException;

}
