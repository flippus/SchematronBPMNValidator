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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.JAXBException;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import de.uniba.dsg.bpmnspector.common.ValidationResult;
import de.uniba.dsg.ppn.ba.helper.BpmnValidationException;
import de.uniba.dsg.ppn.ba.validation.SchematronBPMNValidator;
import de.uniba.dsg.ppn.ba.xml.XmlWriter;

public class Main {

    private final static Logger LOGGER;
    private final static Level DEBUGLEVEL;
    private final static String LICENSE = "SchematronBPMNValidator  Copyright (C) 2014  Philipp Neugebauer\n This program comes with ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under certain conditions; See LGPLv3.";

    static {
        DEBUGLEVEL = Level.DEBUG;
        LOGGER = (Logger) LoggerFactory.getLogger(Main.class.getSimpleName());
    }

    public static void main(String... args) {
        System.out.println(LICENSE);
        SchematronBPMNValidator validator = new SchematronBPMNValidator();
        ArrayList<String> argsAsList = new ArrayList<>(Arrays.asList(args));
        XmlWriter xmlWriter = new XmlWriter();

        if (argsAsList.contains("--debug") || argsAsList.contains("-d")) {
            validator.setLogLevel(DEBUGLEVEL);
            LOGGER.setLevel(DEBUGLEVEL);
            ((Logger) LoggerFactory.getLogger(xmlWriter.getClass()
                    .getSimpleName())).setLevel(DEBUGLEVEL);
            argsAsList.remove("-d");
            argsAsList.remove("--debug");
        }

        LOGGER.info("loglevel is set to {}", LOGGER.getEffectiveLevel());

        if (argsAsList.isEmpty()) {
            LOGGER.error("There must be files to check!");
            System.exit(-1);
        } else {
            for (String parameter : argsAsList) {
                try {
                    File file = new File(parameter);
                    if (!file.isAbsolute()) {
                        file = file.getAbsoluteFile();
                    }
                    ValidationResult result = validator.validate(file);
                    xmlWriter.writeResult(result,
                            new File(file.getParentFile() + File.separator
                                    + "validation_result_" + file.getName()
                                    + ".xml"));
                } catch (BpmnValidationException e) {
                    LOGGER.error(e.getMessage());
                } catch (JAXBException e) {
                    LOGGER.error("result of validation couldn't be written in xml!");
                }
            }
        }
    }
}
