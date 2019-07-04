package com.vaadin.flow.tutorial.theme;

import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("theme/theming-crash-course.asciidoc")
public class ThemingCrashCourse {

    @Route(value = "")
    @JsModule("./styles/shared-styles.js")
    public class MyApplication extends Div {
    }

}
