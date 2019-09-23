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

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("theme/tutorial-theming-overlay.asciidoc")
public class TutorialThemingOverlay {
    @Route(value = "")
    @CssImport(value = "./styles/my-overlay-theme.css",
            themeFor = "vaadin-*-overlay")
    public class MyApplication extends Div {
    }

    @Route(value = "")
    @CssImport(value="./styles/my-dialog-overlay-theme.css",
            themeFor = "vaadin-dialog-overlay")
    public class MyApplicationWithDialog extends Div {
    }

    public class MyView extends VerticalLayout {
        public MyView() {
            Dialog dialog = new Dialog();
            dialog.getElement().setAttribute("theme",
                    "custom-theme-variant");
        }
    }
}
