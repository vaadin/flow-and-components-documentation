package com.vaadin.flow.tutorial.portletsupport;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.portal.VaadinPortlet;
import com.vaadin.flow.portal.handler.PortletModeEvent;
import com.vaadin.flow.portal.handler.PortletModeHandler;
import com.vaadin.flow.portal.handler.VaadinPortletEventContext;
import com.vaadin.flow.portal.handler.VaadinPortletEventView;
import com.vaadin.flow.portal.handler.WindowStateEvent;
import com.vaadin.flow.portal.handler.WindowStateHandler;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("portlet-support/handling-portlet-phases.asciidoc")
public class HandlingPortletPhases {
    public class PortletModeListenerDemo {
        public PortletModeListenerDemo() {
            // for the current portlet that is processing requests
            VaadinPortlet.getCurrent().setPortletMode(PortletMode.EDIT);
        }

        public class MyPortletView extends Div implements VaadinPortletEventView {
            @Override
            public void onPortletEventContextInit(VaadinPortletEventContext context) {
                context.addPortletModeChangeListener(event -> showHelpText(event.isHelpMode()));
            }
        }
    }

    public class WindowStateListenerDemo {
        public WindowStateListenerDemo() {
            // for the current portlet that is processing requests
            VaadinPortlet.getCurrent().setWindowState(WindowState.MAXIMIZED);
        }

        public class MyPortletView extends Div implements VaadinPortletEventView {
            @Override
            public void onPortletEventContextInit(VaadinPortletEventContext context) {
                context.addWindowStateChangeListener(event -> showDetailsField(event.isMaximized()));
            }
        }
    }

    public class MyPortlet extends VaadinPortlet<MyView> {

    }

    public void showHelpText(boolean helpMode) {

    }

    public void showDetailsField(boolean maximized) {

    }

    public class MyView extends Div implements VaadinPortletEventView {

        private Paragraph stateInformation;

        public MyView() {
            stateInformation = new Paragraph("Use the portlet controls or the "
                    + "buttons below to change the portlet's state!");

            Button maximizeButton = new Button("Maximize", event -> VaadinPortlet
                    .getCurrent().setWindowState(WindowState.MAXIMIZED));

            Button helpButton = new Button("Show help", event -> VaadinPortlet
                    .getCurrent().setPortletMode(PortletMode.HELP));

            add(stateInformation, maximizeButton, helpButton);
        }

        @Override
        public void onPortletEventContextInit(VaadinPortletEventContext context) {
            context.addWindowStateChangeListener(event -> stateInformation
                    .setText("Window state changed to " + event.getWindowState()));
            context.addPortletModeChangeListener(event -> stateInformation
                    .setText("Portlet mode changed to " + event.getPortletMode()));
        }
    }

    public class HandlersDemo {
        public class MyView extends Div
                implements PortletModeHandler, WindowStateHandler {

            private Paragraph stateInformation = new Paragraph();

            public MyView() {
                add(stateInformation);
            }

            @Override
            public void portletModeChange(PortletModeEvent event) {
                stateInformation
                        .setText("Portlet mode changed to " + event.getPortletMode());
            }

            @Override
            public void windowStateChange(WindowStateEvent event) {
                stateInformation
                        .setText("Window state changed to " + event.getWindowState());
            }
        }
    }
}
