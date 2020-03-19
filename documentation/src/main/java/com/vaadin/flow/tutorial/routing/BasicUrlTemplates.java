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
package com.vaadin.flow.tutorial.routing;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.UrlParameters;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("routing/tutorial-router-url-templates.asciidoc")
public class BasicUrlTemplates {

    @Route(value = "")
    @RouteAlias(value = "forum/:categoryID", absolute = true)
    @RoutePrefix("forum/category/:categoryID")
    public class ForumView extends Div implements RouterLayout,
            BeforeEnterObserver {

        private String categoryID;

        private String threadID;

        @Override
        public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
            final UrlParameters urlParameters = beforeEnterEvent.getUrlParameters();

            categoryID = urlParameters.get("categoryID").get();
            threadID = urlParameters.get("threadID").get();
        }
    }

    @Route(value = "threadID/:threadID", layout = ForumView.class)
    @RouteAlias(value = "threadID/:threadID/comment", layout = ForumView.class)
    @RouteAlias(value = "forum/:threadID", layout = ForumView.class, absolute = true)
    public class ForumThread extends Div implements BeforeEnterObserver {

        private String threadID;

        @Override
        public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
            threadID = beforeEnterEvent.getUrlParameters().get("threadID").get();

            if ("comment".equals(getLastSegment(beforeEnterEvent))) {
                CommentDialog dialog = new CommentDialog();
                dialog.open();
            }
        }
    }

    public class CommentDialog extends Dialog {

        private TextArea commentTextArea = new TextArea();

        public CommentDialog() {
            add(commentTextArea);
            Button sendButton = new Button("Send");
            sendButton.addClickListener(event -> submit());
        }

        private void submit() {
            // Logic to persist the comment.
        }
    }

    private String getLastSegment(BeforeEnterEvent beforeEnterEvent) {
        final List<String> segments = beforeEnterEvent.getLocation().getSegments();
        return segments.get(segments.size() - 1);
    }

}
