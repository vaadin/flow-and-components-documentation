package com.vaadin.flow.tutorial.portletsupport;

import java.util.Collections;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.portal.handler.VaadinPortletEventContext;
import com.vaadin.flow.portal.handler.VaadinPortletEventView;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("portlet-support/inter-portlet-communication.asciidoc")
public class InterPortletCommunication {
    public class FiringPortletView extends Div implements VaadinPortletEventView {

        private VaadinPortletEventContext portletContext;

        public FiringPortletView() {
            Button button = new Button("Fire event", event -> portletContext
                    .fireEvent("my-ipc-event", Collections.emptyMap()));
        }

        @Override
        public void onPortletEventContextInit(VaadinPortletEventContext context) {
            portletContext = context;
        }
    }

    public class ReceivingPortletView extends Div
            implements VaadinPortletEventView {

        @Override
        public void onPortletEventContextInit(VaadinPortletEventContext context) {
            context.addEventChangeListener("my-ipc-event", event -> Notification
                    .show("Received '" + event.getEventName() + "' event!"));
        }
    }

}
