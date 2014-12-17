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
 * Test class for testing Constraint EXT.025
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class Ext025 extends TestCase {

    private static final String XPATHSTRING = "//bpmn:sequenceFlow[bpmn:conditionExpression] [not(@sourceRef = //bpmn:exclusiveGateway/@id)] [not(@sourceRef = //bpmn:parallelGateway/@id)] [not(@sourceRef = //bpmn:inclusiveGateway/@id)] [not(@sourceRef = //bpmn:complexGateway/@id)] [not(@sourceRef = //bpmn:eventBasedGateway/@id)][0]";

    @Test
    public void testConstraintNoIncomingFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(createFile("fail.bpmn"),
                1);
        assertViolation(result.getViolations().get(0));
    }

    @Test
    public void testConstraintNoIncomingFail2() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_2.bpmn"), 1);
        assertViolation(result.getViolations().get(0));
    }

    @Test
    public void testConstraintSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success.bpmn"));
    }

    @Test
    public void testConstraintSuccess2() throws BpmnValidationException {
        verifyValidResult(createFile("success_2.bpmn"));
    }

    @Test
    public void testConstraintSuccessNoCondition()
            throws BpmnValidationException {
        verifyValidResult(createFile("success_no_condition.bpmn"));
    }

    private void assertViolation(Violation v) {
        assertViolation(v, XPATHSTRING, 19);
    }

    @Override
    protected String getErrorMessage() {
        return "An Activity must not have only one outgoing conditional sequence flow if conditionExpression is present";
    }

    @Override
    protected String getExtNumber() {
        return "025";
    }
}
