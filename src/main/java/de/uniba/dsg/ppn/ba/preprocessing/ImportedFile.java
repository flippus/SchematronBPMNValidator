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

import java.io.File;

/**
 * Just a helper class for avoiding the usage of object arrays and making the
 * code better readable. Is returned as result from
 * preprocessor.selectImportedFiles()
 *
 * @author Philipp Neugebauer
 * @version 1.0
 *
 */
public class ImportedFile {

    private final File file;
    private final String prefix;
    private final String namespace;
    private final String importType;

    public ImportedFile(File file, String prefix, String namespace,
            String importType) {
        this.file = file;
        this.prefix = prefix;
        this.namespace = namespace;
        this.importType = importType;
    }

    public File getFile() {
        return file;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getImportType() {
        return importType;
    }
}
