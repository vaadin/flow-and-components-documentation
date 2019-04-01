package com.vaadin.flow.tutorial.embeddingflowapplications;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.WebComponentExporter;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.webcomponent.WebComponent;
import com.vaadin.flow.component.webcomponent.WebComponentDefinition;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("embedding-flow-applications/tutorial-webcomponent-push.asciidoc")
@Tag("push-component")
@Push
public class PushComponentExporter implements WebComponentExporter<Div> {

    @Override
    public void define(WebComponentDefinition<Div> definition) {
    }
    
    @Override
    public void configure(WebComponent<Div> webComponent, Div component) {
    }

}
