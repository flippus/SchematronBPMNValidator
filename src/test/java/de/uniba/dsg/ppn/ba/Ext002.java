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

import org.junit.Test;

import de.uniba.dsg.bpmnspector.common.ValidationResult;
import de.uniba.dsg.bpmnspector.common.Violation;
import de.uniba.dsg.ppn.ba.helper.BpmnValidationException;

public class Ext002 extends TestCase {

    private final static String ERRORMESSAGE = "Files have id duplicates";
    private final static String XPATHSTRING = "//bpmn:*[@id = 'PROCESS_1'][0]";

    @Test
    public void testConstraintImport1Fail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_import.bpmn"), 8);
        assertViolation(result.getViolations().get(0), "fail_import.bpmn", 4);
        assertViolation(result.getViolations().get(1), "import.bpmn", 3);
    }

    @Test
    public void testConstraintImport2Fail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_import2.bpmn"), 8);
        assertViolation(result.getViolations().get(0), "import.bpmn", 3);
        assertViolation(result.getViolations().get(1), "import2.bpmn", 3);
    }

    @Test
    public void testConstraintImport3Fail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_import3.bpmn"), 16);
        assertViolation(result.getViolations().get(0), "fail_import3.bpmn", 4);
        assertViolation(result.getViolations().get(1), "fail_import2.bpmn", 5);
    }

    @Test
    public void testConstraintSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_import.bpmn"));
    }

    @Override
    protected void assertViolation(Violation v, String fileName, int line) {
        assertViolation(v, ERRORMESSAGE, fileName, XPATHSTRING, line);
    }

    @Override
    protected String getExtNumber() {
        return "002";
    }
}
