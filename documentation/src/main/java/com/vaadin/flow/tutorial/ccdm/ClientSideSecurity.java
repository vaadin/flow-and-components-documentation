/*
 * Copyright 2000-2018 Vaadin Ltd.
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
package com.vaadin.flow.tutorial.ccdm;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.vaadin.flow.server.connect.VaadinService;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("ccdm/client-side-security.asciidoc")
public class ClientSideSecurity {

    @VaadinService
    public class CounterService {
        public int addOne(int number) {
            return number + 1;
        }
    }

    @VaadinService
    @DenyAll
    public class MyService {

    public void deniedService() {
        // Not possible to call by any request due to class-level annotation
    }

    @AnonymousAllowed
    public void anonymousService() {
        // Possible to call by any request (even without
        // authorization) since method level annotation
        // overrides class-level one
    }

    @PermitAll
    public void permittedToAllService() {
        // Permitted to all authenticated users, same as if
        // there were no security annotations on the class
        // and the methods.
        // Since there's a `@DenyAll` annotation is on the
        // class, we specify this one on a method to override
        // the class one.
    }

    @RolesAllowed("ROLE_ADMIN")
    public void permittedToToleService() {
        // Permited to all authenticated users belonging
        // to the ROLE_ADMIN
    }
    }

    @VaadinService
    public class EchoService {
        public String saySomething(String message, HttpServletRequest req) {
            return req.getUserPrincipal().getName() + " says: " + message;
        }
    }

    public class CustomPrincipal implements Principal {
        private final String name;
        private final List<String> roles;

        public CustomPrincipal(String name, String ...roles) {
            this.name = name;
            this.roles = Arrays.asList(roles);
        }

        public String getName() {
            return name;
        }

        public boolean isUserInRole(String role) {
            return roles.contains(role);
        }
    }

    public class CustomHttpServletRequest extends HttpServletRequestWrapper {
        public CustomHttpServletRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public Principal getUserPrincipal() {
            Principal myUser = (Principal) getSession().getAttribute("User");
            return myUser != null ? myUser : super.getUserPrincipal();
        }

        @Override
        public boolean isUserInRole(String role) {
            return getUserPrincipal() instanceof CustomPrincipal
                    && ((CustomPrincipal) getUserPrincipal()).isUserInRole(role)
                    || super.isUserInRole(role);
        }

    }

    @WebFilter("/connect")
    public class CustomWebFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response,
                FilterChain chain) throws IOException, ServletException {

            // Check username/password and set the `User` attribute in session
            if ("foo".equals(request.getParameter("username")) &&
                "abc123".equals(request.getParameter("password"))) {
                ((HttpServletRequest) request).getSession()
                    .setAttribute("User", new CustomPrincipal("foo"));
            }

            // wrap original request with our custom implementation
            chain.doFilter(new CustomHttpServletRequest((HttpServletRequest) request), response);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException { }
        @Override
        public void destroy() { }
    }

}

