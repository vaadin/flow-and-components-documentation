package com.vaadin.flow.tutorial.element;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("element-api/client-server-rpc.asciidoc")
public class RPC extends Component {
    public RPC() {
        getElement().callFunction("remoteProcedure","Hello","World",1.0);
        getElement().executeJavaScript("$0.remoteProcedure($1,$2,$3);",getElement(),"Hello","World",1.0);
    }

    public void clearSelection() {
        getElement().callFunction("clearSelection");
    }

    public void setExpanded(Component component) {
        getElement().callFunction("expand", component.getElement());
    }

    public void complete() {
        // Previous Vaadin Platform versions
        Page page = UI.getCurrent().getPage();
        page.executeJavaScript("$0.complete()", this);

        // Vaadin 12
        getElement().executeJavaScript("$0.complete()", this);
    }
}
