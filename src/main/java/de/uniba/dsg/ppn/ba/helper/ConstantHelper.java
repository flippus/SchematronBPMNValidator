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
package de.uniba.dsg.ppn.ba.helper;

/**
 * This class provides the constants used in the whole project
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class ConstantHelper {

    public final static String PINAMESPACE = "http://www.uniba.de/pi/bpmn-cons/validation";
    public final static String BPMNNAMESPACE = "http://www.omg.org/spec/BPMN/20100524/MODEL";
    public final static String BPMNDINAMESPACE = "http://www.omg.org/spec/BPMN/20100524/DI";
    public final static String FILENOTFOUNDMESSAGE = "file {} couldn't be read.";
    public final static String FILENOTFOUNDMESSAGEWITHCAUSE = String.format(
            "%s%s", FILENOTFOUNDMESSAGE, "Cause: {}");

}
