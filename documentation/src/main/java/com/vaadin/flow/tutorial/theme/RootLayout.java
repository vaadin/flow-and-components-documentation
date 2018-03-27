package com.vaadin.flow.tutorial.theme;


import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("theme/using-component-themes.asciidoc")
@Theme(Lumo.class)
public class RootLayout extends Div implements PageConfigurator, RouterLayout {

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.getUi().getElement().setAttribute("theme", "dark");
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {

    }

}
