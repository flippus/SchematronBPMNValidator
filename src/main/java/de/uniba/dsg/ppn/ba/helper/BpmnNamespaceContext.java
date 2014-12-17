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

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/**
 * Namespace Context helper class which sets the namespace uri to the bpmn
 * namespace if the prefix is bpmn
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class BpmnNamespaceContext implements NamespaceContext {

    @Override
    public String getNamespaceURI(String prefix) {
        if ("bpmn".equals(prefix)) {
            return ConstantHelper.BPMNNAMESPACE;
        } else if ("xml".equals(prefix)) {
            return XMLConstants.XML_NS_URI;
        }
        return XMLConstants.NULL_NS_URI;
    }

    @Override
    public String getPrefix(String uri) {
        return "";
    }

    @Override
    public Iterator<?> getPrefixes(String uri) {
        return null;
    }

}
