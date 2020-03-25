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
package com.vaadin.flow.tutorial.routing;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.router.UrlParameters;
import com.vaadin.flow.tutorial.annotations.CodeFor;
import com.vaadin.flow.tutorial.routing.UrlTemplatesModifier.UserProfileEdit;

@CodeFor("routing/tutorial-routing-navigation.asciidoc")
public class RouterNavigation {

    void navigation() {
        NativeButton button = new NativeButton(
                "Navigate to company");
        button.addClickListener(e ->
            button.getUI().ifPresent(ui ->
                    ui.navigate("company"))
        );

        NativeButton editButton = new NativeButton(
                "Edit user details");
        editButton.addClickListener(e ->
                button.getUI().ifPresent(ui -> ui.navigate(
                        UserProfileEdit.class,
                        new UrlParameters("userID", "123")))
        );
    }

    void routerLink() {
        Div menu = new Div();
        menu.add(new RouterLink("Home", HomeView.class));
        menu.add(new RouterLink("Greeting",
                GreetingComponent.class, "default"));
        // user/123/edit
        menu.add(new RouterLink("Edit user details",
                UserProfileEdit.class, new UrlParameters("userID", "123")));
        // user/edit
        menu.add(new RouterLink("Edit my details",
                UserProfileEdit.class));
    }

    @Route(value = "greet")
    public class GreetingComponent extends Div
            implements HasUrlParameter<String> {

        @Override
        public void setParameter(BeforeEvent event,
                String parameter) {
            setText(String.format("Hello, %s!", parameter));
        }
    }

    @Route(value = "")
    public class HomeView extends Component {

    }
}
