package com.vaadin.flow.tutorial.clientsideforms;

import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("client-side-forms/appendix-form-images.asciidoc")
public class FormImages {
    /**
     * Contact card with base64-encoded image
     */
    public class Contact {
        // ...

        public String getAvatarBase64() {
            // ...
            return "";
        }

        public void setAvatarBase64(String avatarBase64) {
            // ...
        }
    }

    @Endpoint
    public class ServiceEndpoint {
        // ...

        public void saveContact(Contact contact) {
            // ...
        }
    }
}
