package com.vaadin.flow.tutorial.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("components/tutorial-flow-shortcut.asciidoc")
public class ShortcutBasic {

    public void clickShortcut() {
        Button button = new Button();
        button.addClickListener(event -> {/* do the click */});
        button.addClickShortcut(Key.ENTER);
    }

    public void focusShortcut() {
        TextField textField = new TextField("Label");
        textField.addFocusShortcut(Key.KEY_F, KeyModifier.ALT);
    }

    public void generalPurposeShortcut()  {
        // ex 1
        UI.getCurrent().addShortcut(this::openCustomerCreation, Key.KEY_N,
                KeyModifier.CONTROL, KeyModifier.ALT);

        // ex 2
        UI.getCurrent().addShortcut(() -> Notification.show("Shortcut triggered"),
                Key.SPACE);
    }

    public class Scope extends Div {
        public Scope() {
            TextField firstName = new TextField();
            TextField lastName = new TextField();

            add(firstName, lastName);

            // the first `this` is the scope, or "owner" of this shortcut. The
            // second `this` is the lifecycleOwner, which will be discussed later
            Shortcuts.addShortcut(this, this,
                    () -> {
                        firstName.setValue("");
                        lastName.setValue("");
                        firstName.focus();},
                    Key.ESCAPE);
        }
    }

    public void removingShortcut() {
        TextField textField = new TextField("Label");
        Registration registration = textField.addFocusShortcut(Key.KEY_F,
                KeyModifier.ALT);

        // something happens here

        registration.remove(); // shortcut removed!
    }

    public void shortcutLifecycle() {
        Paragraph paragraph = new Paragraph("When you see me, try ALT+G!");

        Shortcuts.addShortcut(paragraph, () -> Notification.show("Well done!"),
                Key.KEY_G, KeyModifier.ALT);

        add(paragraph);
    }

    public void configuringShortcuts_modifiers() {
        Input input = new Input();
        input.registerFocusShortcut(Key.KEY_F).withAlt().withShift();
    }

    private Div anotherComponent = new Div();
    public void configuringShortcuts_lifecycleOwner() {
        UI.getCurrent().registerShortcut(() -> {/* do a thing*/}, Key.KEY_F)
                .bindLifecycleTo(anotherComponent);
    }

    public void configuringShortcuts_clientsideEventBehavior() {
        Input input = new Input();
        input.registerFocusShortcut(Key.KEY_F)
                // other handlers can now catch this event
                .allowEventPropagation()
                // the character 'f' will be written out, if a text field is focused
                .allowBrowserDefault();
    }


    /**
     * Helpers
     */

    private void openCustomerCreation() {}

    private void add(Component... components) {}
}
