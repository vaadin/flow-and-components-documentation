/*
 * Copyright 2000-2020 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.tutorial.databinding;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridDataView;
import com.vaadin.flow.component.grid.dataview.GridLazyDataView;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.select.data.SelectListDataView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.tutorial.annotations.CodeFor;
import com.vaadin.flow.tutorial.databinding.Person.Department;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@CodeFor("binding-data/tutorial-flow-data-provider.asciidoc")
public class DataProviders {

    public static class PersonSort {

    }

    public enum Status {
        OK, ERROR;

        public String getLabel() {
            return "";
        }
    }

    public interface PersonService {
        List<Person> fetch(int offset, int limit,
                           Optional<Predicate<Person>> predicate);

        int getCount(Optional<Predicate<Person>> predicate);

        List<Person> fetchPersons(int offset, int limit);

        List<Person> fetchPersons(int offset, int limit,
                                  List<PersonSort> sortOrders);

        int getPersonCount();

        // @formatter:off
        PersonSort createSort(
                String propertyName,
                boolean descending);

        List<Person> fetchPersons(
                int offset,
                int limit,
                String namePrefix);
        // @formatter:on

        List<Person> fetchPersons(int offset, int limit, String namePrefix,
                                  Department department);

        // @formatter:off
        int getPersonCount(
                String namePrefix,
                Department department);
        // @formatter:on

        Person save(Person person);

        Person fetchById(int i);
    }

    public interface DepartmentService {
        List<Department> fetch(int offset, int limit,
                               String filterText);

        int getCount(String filterText);
    }

    public void combobox() {
        ComboBox<Status> comboBox = new ComboBox<>();
        comboBox.setItemLabelGenerator(Status::getLabel);

        // Sets items as a collection
        comboBox.setItems(EnumSet.allOf(Status.class));

        List<Status> itemsToShow = null;

        // @formatter:off
        /*
        comboBox.setItems(
                (itemCaption, filterText) -> itemCaption.startsWith(filterText),
                itemsToShow);
        */
        // @formatter:on
    }

    public void grid() {
        Grid<Person> grid = new Grid<>();
        grid.addColumn(Person::getName).setHeader("Name");
        grid.addColumn(Person::getYearOfBirth)
                .setHeader("Year of birth");

        // Sets items using varargs
        // @formatter:off
        grid.setItems(
                new Person("George Washington", 1732),
                new Person("John Adams", 1735),
                new Person("Thomas Jefferson", 1743),
                new Person("James Madison", 1751)
        );
        // @formatter:on
        
        PersonRepository personRepository = repo;
        
        // Pass all Person objects to a grid from a Spring Data repository object
        grid.setItems(personRepository.findAll());

        grid.addColumn(Person::getName)
                .setHeader("Name")
                // Override default natural sorting
                .setComparator(Comparator.comparing(person ->
                        person.getName().toLowerCase()));
    }

   public void beanGrid() {
        Grid<Person> grid = new Grid<>(Person.class);
        grid.setColumns("name", "email", "title");
    }


    

    public void lazyDataBindingToGrid(PersonRepository repository) {
    	
    	Grid<Person> grid = new Grid<>();
    	
        grid.setItems(query -> {
            return repository.findAll( // <1>
                    PageRequest.of(query.getPage(), // <2>
                            query.getPageSize()) // <3>
            ).stream(); // <4>
        });
    	
        grid.setItems(query -> { // <1>
            return getPersonService() // <2>
                    .fetchPersons(query.getOffset(), query.getLimit()) // <3>
                    .stream(); // <4>
        });
        
        
        grid.setSortableColumns("name", "email");
        grid.addColumn(person -> person.getTitle())
                .setHeader("title")
                .setSortable(true);
        
        GridLazyDataView<Person> dataView = grid.setItems(query -> { // <1>
            return getPersonService()
                    .fetchPersons(query.getOffset(), query.getLimit())
                    .stream();
        });

        dataView.setItemCountEstimate(1000); // <2>
        dataView.setItemCountEstimateIncrease(500); // <3>
        
        dataView.setItemCountCallback(q -> {
            return getPersonService().getPersonCount(); 
        });
        
    }
    
    TextField filterTextField = new TextField("Filter by name");
    Grid<Person> grid;
    PersonRepository repo;
    
    public void bindWithSorting() {
        Grid<Person> grid = new Grid<>(Person.class);
        grid.setSortableColumns("name", "email"); // <1>
        grid.addColumn(person -> person.getTitle())
        	.setHeader("Title")
        	.setKey("title").setSortable(true); // <2>
        grid.setItems(
            q -> {
                Sort springSort = toSpringDataSort(q.getSortOrders()); // <3>
                return repo.findAll(
                		PageRequest.of(
                				q.getPage(), 
                				q.getPageSize(), 
                				springSort // <4>
                )).stream();
        });
    }
    
    /**
     * A method to convert given Vaadin sort hints to Spring Data specific sort 
     * instructions.
     * 
     * @param vaadinSortOrders a list of Vaadin QuerySortOrders to convert to 
     * @return the Sort object for Spring Data repositories
     */
    public static Sort toSpringDataSort(List<QuerySortOrder> vaadinSortOrders) {
        return Sort.by(
                vaadinSortOrders.stream()
                        .map(so -> 
                                so.getDirection() == SortDirection.ASCENDING ? 
                                        Sort.Order.asc(so.getSorted()) : // <5>
                                        Sort.Order.desc(so.getSorted())
                        )
                        .collect(Collectors.toList())
        );
    }
    
    public void initFiltering() {
        filterTextField.setValueChangeMode(ValueChangeMode.LAZY); // <1>
        filterTextField.addValueChangeListener(e -> listPersonsFilteredByName(e.getValue())); // <2>
        
    }
    
    private void listPersonsFilteredByName(String filterString) {
            String likeFilter = "%" + filterString + "%";// <3>
            grid.setItems(q -> repo
                    .findByNameLikeIgnoreCase(
                            likeFilter, // <4>
                            PageRequest.of(q.getPage(), q.getPageSize()))
                    .stream());
    }
    
    private void refreshItem() {
        Person person = new Person();
        person.setName("Jorma");
        person.setEmail("old@gmail.com");

        GridListDataView<Person> gridDataView = grid.setItems(person);

        Button modify = new Button("Modify data", e -> {
            person.setEmail("new@gmail.com");
            // The component shows the old email until notified of changes
            gridDataView.refreshItem(person);
        });
    }
    
    private void lazyBindingToComboBox() {
        ComboBox<Person> cb = new ComboBox<>();
        cb.setDataProvider((String filter, int offset, int limit) -> {
            return repo.findByNameLikeIgnoreCase(
                    "%" + filter + "%", // <1>
                    PageRequest.of(offset / limit, limit)
            ).stream();
        }, filter -> {
            return (int) repo.countByNameLikeIgnoreCase("%" + filter + "%"); // <2>
        });
    }
    
    private void exportToCsvFile(Grid<Person> grid) 
            throws FileNotFoundException, IOException {
        GridDataView<Person> dataView = grid.getGenericDataView(); // <1>
        FileOutputStream fout = new FileOutputStream(new File("/tmp/export.csv"));
        
        dataView.getItems().forEach(person -> {
            try {
                fout.write((person.getFullName() + ", " + person.getEmail() +"\n").getBytes());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        fout.close();
    }
    
    private void mutationMethodsInListDataView() {

        ArrayList<String> items = new ArrayList<>(Arrays.asList("foo", "bar"));
        
        Select<String> select = new Select<>();
        SelectListDataView<String> dataView = select.setItems(items);

        TextField newItemField = new TextField("Add new item");
        Button addNewItem = new Button("Add", e-> {
                dataView.addItem(newItemField.getValue());
        });
        Button remove = new Button("Remove selected", e-> {
                dataView.removeItem(select.getValue());
        });

        dataView.addItemCountChangeListener(e -> {
                Notification.show(" " + e.getItemCount() + " items available");
        });

    	
    }
    
    

    private PersonService getPersonService() {
        return null;
    }

    public class DepartmentServiceImpl implements DepartmentService {
        List<Department> departments = new ArrayList<>();

        public DepartmentServiceImpl() {
        }

        @Override
        public List<Department> fetch(int offset, int limit, String filterText) {
            List<Department> result = new ArrayList<>();
            return result;
        }

        @Override
        public int getCount(String filterText) {
            int counter = 0;
            return counter;
        }
    }

    public class EmployeeFilter {

        private String filterText;
        private Department department;

        public String getFilterText() {
            return filterText;
        }

        public void setFilterText(String filterText) {
            this.filterText = filterText;
        }

        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        public EmployeeFilter(String filterText,
                              Department department) {

            this.filterText = filterText;
            this.department = department;
        }

    }

    public interface EmployeeService {
        List<Employee> fetch(int offset, int limit,
                             EmployeeFilter filter);

        int getCount(EmployeeFilter filter);
    }


    public class Employee {
        String name;
        int yearOfBirth;
        Department department;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getYearOfBirth() {
            return yearOfBirth;
        }

        public void setYearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
        }


        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        @Override
        public String toString() {
            return name;
        }

        public Employee(String name, int yearOfBirth, Department department) {

            this.name = name;
            this.yearOfBirth = yearOfBirth;
            this.department = department;
        }


    }

    public class EmployeeServiceImpl implements EmployeeService {

        List<Employee> employees = new ArrayList<>();

        public EmployeeServiceImpl() {
        }

        @Override
        public List<Employee> fetch(int offset, int limit, EmployeeFilter filter) {
            List<Employee> searchList = new ArrayList<>();

            if (filter != null && (filter.getDepartment() != null || filter.getFilterText() != null)) {
                for (Employee employee : employees) {
                    if ((filter.getFilterText() == null || employee.getName().contains(filter.getFilterText()))
                            && Objects.equals(employee.getDepartment(), filter.getDepartment())) {
                        searchList.add(employee);
                    }
                }
            } else {
                searchList = employees;
            }

            List<Employee> result = new ArrayList<>();
            int count = 0;
            for (int i = offset; i < offset + limit && i < searchList.size(); i++) {
                result.add(searchList.get(i));
            }

            return result;
        }

        @Override
        public int getCount(EmployeeFilter filter) {
            int counter = 0;

            if (filter == null && (filter.getFilterText() == null || filter.getDepartment() == null)) {
                return employees.size();
            }

            for (Employee employee : employees) {
                if (employee.getName().contains(filter.getFilterText())
                        && Objects.equals(employee.getDepartment(), filter.getDepartment())) {
                    counter++;
                }
            }
            return counter;
        }

    }
}
