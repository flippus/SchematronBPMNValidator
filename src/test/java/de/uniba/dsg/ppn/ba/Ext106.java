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

/**
 * Test class for testing Constraint EXT.106
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class Ext106 extends TestCase {

    @Test
    public void testConstraintEventFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_cancel_end_event.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 9);
    }

    @Test
    public void testConstraintEventRefFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_sub_process.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 24);
    }

    @Test
    public void testConstraintCancelBoundaryEventFail()
            throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_cancel_boundary_event.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 18);
    }

    @Test
    public void testConstraintCancelEventSuccess()
            throws BpmnValidationException {
        verifyValidResult(createFile("success_cancel_event.bpmn"));

    }

    @Test
    public void testConstraintCancelBoundaryEventSuccess()
            throws BpmnValidationException {
        verifyValidResult(createFile("success_cancel_boundary_event.bpmn"));
    }

    private void assertViolation(Violation v, int line) {
        assertViolation(v, "//bpmn:cancelEventDefinition[0]", line);
    }

    @Override
    protected String getErrorMessage() {
        return "A cancel EndEvent is only allowed in a transaction sub-process";
    }

    @Override
    protected String getExtNumber() {
        return "106";
    }

}
