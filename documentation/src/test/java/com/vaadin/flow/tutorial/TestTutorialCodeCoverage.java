/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.tutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class TestTutorialCodeCoverage {
    private static final String ASCII_DOC_EXTENSION = ".asciidoc";
    private static final String WEB_SOURCE_MARK = "tutorial::";

    private static final Path DOCS_ROOT = new File(".").toPath();
    private static final Path JAVA_LOCATION = DOCS_ROOT
            .resolve(Paths.get("src", "main", "java"));
    private static final Path HTML_LOCATION = DOCS_ROOT
            .resolve(Paths.get("src", "main", "html"));
    private static final Path CSS_LOCATION = DOCS_ROOT
            .resolve(Paths.get("src", "main", "css"));

    private static final String HELPER_COMMENT = "// helper";
    private static final String CODE_FOR_COMMENT = "// code for ";

    private static final String JAVA_BLOCK_IDENTIFIER = "[source,java]";
    private static final String HTML_BLOCK_IDENTIFIER = "[source,html]";
    private static final String CSS_BLOCK_IDENTIFIER = "[source,css]";

    private static final Path TUTORIAL_GETTING_STARTED_LOCATION = new File("..")
            .toPath()
            .resolve(Paths.get("tutorial-getting-started", "src", "main"));
    private static final Path TUTORIAL_GETTING_STARTED_HTML_LOCATION = TUTORIAL_GETTING_STARTED_LOCATION
            .resolve(Paths.get("webapp", "frontend"));
    private static final Path TUTORIAL_GETTING_STARTED_JAVA_LOCATION = TUTORIAL_GETTING_STARTED_LOCATION
            .resolve(Paths.get("java"));

    private static final Path[] JAVA_LOCATIONS = new Path[] { JAVA_LOCATION,
            TUTORIAL_GETTING_STARTED_JAVA_LOCATION };

    private final StringBuilder documentationErrors = new StringBuilder();
    private int documentationErrorsCount;

    @Test
    public void verifyTutorialCode() throws IOException {
        List<TutorialLineChecker> lineCheckers = Arrays.asList(
                new CodeFileChecker(JAVA_BLOCK_IDENTIFIER, gatherJavaCode()),
                new CodeFileChecker(CSS_BLOCK_IDENTIFIER,
                        gatherWebFilesCode("css", CSS_LOCATION)),
                new CodeFileChecker(HTML_BLOCK_IDENTIFIER,
                        gatherWebFilesCode("html", HTML_LOCATION,
                                TUTORIAL_GETTING_STARTED_HTML_LOCATION)),
                new AsciiDocLinkWithDescriptionChecker("image:",
                        Pattern.compile("image:(.*?)\\[(.*?)]")),
                new AsciiDocLinkWithDescriptionChecker("#,",
                        Pattern.compile("<<(.*?)#,(.*?)>>"),
                        ASCII_DOC_EXTENSION));

        Files.walk(DOCS_ROOT)
                .filter(path -> path.toString().endsWith(ASCII_DOC_EXTENSION))
                .collect(Collectors.toSet())
                .forEach(tutorialPath -> verifyTutorial(tutorialPath,
                        lineCheckers));

        if (documentationErrorsCount > 0) {
            documentationErrors.insert(0,
                    String.format("%nFound %s problems with documentation",
                            documentationErrorsCount));
            Assert.fail(documentationErrors.toString());
        }
    }

    private void verifyTutorial(Path tutorialPath,
            List<TutorialLineChecker> lineCheckers) {
        String tutorialName = DOCS_ROOT.relativize(tutorialPath).toString();
        try {
            AtomicInteger lineCounter = new AtomicInteger();
            for (String line : Files.readAllLines(tutorialPath)) {
                int lineNumber = lineCounter.incrementAndGet();
                lineCheckers.stream()
                        .map(checker -> checker.verifyTutorialLine(tutorialPath,
                                tutorialName, line, lineNumber))
                        .filter(errorList -> !errorList.isEmpty())
                        .flatMap(Collection::stream)
                        .forEach(this::addDocumentationError);
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    "An error during file read occurred, file = "
                            + tutorialPath.toAbsolutePath(),
                    e);
        }
    }

    private Map<String, Set<String>> gatherJavaCode() throws IOException {
        Map<String, Set<String>> codeFileMap = new HashMap<>();

        // Populate map based on '// code for' comments
        for (Path javaLocation : JAVA_LOCATIONS) {
            Files.walk(javaLocation)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> extractJavaFiles(path, codeFileMap));
        }

        return codeFileMap;
    }

    private Map<String, Set<String>> gatherWebFilesCode(String extension,
            Path... locations) throws IOException {
        Map<String, Set<String>> codeFileMap = new HashMap<>();

        for (Path location : locations) {
            Files.walk(location)
                    .filter(path -> path.toString().endsWith('.' + extension))
                    .forEach(path -> extractWebFiles(path, codeFileMap));
        }

        return codeFileMap;
    }

    private void extractJavaFiles(Path javaFile,
            Map<String, Set<String>> allowedLines) {

        String tutorialName = null;
        Set<String> lines = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(javaFile.toFile()))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith(CODE_FOR_COMMENT)) {
                    tutorialName = trimWhitespace(
                            line.replace(CODE_FOR_COMMENT, ""));
                } else if (line.startsWith(HELPER_COMMENT)) {
                    // found a helper class, ignore
                    return;
                } else {
                    lines.add(trimWhitespace(line));
                }
                line = reader.readLine();
            }

        } catch (IOException e) {
            throw new UncheckedIOException(String
                    .format("Failed to read the java file '%s'", javaFile), e);
        }

        if (tutorialName != null && !tutorialName.isEmpty()) {
            String name = tutorialName;
            lines.forEach(line -> addLineToAllowed(allowedLines, name, line));
        } else {
            addDocumentationError(
                    "Java file without '// code for' or '// helper comments: "
                            + javaFile);
        }
    }

    private void extractWebFiles(Path htmlFile,
            Map<String, Set<String>> allowedLines) {
        try {
            List<String> lines = Files.readAllLines(htmlFile);
            String idLine = lines.remove(0);
            if (idLine.startsWith(WEB_SOURCE_MARK)) {
                String tutorialName = idLine
                        .substring(WEB_SOURCE_MARK.length());
                lines.forEach(line -> addLineToAllowed(allowedLines,
                        tutorialName, line));
            } else {
                addDocumentationError(
                        "Html file with faulty tutorial header: " + htmlFile);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addLineToAllowed(Map<String, Set<String>> allowedLines,
            String tutorialName, String line) {
        allowedLines
                .computeIfAbsent(tutorialName.replace('/', File.separatorChar),
                        n -> new HashSet<>())
                .add(trimWhitespace(line));
    }

    static String trimWhitespace(String codeLine) {
        return codeLine.replaceAll("\\s", "");
    }

    private void addDocumentationError(String documentationError) {
        documentationErrorsCount++;

        documentationErrors.append(System.lineSeparator());
        documentationErrors
                .append(String.format("%s. ", documentationErrorsCount));
        documentationErrors.append(documentationError);
    }

}
