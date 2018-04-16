package com.vaadin.flow.tutorial.webcomponent.b;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.tutorial.annotations.CodeFor;
import com.vaadin.flow.tutorial.webcomponent.ClickEvent;

@CodeFor("web-components/creating-java-api-for-a-web-component.asciidoc")
//@formatter:off
public class PaperSlider extends Component implements HasValue<PaperSlider, Integer> {
    private static final PropertyDescriptor<Integer, Integer> valueProperty = PropertyDescriptors.propertyWithDefault("value", 0);

    // @formatter:off
    private static final PropertyDescriptor<Boolean, Boolean> pinProperty = PropertyDescriptors.propertyWithDefault("pin", false);

    public void setPin(boolean pin) {
        pinProperty.set(this, pin);
    }

    public boolean isPin() {
        return pinProperty.get(this);
    }

    @Override
    public void setValue(Integer value) {
        valueProperty.set(this, value);
    }

    @Override
    public Integer getValue() {
        return valueProperty.get(this);
    }

    public Registration addClickListener(ComponentEventListener<ClickEvent> listener) {
        return addListener(ClickEvent.class, listener);
    }

    public void increment() {
        getElement().callFunction("increment");
    }

    //@formatter:on
}