package com.vaadin.flow.tutorial.pwa;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

// code for pwa/tutorial-pwa-pwa-with-flow.asciidoc
@PWA(name = "My Progressive Web Application", shortName = "MyPWA")
@Route("")
public class MyPWA extends Div {
    public MyPWA(){
        setText("Welcome to my PWA");
    }
}
