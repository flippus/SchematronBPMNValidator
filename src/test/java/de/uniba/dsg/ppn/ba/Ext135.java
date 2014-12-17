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
import de.uniba.dsg.ppn.ba.helper.BpmnValidationException;

public class Ext135 extends TestCase {

    private final static String ERRORMESSAGE = "A Gateway MUST have either multiple incoming Sequence Flows or multiple outgoing Sequence Flows";

    @Test
    public void testConstraintFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(createFile("fail.bpmn"),
                2);
        assertViolation(result.getViolations().get(0),
                "//bpmn:parallelGateway[0]", 10);
        assertViolation(result.getViolations().get(1),
                "//bpmn:parallelGateway[1]", 20);
    }

    @Test
    public void testConstraintSubFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_no_connection.bpmn"), 1);
        assertViolation(result.getViolations().get(0),
                "//bpmn:parallelGateway[0]", 4);
    }

    @Test
    public void testConstraintEXSubFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_ex_no_connection.bpmn"), 1);
        assertViolation(result.getViolations().get(0),
                "//bpmn:exclusiveGateway[0]", 4);
    }

    @Test
    public void testConstraintBothMultipleSuccess()
            throws BpmnValidationException {
        verifyValidResult(createFile("success_multiple_in_and_out.bpmn"));
    }

    @Test
    public void testConstraintOutMultipleSuccess()
            throws BpmnValidationException {
        verifyValidResult(createFile("success_multiple_out.bpmn"));
    }

    @Override
    protected String getErrorMessage() {
        return ERRORMESSAGE;
    }

    @Override
    protected String getExtNumber() {
        return "135";
    }
}
