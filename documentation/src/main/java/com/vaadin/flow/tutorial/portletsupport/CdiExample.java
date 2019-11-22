package com.vaadin.flow.tutorial.portletsupport;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.portal.VaadinPortlet;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("portlet-support/portlet-07-cdi-support.asciidoc")
public class CdiExample {
    // old, normal portlet:
    public class NormalBookListPortlet extends VaadinPortlet<BookListView> {
        // ... contents
    }

    // new, cdi portlet:
    public class CdiBookListVaadinPortlet extends CdiVaadinPortlet<BookListView> {
        // ... contents
    }

    private static class BookListView extends Component {

    }

    // TODO: remove once the cdi support is in
    private static class CdiVaadinPortlet<C extends Component> extends VaadinPortlet<C> {

    }
}
