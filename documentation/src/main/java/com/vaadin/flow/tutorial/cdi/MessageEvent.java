package com.vaadin.flow.tutorial.cdi;

import com.vaadin.flow.tutorial.annotations.Helper;

@Helper
public class MessageEvent {
    private final String text;

    public MessageEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
