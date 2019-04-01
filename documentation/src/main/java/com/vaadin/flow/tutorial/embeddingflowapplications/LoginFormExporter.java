package com.vaadin.flow.tutorial.embeddingflowapplications;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.WebComponentExporter;
import com.vaadin.flow.component.webcomponent.WebComponent;
import com.vaadin.flow.component.webcomponent.WebComponentDefinition;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("embedding-flow-applications/tutorial-webcomponent-exporter.asciidoc")
@Tag("login-form")
public class LoginFormExporter implements WebComponentExporter<LoginForm> {

    @Override
    public void define(WebComponentDefinition<LoginForm> definition) {
        definition.addProperty("userlbl", "")
                .onChange(LoginForm::setUserNameLabel);
        definition.addProperty("pwdlbl", "")
                .onChange(LoginForm::setPasswordLabel);
    }

    @Override
    public void configure(WebComponent<LoginForm> webComponent,
            LoginForm form) {
        form.addLoginListener(() -> webComponent.fireEvent("logged-in"));
    }
}
