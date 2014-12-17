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
 * Test class for testing the XSD validation
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class Xsd extends TestCase {

    @Test
    public void testXsdFail() throws BpmnValidationException {
        ValidationResult result = verifyInValidResult(
                createFile("xsdfail.bpmn"), 1);
        Violation v = result.getViolations().get(0);
        assertEquals("xsdfail.bpmn", v.getFileName());
        assertEquals(6, v.getLine());
        assertTrue(v.getMessage().contains("cvc-complex-type.2.4.a:"));
        assertTrue(v.getMessage().contains("outgoing"));
        assertEquals("", v.getxPath());
        assertEquals("XSD-Check", v.getConstraint());
    }

    @Override
    protected String getExtNumber() {
        return "xsd";
    }
}
