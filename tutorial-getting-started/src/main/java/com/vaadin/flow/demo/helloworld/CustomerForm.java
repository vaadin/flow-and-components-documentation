package com.vaadin.flow.demo.helloworld;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.starter.skeleton.Customer;
import com.vaadin.starter.skeleton.CustomerService;
import com.vaadin.starter.skeleton.CustomerStatus;

@SuppressWarnings("serial")
// code for introduction/tutorial-get-started.asciidoc
public class CustomerForm extends FormLayout {
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private ComboBox<CustomerStatus> status = new ComboBox<>("Status");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private CustomerService service = CustomerService.getInstance();
    private Customer customer;
    private MainView view;

    private Binder<Customer> binder = new Binder<>(Customer.class);

    public CustomerForm(MainView view) {
        this.view = view;

        HorizontalLayout buttons = new HorizontalLayout(save, delete);

        add(firstName, lastName, status, buttons);

        status.setItems(CustomerStatus.values());

        save.getElement().setAttribute("theme", "primary");

        binder.bindInstanceFields(this);

        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());

        setCustomer(null);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        binder.setBean(customer);
        boolean enabled = customer != null;
        save.setEnabled(enabled);
        delete.setEnabled(enabled);
        if (enabled) {
            firstName.focus();
        }
    }

    private void delete() {
        service.delete(customer);
        view.updateList();
        setCustomer(null);
    }

    private void save() {
        service.save(customer);
        view.updateList();
        setCustomer(null);
    }
}
