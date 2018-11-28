package com.vaadin.flow.tutorial.creatingcomponents;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("creating-components/tutorial-extending-component.asciidoc")
public class NumericField extends TextField {

    private Button substractBtn;
    private TextField textField;
    private Button addBtn;

    private Double currentValue = 0d;
    private Double incrementValue = 1d;
    private Double decrementValue = -incrementValue;

    public NumericField() {
        initComonents();
    }

    public NumericField(Double currentValue, Double incrementValue, Double decrementValue) {
        this.currentValue = currentValue;
        this.incrementValue = incrementValue;
        this.decrementValue = decrementValue;

        initComonents();
    }

    private void initComonents(){
        substractBtn = new Button("-", event -> {
            setValue(currentValue + decrementValue);
        });

        textField = new TextField();

        addBtn = new Button("+", event -> {
            setValue(currentValue + incrementValue);
        });

        this.addToPrefix(substractBtn);
        this.addToSuffix(addBtn);

        updateShownValue(currentValue);
    }

    // ...

    private void updateShownValue(Double value) {
        textField.setValue(value + "");
    }

    public void setValue(Double value) {
        currentValue = value;
        updateShownValue(value);
    }
}
