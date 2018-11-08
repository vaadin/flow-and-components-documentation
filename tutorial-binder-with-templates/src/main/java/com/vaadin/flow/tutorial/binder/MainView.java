package com.vaadin.flow.tutorial.binder;

import java.util.Optional;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.tutorial.binder.data.User;
import com.vaadin.flow.tutorial.binder.data.UsersRepository;
import com.vaadin.flow.tutorial.binder.ui.FormButtonsBar;
import com.vaadin.flow.tutorial.binder.ui.UserForm;
import com.vaadin.flow.tutorial.binder.ui.UsersGrid;

/**
 * The main view of the application.
 */
@Tag("main-view")
@HtmlImport("src/main-view.html")
@Route("")
public class MainView extends PolymerTemplate<TemplateModel> {


    @Id("user-form")
    private UserForm userForm;

    @Id("users-grid")
    private UsersGrid usersGrid;

    /**
     * Initializes the Main view and the listeners of its components.
     */
    public MainView() {

        // selection listener on the rows of the grid.
        usersGrid.addSelectionListener(selectionEvent -> {
            Optional<User> optionalUser = usersGrid.getSelectedItems().stream().findAny();

            if (optionalUser.isPresent()) {
                userForm.setBean(optionalUser.get());
                setEditionEnabled(true);
            } else {
                userForm.removeBean();
                setEditionEnabled(false);
            }
        });

        initFormListeners();
    }

    /**
     * Initialization of the listeners of the UserForm
     */
    private void initFormListeners() {
        FormButtonsBar formButtonsBar = userForm.getActionButtons();

        // SAVE
        formButtonsBar.addSaveListener(saveEvent -> {
            // it checks that the bind user of the UserForm has a correct format.
            if (!userForm.getBinder().validate().isOk()) {
                return;
            }

            Optional<User> optionalUser = userForm.getBean();

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                user = UsersRepository.save(user);

                usersGrid.refresh(user);
                userForm.setBean(user);
            }
        });

        // CANCEL
        formButtonsBar.addCancelListener(cancelEvent -> {
            usersGrid.deselectAll();
        });

        // DELETE
        formButtonsBar.addDeleteListener(deleteEvent -> {
            Optional<User> optionalUser = usersGrid.getSelectedItems().stream().findAny();

            if (optionalUser.isPresent()) {
                UsersRepository.delete(optionalUser.get());
                usersGrid.refreshAll();
                usersGrid.deselectAll();
            }
        });
    }

    /**
     * Enables or disables the UserForm.
     *
     * @param enabled true or false
     */
    public void setEditionEnabled(boolean enabled) {
        userForm.setEnabled(enabled);
    }
}