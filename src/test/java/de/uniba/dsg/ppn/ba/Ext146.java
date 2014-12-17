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
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uniba.dsg.bpmnspector.common.ValidationResult;
import de.uniba.dsg.bpmnspector.common.Violation;
import de.uniba.dsg.ppn.ba.helper.BpmnValidationException;

/**
 * Test class for testing Constraint EXT.146
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class Ext146 extends TestCase {

    private final static String ERRORMESSAGE = "Only messageEventDefininitions, escalationEventDefinitions, errorEventDefinitions, cancelEventDefinitions, compensationEventDefinitions, signalEventDefinitions and terminateEventDefinitions are allowed for end events";
    private final static String XPATHSTRING = "//bpmn:endEvent[0]";

    @Test
    public void testConstraintLinkFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_link.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 7);
    }

    @Test
    public void testConstraintTimerFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_timer.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 7);
    }

    @Test
    public void testConstraintTimerRefFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_timer_ref.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 8);
    }

    @Test
    public void testConstraintMultipleFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_multiple.bpmn"), 1);
        assertViolation(result.getViolations().get(0), 7);
    }

    @Test
    public void testConstraintConditionalFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_conditional.bpmn"), 2);
        Violation v = result.getViolations().get(0);
        assertTrue(v.getMessage().contains("cvc-complex-type.2.4.b"));
        assertTrue(v.getMessage().contains("conditionalEventDefinition"));
        assertEquals("XSD-Check", v.getConstraint());
        assertEquals(9, v.getLine());
        assertViolation(result.getViolations().get(1), 7);
    }

    @Test
    public void testConstraintSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success.bpmn"));
    }

    @Test
    public void testConstraintMultipleSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success_multiple.bpmn"));
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
        return "146";
    }

}
