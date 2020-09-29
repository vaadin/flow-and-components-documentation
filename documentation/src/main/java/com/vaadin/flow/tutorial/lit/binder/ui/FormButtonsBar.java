package com.vaadin.flow.tutorial.lit.binder.ui;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("polymer-templates/tutorial-template-and-binder.asciidoc")
/**
 * Java wrapper of the polymer element `form-buttons-bar`
 */
@Tag("form-buttons-bar")
@JsModule("./src/form-buttons-bar.ts")
public class FormButtonsBar extends LitTemplate {

    @Id("save")
    private Button save;
    @Id("cancel")
    private Button cancel;
    @Id("delete")
    private Button delete;

    public void setSaveText(String saveText) {
        save.setText(saveText == null ? "" : saveText);
    }

    public void setCancelText(String cancelText) {
        cancel.setText(cancelText == null ? "" : cancelText);
    }

    public void setDeleteText(String deleteText) {
        delete.setText(deleteText == null ? "" : deleteText);
    }

    public void setSaveDisabled(boolean saveDisabled) {
        save.setEnabled(!saveDisabled);
    }

    public void setCancelDisabled(boolean cancelDisabled) {
        cancel.setEnabled(!cancelDisabled);
    }

    public void setDeleteDisabled(boolean deleteDisabled) {
        delete.setEnabled(!deleteDisabled);
    }

    public static class SaveEvent extends ComponentEvent<FormButtonsBar> {
        public SaveEvent(FormButtonsBar source, boolean fromClient) {
            super(source, fromClient);
        }
    }

    public Registration addSaveListener(
            ComponentEventListener<SaveEvent> listener) {
        return save.addClickListener(
                e -> listener.onComponentEvent(new SaveEvent(this, true)));
    }

    public static class CancelEvent extends ComponentEvent<FormButtonsBar> {
        public CancelEvent(FormButtonsBar source, boolean fromClient) {
            super(source, fromClient);
        }
    }

    public Registration addCancelListener(
            ComponentEventListener<CancelEvent> listener) {
        return cancel.addClickListener(
                e -> listener.onComponentEvent(new CancelEvent(this, true)));
    }

    public static class DeleteEvent extends ComponentEvent<FormButtonsBar> {
        public DeleteEvent(FormButtonsBar source, boolean fromClient) {
            super(source, fromClient);
        }
    }

    public Registration addDeleteListener(
            ComponentEventListener<DeleteEvent> listener) {
        return delete.addClickListener(
                e -> listener.onComponentEvent(new DeleteEvent(this, true)));
    }
}