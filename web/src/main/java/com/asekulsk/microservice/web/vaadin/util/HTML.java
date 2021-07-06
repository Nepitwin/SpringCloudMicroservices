/*
 * Copyright 2018 Andreas Sekulski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.asekulsk.microservice.web.vaadin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Scanner;

/**
 * Hyper text markup language class to handle static content.
 *
 * @author Andreas Sekulski
 */
public class HTML {

    /**
     * Define the logger object for this class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HTML.class);

    /**
     * Load static served html file.
     *
     * @param classLoader Classloader to load files from ressources.
     * @param file        HTML file to load.
     * @param locale      Locale to get informations for specific languages..
     * @return Empty String result if file not found or empty otherwise file as string output.
     */
    public static String loadStaticHTML(ClassLoader classLoader, String file, Locale locale) {
        File htmlFile;
        String country = locale.toLanguageTag();

        URL url = classLoader.getResource(file + "_" + country + ".html");
        if (url != null) {
            htmlFile = new File(url.getFile());
            if (htmlFile.exists()) {
                return loadFile(htmlFile);
            }
        }

        return "<h1><STATIC_FILE_NOT_FOUND></h1>";
    }

    /**
     * Loads given file and reads all input.
     *
     * @param file File to load.
     * @return Reads file and returns input as string if file exists otherwise empty string.
     */
    private static String loadFile(File file) {
        String html = "";

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                html += scanner.nextLine();
            }
            scanner.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return html;
    }
}
