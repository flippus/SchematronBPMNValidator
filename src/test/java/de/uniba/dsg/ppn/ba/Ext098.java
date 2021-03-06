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
 * Test class for testing Constraint EXT.098
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
public class Ext098 extends TestCase {

    private final static String ERRORMESSAGE = "Only messageEventDefininitions, timerEventDefinitions, conditionalEventDefinitions and signalEventDefinitions are allowed for top-level process start events";
    private final static String XPATHSTRING = "//bpmn:startEvent[parent::bpmn:process][0]";

    @Test
    public void testConstraintCancelFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_cancel.bpmn"), 2);
        assertViolation(result.getViolations().get(0), 4);
        assertViolation(
                result.getViolations().get(1),
                "A cancel EndEvent is only allowed in a transaction sub-process",
                "//bpmn:cancelEventDefinition[0]", 6);
    }

    @Test
    public void testConstraintCompensateFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_compensate.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 4);
    }

    @Test
    public void testConstraintErrorFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_error.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 4);
    }

    @Test
    public void testConstraintEscalationFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_escalation.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 4);
    }

    @Test
    public void testConstraintEscalationRefFail()
            throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_escalation_ref.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 5);
    }

    @Test
    public void testConstraintLinkFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_link.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 4);
    }

    @Test
    public void testConstraintMultipleFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_multiple.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 4);
    }

    @Test
    public void testConstraintTerminateFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_terminate.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 4);
    }

    @Test
    public void testConstraintConditionalSuccess()
            throws BpmnValidationException {
        verifyValidResult(createFile("success_conditional.bpmn"));
    }

    @Test
    public void testConstraintMessageSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_message.bpmn"));
    }

    @Test
    public void testConstraintMultipleSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_multiple.bpmn"));
    }

    @Test
    public void testConstraintNoneSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_none.bpmn"));
    }

    @Test
    public void testConstraintSignalSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_signal.bpmn"));
    }

    @Test
    public void testConstraintTimerSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_timer.bpmn"));
    }

    private void assertViolation(Violation v, int line) {
        assertViolation(v, XPATHSTRING, line);
    }

    @Override
    protected String getErrorMessage() {
        return ERRORMESSAGE;
    }

    @Override
    protected String getExtNumber() {
        return "098";
    }
}
