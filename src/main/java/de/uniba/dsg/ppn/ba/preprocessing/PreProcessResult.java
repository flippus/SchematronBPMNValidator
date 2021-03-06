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
package de.uniba.dsg.ppn.ba.preprocessing;

import java.util.Map;

import org.w3c.dom.Document;

/**
 * Just a helper class for avoiding the usage of object arrays and making the
 * code better readable. Is returned as result from preprocessor.preprocess()
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class PreProcessResult {

    private final Document documentResult;
    private final Map<String, String> namespaceTable;

    public PreProcessResult(Document documentResult,
            Map<String, String> namespaceTable) {
        this.documentResult = documentResult;
        this.namespaceTable = namespaceTable;
    }

    public Document getDocumentResult() {
        return documentResult;
    }

    public Map<String, String> getNamespaceTable() {
        return namespaceTable;
    }
}
