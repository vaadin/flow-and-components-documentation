package com.vaadin.flow.tutorial.theme;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("theme/theming-crash-course.asciidoc")
public class ThemingCrashCourse {

    @Route(value = "")
    @CssImport("./styles/shared-styles.css")
    @CssImport(value = "./styles/shared-styles.css",
            include = "common-styles")
    @CssImport(value = "./styles/common-styles.css",
            id = "common-styles")
    @CssImport(value = "./styles/specific-styles.css",
            include = "common-styles")
    public class MyApplication extends Div {
    }



}


