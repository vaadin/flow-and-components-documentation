package com.vaadin.flow.tutorial.advanced;

import com.vaadin.flow.component.page.LoadingIndicatorConfiguration;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.UIInitEvent;
import com.vaadin.flow.server.UIInitListener;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("advanced/tutorial-loading-indicator.asciidoc")
public class LoadingIndicatorConfig
        implements VaadinServiceInitListener, UIInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        VaadinService service = event.getSource();
        service.addUIInitListener(this);
    }

    @Override
    public void uiInit(UIInitEvent event) {
        LoadingIndicatorConfiguration conf = event.getUI()
                .getLoadingIndicatorConfiguration();

        /*
         * Delay for showing the indicator and setting the 'first' class name.
         */
        conf.setFirstDelay(300); // 300ms is the default

        /* Delay for setting the 'second' class name */
        conf.setSecondDelay(1500); // 1500ms is the default

        /* Delay for setting the 'third' class name */
        conf.setThirdDelay(5000); // 5000ms is the default
    }
}
