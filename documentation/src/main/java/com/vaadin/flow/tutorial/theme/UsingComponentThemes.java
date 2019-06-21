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
package com.vaadin.flow.tutorial.theme;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.AbstractTheme;
import com.vaadin.flow.theme.NoTheme;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("theme/using-component-themes.asciidoc")
public class UsingComponentThemes {

    @Route(value = "")
    public class LumoApplication extends Div {
    }

    @Route(value = "")
    @Theme(MyTheme.class)
    public class MaterialApplication extends Div {
    }

    @Theme(MyTheme.class)
    public class MainLayout extends Div implements RouterLayout {
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

    public class Button extends Component {
		public Button(String string) {
        }
        public void addThemeVariants(ButtonVariant... vars) {
        }
        public List<String> getThemeNames() {
            return null;
        }
    }

    {
        Button button = new Button("Themed button");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_CONTRAST);   
    }
    {
        Button button = new Button("Themed button");
        button.getThemeNames().addAll(Arrays.asList("contrast", "primary"));
    }
    {
        Button button = new Button("Themed button");
        String themeAttributeName = "theme";
        String oldValue = button.getElement().getAttribute(themeAttributeName);
        String variantsToAdd = "contrast primary";
        button.getElement().setAttribute(themeAttributeName,
                oldValue == null || oldValue.isEmpty() ? variantsToAdd
                        : ' ' + variantsToAdd);        
    }

    @JsModule("frontend://bower_components/vaadin-lumo-styles/presets/compact.js")
    @Theme(Lumo.class)
    public class CompactMainLayout extends Div implements RouterLayout {
    }    

    @JsModule("@vaadin/vaadin-lumo-styles/color.js")
    public class MyTheme implements AbstractTheme {
        @Override
        public String getBaseUrl() {
            return "/src/";
        }

        @Override
        public String getThemeUrl() {
            return "/theme/myTheme/";
        }
    }
}
