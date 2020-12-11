package com.vaadin.flow.tutorial.pwa;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("pwa/tutorial-pwa-offline.asciidoc")
public class MyPWA4 {

    @PWA(name = "My Progressive Web Application",
         shortName = "MyPWA",
         offlinePath = "offline.html")
    public class AppShell implements AppShellConfigurator {
    }
}
