package com.vaadin.flow.tutorial.pwa;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("pwa/tutorial-pwa-pwa-with-flow.asciidoc")
@PWA(name = "My Progressive Web Application",
        shortName = "MyPWA")
@Route("")
public class MyPWA extends Div {
    public MyPWA(){
        setText("Welcome to my PWA");
    }

    @PWA(name = "My Progressive Web Application",
            shortName = "MyPWA",
            manifestPath = "manifest.json")
    class MyPWA1 {}

    @PWA(name = "My Progressive Web Application",
            shortName = "MyPWA",
            offlineResources = { "styles/offline.css",
                    "js/jquery.js", "img/offline.jpg"})
    class MyPWA2 {}

    @PWA(name = "My Progressive Web Application",
            shortName = "MyPWA",
            iconPath = "img/icons/logo.png")
    class MyPWA3 {}
}