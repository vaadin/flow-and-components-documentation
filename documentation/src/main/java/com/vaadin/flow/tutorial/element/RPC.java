package com.vaadin.flow.tutorial.element;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("element-api/client-server-rpc.asciidoc")
public class RPC extends Component {
    public RPC() {
        getElement().callFunction("remoteProcedure","Hello","World",1.0);
        getElement().executeJavaScript("$0.remoteProcedure($1,$2,$3);",getElement(),"Hello","World",1.0);
    }
}
