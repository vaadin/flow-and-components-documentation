package com.vaadin.flow.tutorial.migration;

import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.VaadinServletService;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("v14-migration/v14-migration-guide.asciidoc")
/*
@HtmlImport("frontend://my-templates/top-bar.html")
 */
@JsModule("./my-templates/top-bar.js")
public class V14Migration {
    private void addImageOld() {
        String resolvedImage = VaadinServletService.getCurrent()
                .resolveResource("frontend://img/logo.png");

        Image image = new Image(resolvedImage, "");
    }

    private void addImageNew() {
        String resolvedImage = VaadinServletService.getCurrent()
                .resolveResource("img/logo.png");

        Image image = new Image(resolvedImage, "");
    }
}
