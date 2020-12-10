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
package com.vaadin.flow.tutorial.typescript;

import com.vaadin.flow.tutorial.annotations.CodeFor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@CodeFor("typescript/adding-custom-login-form-with-spring-security.asciidoc")
public class AddingCustomLoginFormWithSpringSecurity {

    @EnableWebSecurity
    @Configuration
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

      @Override
      protected void configure(HttpSecurity http) throws Exception {
        // Ignore the login processing url and vaadin endpoint calls
        http.csrf().ignoringAntMatchers("/login", "/connect/**");
        
        // specify the URL of the login view, the value of the parameter
        // is the defined route for the login view component.
        http.formLogin().loginPage("login");
      } 

    }

}

