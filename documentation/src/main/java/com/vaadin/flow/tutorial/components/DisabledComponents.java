/*
 * Copyright 2000-2017 Vaadin Ltd.
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
package com.vaadin.flow.tutorial.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.DisabledUpdateMode;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.PropertyChangeEvent;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("components/tutorial-disabled-components.asciidoc")
public class DisabledComponents extends Component {

    @Tag("registration-form")
    @HtmlImport("src/registration-form.html")
    public class RegistrationForm extends PolymerTemplate<TemplateModel>
            implements HasEnabled {

        @Id
        private TextField name;

        @Id
        private TextField email;

        @Id
        private Button submit;

        @Id
        private Element enable;

        public RegistrationForm() {
            enable.synchronizeProperty("checked", "checked-changed",
                    DisabledUpdateMode.ALWAYS);
            enable.addPropertyChangeListener("checked", this::handleEnabled);
        }

        @Override
        public void onEnabledStateChange(boolean enabled) {
            name.getElement().setAttribute("disabled", !enabled);
            email.getElement().setAttribute("disabled", !enabled);
            submit.getElement().setAttribute("disabled", !enabled);
        }

        private void handleEnabled(PropertyChangeEvent event) {
            setEnabled((Boolean) event.getValue());
        }

        @EventHandler
        private void register() {
            String userName = name.getValue();
            String userEmail = name.getValue();
            System.out.println("Register user with name='" + userName
                    + "' and email='" + userEmail + "'");
        }
    }

    @Synchronize(property = "prop", value = "prop-changed", allowUpdates = DisabledUpdateMode.ALWAYS)
    public String getProp() {
        return getElement().getProperty("prop");
    }
}
