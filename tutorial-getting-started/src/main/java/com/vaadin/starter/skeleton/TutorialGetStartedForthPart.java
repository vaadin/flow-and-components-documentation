package com.vaadin.starter.skeleton;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

// code for introduction/tutorial-get-started-forth-part.asciidoc
public class TutorialGetStartedForthPart {

    public class CustomerForm extends FormLayout {
        private TextField firstName = new TextField("First name");
        private TextField lastName = new TextField("Last name");
        private ComboBox<CustomerStatus> status = new ComboBox<>("Status");
        private CustomerService service = CustomerService.getInstance();
        private Customer customer;
        private MainView mainView;
        private Binder<Customer> binder = new Binder<>(Customer.class);
        private Button save = new Button("Save");
        private Button delete = new Button("Delete");

        public CustomerForm(MainView mainView) {
            this.mainView = mainView;
            add(firstName, lastName, status);
            status.setItems(CustomerStatus.values());
            binder.bindInstanceFields(this);
            HorizontalLayout buttons = new HorizontalLayout(save, delete);
            add(firstName, lastName, status, buttons);
            save.getElement().setAttribute("theme", "primary");
            setCustomer(null);
            save.addClickListener(e -> this.save());
            delete.addClickListener(e -> this.delete());
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
            mainView.updateList();
            setCustomer(null);
        }

        private void save() {
            service.save(customer);
            mainView.updateList();
            setCustomer(null);
        }
    }

    /**
     * The main view contains a button and a click listener.
     */
    @Route("")
    public class MainView extends VerticalLayout {
        private CustomerService service = CustomerService.getInstance();
        private Grid<Customer> grid = new Grid<>();
        private TextField filterText = new TextField();
        private CustomerForm form = new CustomerForm(this);

        public MainView() {
            filterText.setPlaceholder("Filter by name...");
            filterText.setValueChangeMode(ValueChangeMode.EAGER);
            filterText.addValueChangeListener(e -> updateList());
            Button clearFilterTextBtn = new Button(
                    new Icon(VaadinIcon.CLOSE_CIRCLE));

            clearFilterTextBtn.addClickListener(e -> filterText.clear());

            HorizontalLayout filtering = new HorizontalLayout(filterText,
                    clearFilterTextBtn);
            Button addCustomerBtn = new Button("Add new customer");
            addCustomerBtn.addClickListener(e -> {
                grid.asSingleSelect().clear();
                form.setCustomer(new Customer());
            });
            add(filtering, grid);

            HorizontalLayout toolbar = new HorizontalLayout(filtering,
                    addCustomerBtn);


            grid.setSizeFull();

            grid.addColumn(Customer::getFirstName).setHeader("First name");
            grid.addColumn(Customer::getLastName).setHeader("Last name");
            grid.addColumn(Customer::getStatus).setHeader("Status");

            HorizontalLayout main = new HorizontalLayout(grid, form);
            main.setAlignItems(Alignment.START);
            main.setSizeFull();

            add(toolbar, main);
            add(filtering, main);
            setHeight("100vh");
            updateList();

            grid.asSingleSelect().addValueChangeListener(event -> {
                form.setCustomer(event.getValue());
            });

        }

        public void updateList() {
            /**
             * Note that filterText.getValue() might return null; in this case, the backend
             * takes care of it for us
             */
            grid.setItems(service.findAll(filterText.getValue()));
        }

    }

}
