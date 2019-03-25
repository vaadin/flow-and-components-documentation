package com.vaadin.starter.skeleton;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("introduction/tutorial-get-started-first-part.asciidoc")
public class TutorialGetStartedFirstPart {
    /**
     * The main view contains a button and a click listener.
     */
    @Route("1")
    public static class MainView extends VerticalLayout {

        public MainView() {
            Button button = new Button("Click me",
                    event -> Notification.show("Clicked!"));
            add(button);
        }
    }
}
