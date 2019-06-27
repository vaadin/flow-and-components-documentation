// tutorial::importing-dependencies/tutorial-include-css.asciidoc

import '@polymer/polymer/lib/elements/custom-style.js';

const documentContainer = document.createElement('template');

documentContainer.innerHTML = `<custom-style>
      <style>
        html {
          color: red;
        }
      </style>
    </custom-style>`;

document.head.appendChild(documentContainer.content);
