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
package com.vaadin.flow.tutorial.polymer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("polymer-templates/tutorial-template-components-in-slot.asciidoc")
public class PolymerSlotView {
    @Tag("component-container")
    @HtmlImport("/com/example/ComponentContainer.html")
    public class ComponentContainer extends PolymerTemplate<TemplateModel> {

        public ComponentContainer() {
            Element label = ElementFactory.createLabel("Main layout header");
            Element button = ElementFactory.createButton("Click me");

            getElement().appendChild(label, button);
        }
    }

    @Tag("main-layout")
    @HtmlImport("/com/example/ComponentContainer.html")
    public class MainLayout extends PolymerTemplate<TemplateModel>
            implements RouterLayout {
    }

    @Route(value = "editor", layout = MainLayout.class)
    public class Editor extends Div {
    }

    @ParentLayout(MainLayout.class)
    public class MenuBar extends Div {
    }

    @Tag("name-element")
    @HtmlImport("/com/example/NameElement.html")
    public class NameElement extends PolymerTemplate<TemplateModel> {
        public NameElement() {
            Element firstName = ElementFactory.createSpan("Jack");
            Element middleName = ElementFactory.createSpan(" James");
            Element surName = ElementFactory.createSpan("Christobald");

            firstName.setAttribute("slot", "firstName");
            middleName.setAttribute("slot", "firstName");
            surName.setAttribute("slot", "lastName");

            getElement().appendChild(firstName, middleName, surName);
        }
    }
}
