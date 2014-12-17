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
package de.uniba.dsg.ppn.ba.artifacts;

import org.junit.Test;

import de.uniba.dsg.bpmnspector.common.ValidationResult;
import de.uniba.dsg.bpmnspector.common.Violation;
import de.uniba.dsg.ppn.ba.TestCase;
import de.uniba.dsg.ppn.ba.helper.BpmnValidationException;

abstract public class AbstractArtifactTest extends TestCase {

    @Test
    public void testConstraintAssociationFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("Fail_association.bpmn"), 1);
        assertViolation(result.getViolations().get(0));
    }

    @Test
    public void testConstraintGroupFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("Fail_group.bpmn"), 1);
        assertViolation(result.getViolations().get(0));
    }

    @Test
    public void testConstraintTextAnnotationFail()
            throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("Fail_text_annotation.bpmn"), 1);
        assertViolation(result.getViolations().get(0));
    }

    protected void assertViolation(Violation v) {
        throw new UnsupportedOperationException(
                "must be overriden by every child class!");
    }
}
