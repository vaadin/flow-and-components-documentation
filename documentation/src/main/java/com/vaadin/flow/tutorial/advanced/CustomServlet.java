package com.vaadin.flow.tutorial.advanced;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@WebServlet(urlPatterns = "/*", name = "myservlet", asyncSupported = true, initParams = {
        @WebInitParam(name = "vaadin.pnpm.enable", value = "true") })
@CodeFor("advanced/tutorial-switch-npm-pnpm.asciidoc")
public class CustomServlet extends VaadinServlet {
}
