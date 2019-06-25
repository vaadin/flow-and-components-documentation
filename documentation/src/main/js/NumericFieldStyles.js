// tutorial::creating-components/tutorial-extending-component.asciidoc
const documentContainer = document.createElement('template');

documentContainer.innerHTML =
`<dom-module id="vaadin-numeric-field-theme" theme-for="vaadin-text-field">
    <template>
        <style>
                :host(vaadin-text-field) [part="input-field"] {
                    background-color: #FFFFFF;
                    border: solid 1px #E0E5E8;
                    box-sizing: border-box;
                }

                :host(vaadin-text-field) [part="value"]{
                    --_lumo-text-field-overflow-mask-image: none;
                    text-align:center;
                }

                :host(vaadin-text-field) [part="input-field"] ::slotted(vaadin-button) {
                    background-color: transparent !important;
                }
            </style>
    </template>
</dom-module>`;

document.head.appendChild(documentContainer.content);
