package com.vaadin.flow.tutorial.advanced;

import java.util.Optional;

import com.vaadin.flow.component.WebComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.webcomponent.WebComponentMethod;
import com.vaadin.flow.component.webcomponent.WebComponentProperty;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("advanced/tutorial-sswc.asciidoc")
@WebComponent("login-form")
public class LoginForm extends Div {

    private WebComponentProperty<String> userlbl = new WebComponentProperty<>(
            "", String.class);

    private WebComponentProperty<String> pwdlbl = new WebComponentProperty<>("",
            String.class);

    private TextField userName = new TextField();
    private PasswordField password = new PasswordField();
    private Div errorMsg = new Div();
    private Div msg = new Div();

    public LoginForm() {
        FormLayout layout = new FormLayout();

        updateForm(layout);

        userlbl.addValueChangeListener(event -> updateForm(layout));
        pwdlbl.addValueChangeListener(event -> updateForm(layout));

        add(layout);

        Button login = new Button("Login", event -> login());
        add(login, errorMsg);
    }

    private void updateForm(FormLayout layout) {
        layout.removeAll();

        layout.addFormItem(userName, userlbl.get());
        layout.addFormItem(password, pwdlbl.get());
    }

    private void login() {
        Optional<Object> authToken = UserService.getInstance()
                .authenticate(userName.getValue(), password.getValue());
        if (authToken.isPresent()) {
            VaadinRequest.getCurrent().getWrappedSession()
                    .setAttribute("auth_token", authToken.get());
            getUI().get().getPage()
                    .executeJavaScript("window.location.href='/'");
        } else {
            errorMsg.setText("Authentication failure");
        }
    }

    @WebComponentMethod("message")
    public void setMessage(String message) {
        msg.setText(message);
    }

}