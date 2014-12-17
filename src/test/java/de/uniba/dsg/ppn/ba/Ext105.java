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

/**
 * Test class for testing Constraint EXT.105
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class Ext105 extends TestCase {

    @Test
    public void testConstraintFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_end_without_sub-events.bpmn"), 1);
        assertViolation(result.getViolations().get(0), "//bpmn:startEvent[0]",
                4);
    }

    @Test
    public void testConstraintSubFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_with_sub-startevent.bpmn"), 1);
        assertViolation(result.getViolations().get(0), "//bpmn:startEvent[1]",
                10);
    }

    @Test
    public void testConstraintSubSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_with_sub-events.bpmn"));
    }

    @Test
    public void testConstraintSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_without_sub-events.bpmn"));
    }

    @Override
    protected String getErrorMessage() {
        return "An end event must be present when a start event is used in the same process level";
    }

    @Override
    protected String getExtNumber() {
        return "105";
    }
}
