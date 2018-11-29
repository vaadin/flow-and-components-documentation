package com.vaadin.flow.tutorial.creatingcomponents;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("creating-components/tutorial-extending-component.asciidoc")
public class NumericField extends TextField {

    private Button substractBtn;
    private Button addBtn;

    private static final int DEFAULT_VALUE = 0;
    private static final int DEFAULT_INCREMENT = 1;

    private Integer numericValue;
    private Integer incrementValue;
    private Integer decrementValue;

    public NumericField() {
        this(DEFAULT_VALUE, DEFAULT_INCREMENT, -DEFAULT_INCREMENT);
    }

    public NumericField(Integer value, Integer incrementValue, Integer decrementValue) {
        setNumericValue(value);
        this.incrementValue = incrementValue;
        this.decrementValue = decrementValue;

        setPattern("-?[0-9]*");
        setPreventInvalidInput(true);

        addChangeListener(event -> {
            String text = event.getSource().getValue();
            if (StringUtils.isNumeric(text)) {
                setNumericValue(Integer.parseInt(text));
            } else {
                setNumericValue(DEFAULT_VALUE);
            }
        });

        substractBtn = new Button("-", event -> {
            setNumericValue(numericValue + decrementValue);
        });

        addBtn = new Button("+", event -> {
            setNumericValue(numericValue + incrementValue);
        });

        styleBtns();

        addToPrefix(substractBtn);
        addToSuffix(addBtn);
    }

    private void styleBtns() {
        // Note: The same as addThemeVariants
        substractBtn.getElement().setAttribute("theme", "icon");
        addBtn.getElement().setAttribute("theme", "icon");
    }

    public void setNumericValue(Integer value) {
        numericValue = value;
        setValue(value + "");
    }

    // ...

    public Integer getNumericValue() {
        return numericValue;
    }

    public Integer getIncrementValue() {
        return incrementValue;
    }

    public void setIncrementValue(Integer incrementValue) {
        this.incrementValue = incrementValue;
    }

    public Integer getDecrementValue() {
        return decrementValue;
    }

    public void setDecrementValue(Integer decrementValue) {
        this.decrementValue = decrementValue;
    }
}
