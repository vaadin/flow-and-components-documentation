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
package com.vaadin.flow.tutorial.components;

import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("components/tutorial-component-basic-features.asciidoc")
public class ComponentBasicFeatures {

    @Id("my-component")
    private Component mappedComponent;

    public void visibility() {
        Span label = new Span("My label");
        label.setVisible(false);
        // this is not transmitted to the client side
        label.setText("Changed my label");

        //@formatter:off
        Button makeVisible = new Button("Make visible", evt -> {
            // makes the label visible - only now the "Changed my label" text is transmitted
            label.setVisible(true);
        });
        //@formatter:on

        Div container = new Div();
        // the label is not transmitted to the client side. The corresponding
        // element will be created in the DOM only when it becomes visible
        container.add(label);

        // prints 1 - the server-side structure is preserved no matter if the
        // component is visible or not
        System.out.println("Number of children: "
                + container.getChildren().collect(Collectors.counting()));

        // sets the attribute "hidden" of the element on the client-side
        mappedComponent.setVisible(false);
    }

    public void id() {
        Span component = new Span();
        component.setId("my-component");
    }

    public void enabledState_component() {
        FormLayout form = new FormLayout();

        TextField name = new TextField("Name");
        TextField email = new TextField("E-mail");
        Button submit = new Button("Submit");
        submit.setEnabled(false); // the submit button is disabled

        form.add(name, email, submit);
    }

    public void enabledState_layout() {
        //@formatter:off
        FormLayout form = new FormLayout();
        form.setEnabled(false); // the entire form is disabled

        TextField name = new TextField("Name");
        System.out.println(name.isEnabled()); // prints true, since it is not attached yet

        Button submit = new Button("Submit");
        submit.setEnabled(false); // the submit button is disabled
        System.out.println(submit.isEnabled()); // prints false

        form.add(name, submit); // attaches children

        System.out.println(name.isEnabled()); // prints false
        System.out.println(submit.isEnabled()); // prints false

        form.remove(name); // the name field gets detached
        System.out.println(name.isEnabled()); // prints true

        form.remove(submit); // the submit button gets detached
        System.out.println(submit.isEnabled()); // prints false, since it was disabled directly
        //@formatter:on
    }

}
