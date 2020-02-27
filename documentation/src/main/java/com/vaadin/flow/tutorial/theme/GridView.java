package com.vaadin.flow.tutorial.theme;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.tutorial.theme.Person;

@CodeFor("theme/theming-crash-course.asciidoc")
@Route("grid")
@CssImport(value = "./styles/vaadin-grid-styles.css", themeFor = "vaadin-grid")
public class GridView extends VerticalLayout {

    public GridView(){
        Label description=new Label("The first row of the grid below doesn't react to user actions.");
        add(description);

        Grid<Person> grid = new Grid<>();
        grid.addColumn(Person::getFirstName).setHeader("First Name");
        grid.addColumn(Person::getLastName).setHeader("Last Name");
        grid.addColumn(Person::getAge).setHeader("Age");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setThemeName("grid-no-reaction");


        grid.setItemDetailsRenderer(
                new ComponentRenderer<>(s -> {
                    VerticalLayout layout = new VerticalLayout();
                    layout.setSizeFull();
                    Label label = new Label(s.getFirstName() + " " + s.getLastName());
                    layout.add(label);
                    return layout;
                }));

        add(grid);
        grid.setItems(new Person(24,"Aaron","Tiili"),new Person(28,"Anna","Keski"));
    }
}
