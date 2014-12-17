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
 * Test class for testing Constraint EXT.022
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class Ext022 extends TestCase {

    @Test
    public void testConstraintEventSubProcessFail()
            throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_event_sub_process.bpmn"), 1);
        assertViolation(
                result.getViolations().get(0),
                "For a Process: Of the types of FlowNode, only Activities, Gateways, and Events can be the target. However, Activities that are Event SubProcesses are not allowed to be a target",
                "//bpmn:*[./@id = //bpmn:sequenceFlow/@targetRef][0]", 7);
    }

    @Test
    public void testConstraintEventsSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_events.bpmn"));
    }

    @Test
    public void testConstraintGatewaysSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_gateways.bpmn"));
    }

    @Test
    public void testConstraintTasksSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_tasks.bpmn"));
    }

    @Test
    public void testConstraintTasksSuccessEventProcessInSubProcess()
            throws BpmnValidationException {
        verifyValidResult(createFile("success_event_in_normal_subprocess.bpmn"));
    }

    @Override
    protected String getExtNumber() {
        return "022";
    }
}
