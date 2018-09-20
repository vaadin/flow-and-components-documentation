package com.vaadin.starter.skeleton;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

// code for introduction/tutorial-get-started-second-part.asciidoc
public class TutorialGetStartedSecondPart {


    @Route("")
    public class MainView extends VerticalLayout {

        private CustomerService service = CustomerService.getInstance();
        private Grid<Customer> grid = new Grid<>();


        public MainView() {
            grid.setSizeFull();

            grid.addColumn(Customer::getFirstName).setHeader("First name");
            grid.addColumn(Customer::getLastName).setHeader("Last name");
            grid.addColumn(Customer::getStatus).setHeader("Status");

            add(grid);
            setHeight("100vh");
            updateList();

        }

        public void updateList() {
            grid.setItems(service.findAll());
        }
    }
}

