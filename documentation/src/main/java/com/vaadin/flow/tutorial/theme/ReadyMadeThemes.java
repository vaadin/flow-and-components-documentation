package com.vaadin.flow.tutorial.theme;

import java.util.Arrays;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.NoTheme;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("theme/using-component-themes.asciidoc")
public class ReadyMadeThemes {
    @Route(value = "")
    @Theme(Lumo.class) // can be omitted for Lumo
    public class Application extends Div {
    }

    @Theme(MyTheme.class)
    public class MainLayout extends Div implements RouterLayout {
    }

    @HtmlImport("frontend://bower_components/vaadin-lumo-styles/presets/compact.html")
    @Theme(Lumo.class)
    public class CompactMainLayout extends Div implements RouterLayout {
    }

    @Route(value = "", layout = MainLayout.class)
    public class HomeView extends Div {
    }

    @Route(value = "blog", layout = MainLayout.class)
    public class BlogPost extends Div {
    }

    @Route(value = "")
    @NoTheme
    public class UnThemedApplication extends Div {
    }

    @Route(value = "")
    @Theme(value = MyTheme.class, variant = "large")
    public class LargeThemedApplication extends Div {
    }

    @Route(value = "")
    @Theme(value = Lumo.class, variant = Lumo.DARK)
    public class DarkApplication extends Div {
    }

    @Route(value = "")
    @Theme(value = Material.class, variant = Material.DARK)
    public class DarkMaterialApplication extends Div {
    }

    public class MyTheme extends Lumo {
    }

    public void buttonVariant() {
        Button button = new Button("Themed button");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_CONTRAST);
    }

    public void buttonVariant2() {
        Button button = new Button("Themed button");
        button.getThemeNames().addAll(Arrays.asList("contrast", "primary"));
    }

    public void buttonVariant3() {
        Button button = new Button("Themed button");
        String themeAttributeName = "theme";
        String oldValue = button.getElement().getAttribute(themeAttributeName);
        String variantsToAdd = "contrast primary";
        button.getElement().setAttribute(themeAttributeName,
                oldValue == null || oldValue.isEmpty() ? variantsToAdd
                        : ' ' + variantsToAdd);
    }
}
