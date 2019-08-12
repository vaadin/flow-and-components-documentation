/*
 * Copyright 2000-2018 Vaadin Ltd.
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
package com.vaadin.flow.tutorial.ccdm;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.vaadin.flow.server.ClientIndexBootstrapListener;
import com.vaadin.flow.server.ClientIndexBootstrapPage;
import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("ccdm/client-side-bootstrapping.asciidoc")
public class ClientSideBootstrapPage {
    public class CustomBootstrapPageListener implements
            ClientIndexBootstrapListener {

        @Override
        public void modifyBootstrapPage(
                ClientIndexBootstrapPage clientIndexBootstrapPage) {
            Document document = clientIndexBootstrapPage.getDocument();

            Element head = document.head();

            head.appendChild(createMeta(document, "og:title", "The Rock"));
            head.appendChild(createMeta(document, "og:type", "video.movie"));
            head.appendChild(createMeta(document, "og:url",
                    "http://www.imdb.com/title/tt0117500/"));
            head.appendChild(createMeta(document, "og:image",
                    "http://ia.media-imdb.com/images/rock.jpg"));
        }

        private Element createMeta(Document document, String property,
                String content) {
            Element meta = document.createElement("meta");
            meta.attr("property", property);
            meta.attr("content", content);
            return meta;
        }
    }
}
