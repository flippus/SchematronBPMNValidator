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

public class Ext023 extends TestCase {

    @Test
    public void testConstraintNoIncomingFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_no_incoming.bpmn"), 1);
        assertViolation(
                result.getViolations().get(0),
                "The target element of the sequence flow must reference the SequenceFlow definition using their incoming attribute.",
                "//bpmn:sequenceFlow[@targetRef][0]", 10);

    }

    @Test
    public void testConstraintNoOutgoingFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("fail_no_outgoing.bpmn"), 1);
        assertViolation(
                result.getViolations().get(0),
                "The source element of the sequence flow must reference the SequenceFlow definition using their outgoing attribute.",
                "//bpmn:sequenceFlow[@sourceRef][0]", 10);
    }

    @Test
    public void testConstraintSuccess() throws BpmnValidationException {
        verifyValidResult(createFile("success.bpmn"));
    }

    @Override
    protected String getExtNumber() {
        return "023";
    }
}
