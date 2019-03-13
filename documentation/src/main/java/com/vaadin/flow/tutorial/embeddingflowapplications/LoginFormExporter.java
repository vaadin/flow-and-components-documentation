package com.vaadin.flow.tutorial.embeddingflowapplications;

import com.vaadin.flow.component.WebComponentExporter;
import com.vaadin.flow.component.webcomponent.WebComponentDefinition;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("embedding-flow-applications/tutorial-webcomponent-exporter.asciidoc")
public class LoginFormExporter implements WebComponentExporter<LoginForm> {

    @Override
    public String tag() {
        return "login-form";
    }

    @Override
    public void define(WebComponentDefinition<LoginForm> definition) {
        definition.addProperty("userlbl", "").onChange(LoginForm::setUserNameLabel);
        definition.addProperty("pwdlbl", "").onChange(LoginForm::setPasswordLabel);
    }
}
