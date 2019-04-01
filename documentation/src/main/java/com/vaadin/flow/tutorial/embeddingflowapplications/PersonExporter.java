package com.vaadin.flow.tutorial.embeddingflowapplications;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.WebComponentExporter;
import com.vaadin.flow.component.webcomponent.PropertyConfiguration;
import com.vaadin.flow.component.webcomponent.WebComponent;
import com.vaadin.flow.component.webcomponent.WebComponentDefinition;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("embedding-flow-applications/tutorial-webcomponent-properties"
        + ".asciidoc")
@Tag("person-display")
public class PersonExporter implements WebComponentExporter<PersonComponent> {

    private PropertyConfiguration<PersonComponent, Boolean> isAdultProperty;

    @Override
    public void define(WebComponentDefinition<PersonComponent> definition) {
        definition.addProperty("name", "John Doe")
                .onChange(PersonComponent::setName);
        definition.addProperty("age", 0).onChange(PersonComponent::setAge);

        isAdultProperty = definition.addProperty("is-adult", false);
    }

    @Override
    public void configure(WebComponent<PersonComponent> webComponent,
            PersonComponent component) {
        component.setAdultAge(18); // initialization

        component.addAgeChangedListener(event -> {
            webComponent.setProperty(isAdultProperty, component.isAdult());
        });

        component.addAgeChangedListener(event -> {
            if (event.getAge() > 65) {
                webComponent.fireEvent("retirement-age-reached");
            }
        });
    }
}
