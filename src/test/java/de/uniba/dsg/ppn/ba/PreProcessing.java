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

public class PreProcessing extends TestCase {

    private final static String ERRORMESSAGE = "Referenced process must have at least one None Start Event";
    private final static String XPATHSTRING = "//bpmn:*[@id = 'PROCESS_1'][0]";

    @Test
    public void testConstraintImportedProcessFail()
            throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_call_ref_process.bpmn"), 1);
        assertViolation(result.getViolations().get(0), "ref_process.bpmn", 3);
    }

    @Test
    public void testConstraintImportedProcessFail1()
            throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_call_ref_process_call.bpmn"), 2);
        assertViolation(result.getViolations().get(0),
                "fail_call_ref_process.bpmn", 4);
        assertViolation(result.getViolations().get(1), "ref_process.bpmn", 3);
    }

    @Test
    public void testConstraintImportedProcessFail2()
            throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_call_ref_process_call_call.bpmn"), 2);
        assertViolation(result.getViolations().get(0),
                "fail_call_ref_process.bpmn", 4);
        assertViolation(result.getViolations().get(1), "ref_process.bpmn", 3);
    }

    @Test
    public void testConstraintParticipantImportedProcessFail()
            throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_call_participant_process.bpmn"), 1);
        assertViolation(
                result.getViolations().get(0),
                "An end event must be present when a start event is used in the same process level",
                "//bpmn:*[@id = '_3'][0]", 4);
    }

    @Override
    protected void assertViolation(Violation v, String fileName, int line) {
        assertViolation(v, ERRORMESSAGE, fileName, XPATHSTRING, line);
    }

    @Override
    protected String getExtNumber() {
        return "preprocessing";
    }

}
