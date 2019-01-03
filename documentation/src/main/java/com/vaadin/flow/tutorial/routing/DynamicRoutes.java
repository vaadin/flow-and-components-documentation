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

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.DynamicRoute;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("routing/tutorial-router-dynamic-routes.asciidoc")
public class DynamicRoutes {

    @Route("main")
    @RouteAlias("info")
    @RouteAlias("version")
    private class MyRoute extends Div {

        public MyRoute() {
            RouteConfiguration configuration = RouteConfiguration
                    .forSessionScope();

            RouteConfiguration.forSessionScope().setRoute("home", Home.class);
            RouteConfiguration.forSessionScope().setRoute("home", Home.class, MainLayout.class);

            configuration.setRoute("main", MyRoute.class);
            configuration.setRoute("info", MyRoute.class);
            configuration.setRoute("version", MyRoute.class);
            // No path "users" should be available
            configuration.removeRoute("users");

            // No navigationTarget Users should be available
            configuration.removeRoute(Users.class);

            // Only the Users navigationTarget should be removed from "users"
            configuration.removeRoute("users", Users.class);

            configuration.setRoute("home", Home.class);

            configuration.setRoute("home", Home.class, MainLayout.class);
        }
    }

    public class Admin extends Div {
    }

    public class User extends Div {
    }

    private static class Home extends Div {
    }

    private static class Users extends Div {
    }

    public class MainLayout extends Div implements RouterLayout {
        public MainLayout() {
            // Implementation omitted, but could contain a menu.
        }
    }

    @Route("")
    public class Login extends Div {

        private TextField login;
        private PasswordField password;

        public Login() {
            login = new TextField("Login");
            password = new PasswordField("Password");

            Button submit = new Button("Submit", this::handeLogin);

            add(login, password, submit);
        }

        private void handeLogin(ClickEvent<Button> buttonClickEvent) {
            // Validation of credentials is skipped

            RouteConfiguration configuration = RouteConfiguration.forSessionScope();

            if ("admin".equals(login.getValue())) {
                configuration.setRoute("", Admin.class, MainLayout.class);
            } else if ("user".equals(login.getValue())) {
                configuration.setRoute("", User.class, MainLayout.class);
            }

            configuration.setAnnotatedRoute(Info.class);

            UI.getCurrent().getPage().reload();
        }
    }

    @Route(value = "info", layout = MainLayout.class)
    @DynamicRoute
    public class Info extends Div {
        public Info() {
            add(new Span("This page contains info about the application"));
        }
    }
}
