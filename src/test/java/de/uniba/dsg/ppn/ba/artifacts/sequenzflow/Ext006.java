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
package de.uniba.dsg.ppn.ba.artifacts.sequenzflow;

import de.uniba.dsg.bpmnspector.common.Violation;

public class Ext006 extends AbstractArtifactSequenceFlowTest {

    private final static String ERRORMESSAGEONE = "An Artifact MUST NOT be a target for a Sequence Flow";
    private final static String ERRORMESSAGETWO = "For a Process: Of the types of FlowNode, only Activities, Gateways, and Events can be the target. However, Activities that are Event SubProcesses are not allowed to be a target";
    private final static String ERRORMESSAGETHREE = "The target element of the sequence flow must reference the SequenceFlow definition using their incoming attribute.";
    private final static String XPATHSTRING = "//bpmn:sequenceFlow[@targetRef][0]";

    @Override
    protected void assertFirstViolation(Violation v, String fileName) {
        assertViolation(v, ERRORMESSAGEONE, fileName, XPATHSTRING, 7);
    }

    @Override
    protected void assertSecondViolation(Violation v, String fileName, int line) {
        assertViolation(v, ERRORMESSAGETWO, fileName,
                "//bpmn:*[./@id = //bpmn:sequenceFlow/@targetRef][0]", line);
    }

    @Override
    protected void assertThirdViolation(Violation v, String fileName) {
        assertViolation(v, ERRORMESSAGETHREE, fileName, XPATHSTRING, 7);
    }

    @Override
    protected String getExtNumber() {
        return "006";
    }
}
