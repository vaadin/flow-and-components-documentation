package com.vaadin.flow.tutorial.theme;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("theme/theming-crash-course.asciidoc")
public class ThemingCrashCourse {

    @Route(value = "")

    // tag::cssimport[]
    @CssImport("./styles/shared-styles.css")
    // end::cssimport[]

    @CssImport(value = "./styles/shared-styles.css", include = "common-styles")

    // tag::cssimport-id[]
    @CssImport(value = "./styles/common-styles.css", id = "common-styles")
    // end::cssimport-id[]

    // tag::cssimport-include[]
    @CssImport(value = "./styles/specific-styles.css", include = "common-styles")
    // end::cssimport-include[]

    // tag::cssimport-themefor[]
    @CssImport(value = "./styles/text-field.css", themeFor = "vaadin-text-field")
    // end::cssimport-themefor[]

    public class MyApplication extends Div {
    }

}
