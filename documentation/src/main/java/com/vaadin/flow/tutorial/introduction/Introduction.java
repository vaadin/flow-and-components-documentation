package com.vaadin.flow.tutorial.introduction;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("introduction/introduction-overview.asciidoc")
public class Introduction {

    public Introduction() {
        TextField textField = new TextField();
        Span greeting = new Span("Hello stranger"); // Simple HTML inline text

        textField.addValueChangeListener(event ->
                greeting.setText("Hello " + event.getValue()));

        VerticalLayout layout = new VerticalLayout(textField, greeting);
    }

    @Tag("my-label")
    public class MyLabel extends Component {
        public void setText(String text) {
            getElement().setText(text);
        }

        public String getText() {
            return getElement().getText();
        }
    }

    @Tag("game-card")
    @JsModule("./game-card.js")
    public class GameCard extends Component {

    }

    public interface MyModel extends TemplateModel {
        void setColorCode(String color);
    }

    public class MyTemplate extends PolymerTemplate<MyModel> {

        private @Id("textField") TextField textField;
        private @Id("greeting") Label greeting;

        public MyTemplate() {
            // Setting things up in the component's constructor
            textField.addValueChangeListener(event ->
                    greeting.setText("Hello " + event.getValue()));
        }

        // Instance method in the component published to the client
        @EventHandler private void updateFavoriteColor(
                @EventData("event.target.value") String color) {
            getModel().setColorCode(color);
        }
    }

    // register the component to url /company and show it inside the main layout
    @Route(value="company", layout=MainLayout.class)
    @Tag("div")
    public class CompanyComponent extends Component {
    }

    public class MainLayout extends Div implements RouterLayout {
    }
}
