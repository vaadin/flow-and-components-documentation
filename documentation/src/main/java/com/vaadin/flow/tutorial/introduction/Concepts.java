package com.vaadin.flow.tutorial.introduction;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("introduction/introduction-concepts.asciidoc")
public class Concepts {

    public Concepts() {
    	Button button = new Button("Push me!");

    	button.addClickListener(event ->
    	  button.setText("You pushed me!"));
    }
}
