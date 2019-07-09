package com.vaadin.flow.tutorial.theme;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("theme/application-theming-basics.asciidoc")
public class ApplicationThemingBasics {

    @CssImport("./styles/shared-styles.css")
    @Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
    public class MainLayout extends Div implements RouterLayout {
    }
}
