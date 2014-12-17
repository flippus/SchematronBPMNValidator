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
package de.uniba.dsg.ppn.ba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Paths;

import ch.qos.logback.classic.Level;
import de.uniba.dsg.bpmnspector.common.ValidationResult;
import de.uniba.dsg.bpmnspector.common.Violation;
import de.uniba.dsg.ppn.ba.helper.BpmnValidationException;
import de.uniba.dsg.ppn.ba.validation.SchematronBPMNValidator;

/**
 * Abstract test class for all tests of the BPMN Validator to simplify testing
 * and reduce redundancy
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class TestCase {

    protected final SchematronBPMNValidator validator;

    {
        validator = new SchematronBPMNValidator();
        validator.setLogLevel(Level.OFF);
    }

    protected static String getTestFilePath() {
        return Paths.get(System.getProperty("user.dir"))
                .resolve("src/test/resources").toString();
    }

    protected File createFile(String fileName) {
        String path = String.format("%s%s%s%s%s", getTestFilePath(),
                File.separator, getExtNumber(), File.separator, fileName);
        return new File(path);
    }

    protected ValidationResult validate(File f) throws BpmnValidationException {
        return validator.validate(f);
    }

    protected void verifyValidResult(File f) throws BpmnValidationException {
        ValidationResult result = validate(f);
        assertTrue(result.isValid());
        assertTrue(result.getViolations().isEmpty());
    }

    protected ValidationResult verifyInValidResult(File f, int violationsCount)
            throws BpmnValidationException {
        ValidationResult result = validate(f);
        assertFalse(result.isValid());
        assertEquals(violationsCount, result.getViolations().size());
        return result;
    }

    protected void assertViolation(Violation v, String message,
            String fileName, String xpath, int line) {
        assertViolation(v, message, xpath, line);
        assertEquals(fileName, v.getFileName());
    }

    protected void assertViolation(Violation v, String message, String xpath,
            int line) {
        assertEquals(message, v.getMessage());
        assertEquals(xpath, v.getxPath());
        assertEquals(line, v.getLine());
    }

    protected void assertViolation(Violation v, String xpath, int line) {
        assertViolation(v, getErrorMessage(), xpath, line);
    }

    protected String getErrorMessage() {
        throw new UnsupportedOperationException(
                "must be overriden by every child class!");
    }

    protected String getExtNumber() {
        throw new UnsupportedOperationException(
                "must be overriden by every child class!");
    }
}
