package com.vaadin.flow.tutorial.portletsupport;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.portal.VaadinPortlet;
import com.vaadin.flow.portal.handler.PortletEvent;
import com.vaadin.flow.portal.handler.PortletModeEvent;
import com.vaadin.flow.portal.handler.PortletView;
import com.vaadin.flow.portal.handler.PortletViewContext;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("portlet-support/portlet-demo-01-address-book.asciidoc")
public class DemoAddressBook {
    public class ContactListView extends VerticalLayout implements PortletView {
        private PortletViewContext portletViewContext;
        private Grid<Contact> grid = new Grid<>(Contact.class);

        public ContactListView() {
            // ... other initialization ...

            Grid<Contact> grid = new Grid<>(Contact.class);
            // ... other grid configuration ...
            grid.addItemClickListener(this::fireSelectionEvent);
        }

        @Override
        public void onPortletViewContextInit(PortletViewContext context) {
            context.addEventChangeListener("contact-updated",
                    this::onContactUpdated);
            context.addWindowStateChangeListener(
                    event -> handleWindowStateChanged(event.getWindowState()));
            // save context for sending events
            portletViewContext = context;
        }

        private void fireSelectionEvent(ItemClickEvent<Contact> contactItemClickEvent) {
            // get contact id
            Integer contactId = contactItemClickEvent.getItem().getId();

            // save the id into a string-to-string map
            Map<String, String> param = Collections.singletonMap(
                    "contactId", contactId.toString());

            // send the event with name "contact-selected"
            portletViewContext.fireEvent("contact-selected", param);
        }

        private void handleWindowStateChanged(WindowState windowState) {
            if (WindowState.MAXIMIZED.equals(windowState)) {
                grid.setColumns("firstName", "lastName", "phoneNumber", "email",
                        "birthDate");
                grid.setMinWidth("700px");
            } else if (WindowState.NORMAL.equals(windowState)) {
                grid.setColumns("firstName", "lastName", "phoneNumber");
                grid.setMinWidth("450px");
            }
        }

        private void onContactUpdated(PortletEvent event) {
            int contactId = Integer
                    .parseInt(event.getParameters().get("contactId")[0]);
            Optional<Contact> contact = ContactService.getInstance()
                    .findById(contactId);
            contact.ifPresent(value -> grid.getDataProvider().refreshItem(value));
        }
    }

    public class ContactFormView extends VerticalLayout implements PortletView {
        private static final String ACTION_EDIT = "Edit";
        private static final String ACTION_SAVE = "Save";

        private PortletViewContext portletViewContext;

        private Button action;
        private Binder<Contact> binder;
        private Contact contact;
        private Image image;
        // ... other components

        @Override
        public void onPortletViewContextInit(PortletViewContext context) {
            context.addEventChangeListener("contact-selected",
                    this::onContactSelected);
            context.addPortletModeChangeListener(this::handlePortletModeChange);
            // save context for sending events
            this.portletViewContext = context;

            // ... setup other form components

            action = new Button(PortletMode.EDIT
                    .equals(context.getPortletMode()) ?
                    ACTION_SAVE : ACTION_EDIT, event -> {
                if (PortletMode.EDIT.equals(portletViewContext.getPortletMode())) {
                    // save bean, switch to VIEW mode, send an event
                    save();
                } else {
                    // switch portlet to EDIT mode
                    context.setPortletMode(PortletMode.EDIT);
                }
            });

            add(action);

            // ... setup rest of the form components
        }

        // called when the portlet mode changes
        // FormPortlet supports two modes: 'view' and 'edit'
        private void handlePortletModeChange(PortletModeEvent event) {
            // set fields to read-only mode when portlet mode is 'view'
            final boolean isViewMode = event.isViewMode();
            binder.setReadOnly(isViewMode);

            // set the button's text based on the portlet mode
            if (isViewMode) {
                action.setText(ACTION_EDIT);
            } else {
                action.setText(ACTION_SAVE);
            }
        }

        // handles "contact-selected" event from PortletListView.
        // we check that the event name is correct and that the contact exists.
        // then we display the contact information on the form.
        private void onContactSelected(PortletEvent event) {
            int contactId = Integer.parseInt(event.getParameters().get("contactId")[0]);
            Optional<Contact> contact = ContactService.getInstance().findById(contactId);
            if (contact.isPresent()) {
                this.contact = contact.get();
                binder.readBean(this.contact);
                image.setSrc(this.contact.getImage().toString());
            } else {
                clear();
            }
        }

        private void save() {
            if (contact != null) {
                binder.writeBeanIfValid(contact);
                ContactService.getInstance().save(contact);
                portletViewContext.fireEvent("contact-updated", Collections.singletonMap(
                        "contactId", contact.getId().toString()));
            }

            portletViewContext.setPortletMode(PortletMode.VIEW);
        }
    }

    private void clear() {

    }

    private class Contact {
        private Integer id;
        private String firstName;
        private String image;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    private static class ContactService {
        public static ContactService getInstance() {
            return null;
        }

        public Optional<Contact> findById(int contactId) {
            return null;
        }

        public void save(Contact contact) {

        }
    }

    private static class FormPortlet {
        public static VaadinPortlet<Component> getCurrent() {
            return null;
        }
    }
}
