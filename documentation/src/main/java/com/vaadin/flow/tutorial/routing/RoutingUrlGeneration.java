/*
 * Copyright 2000-2020 Vaadin Ltd.
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

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("routing/tutorial-routing-url-generation.asciidoc")
public class RoutingUrlGeneration {

    @Route("path")
    public class PathComponent extends Div {
        public PathComponent() {
            setText("Hello @Route!");
        }
    }

    public class Menu extends Div {
        public Menu() {
            String route = RouteConfiguration.forSessionScope()
                    .getUrl(PathComponent.class);
            Anchor link = new Anchor(route, "Path");
            add(link);
        }
    }

    @Route(value = "greet")
    public class GreetingComponent extends Div
            implements HasUrlParameter<String> {

        @Override
        public void setParameter(BeforeEvent event, String parameter) {
            setText(String.format("Hello, %s!", parameter));
        }
    }

    public class ParameterMenu extends Div {
        public ParameterMenu() {
            String route = RouteConfiguration.forSessionScope()
                    .getUrl(GreetingComponent.class, "anonymous");
            Anchor link = new Anchor(route, "Greeting");
            add(link);
        }
    }
}
