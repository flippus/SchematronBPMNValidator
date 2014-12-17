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

/**
 * Test class for testing Constraint EXT.008
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class Ext008 extends AbstractArtifactTest {

    @Override
    protected void assertViolation(de.uniba.dsg.bpmnspector.common.Violation v) {
        assertViolation(v, "//bpmn:messageFlow[@targetRef][0]", 7);
    }

    @Override
    protected String getErrorMessage() {
        return "An Artifact MUST NOT be a target for a Message Flow";
    }

    @Override
    protected String getExtNumber() {
        return "008";
    }
}
