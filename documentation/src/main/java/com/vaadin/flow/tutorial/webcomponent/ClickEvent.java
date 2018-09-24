package com.vaadin.flow.tutorial.webcomponent;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

// code for web-components/creating-java-api-for-a-web-component.asciidoc
@DomEvent("click")
// @formatter:off
public class ClickEvent extends ComponentEvent<PaperSlider> {

    private int x,y;

    public ClickEvent(PaperSlider source, boolean fromClient, @EventData("event.offsetX") int x, @EventData("event.offsetY") int y) {
        super(source, fromClient);
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
