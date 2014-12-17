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
 * Test class for testing Constraint EXT.026
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class Ext026 extends TestCase {

    @Test
    public void testConstraintActivityFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_activity.bpmn"), 1);
        assertViolation(result.getViolations().get(0),
                "//bpmn:task[@default][0]");
    }

    @Test
    public void testConstraintGatewayFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_gateway.bpmn"), 1);
        assertViolation(result.getViolations().get(0),
                "//bpmn:exclusiveGateway[@default][0]");
    }

    @Test
    public void testConstraintSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success.bpmn"));
    }

    private void assertViolation(Violation v, String xpath) {
        assertViolation(v, xpath, 11);
    }

    @Override
    protected String getErrorMessage() {
        return "If an activity or gateway references a sequenceFlow as default flow - the referenced sequence flow must reference the activity/the gateway as sourceRef";
    }

    @Override
    protected String getExtNumber() {
        return "026";
    }
}
