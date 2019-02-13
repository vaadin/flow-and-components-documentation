package com.vaadin.flow.tutorial.advanced;

import javax.servlet.annotation.WebServlet;

import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("advanced/tutorial-sswc.asciidoc")
@WebServlet(urlPatterns = { "/vaadin/*", "/frontend/*" })
public class WebComponentVaadinServlet extends VaadinServlet {
}
