import {PolymerElement, html} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-button/vaadin-button.js';
import '@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout.js';

class FormButtonsBarElement extends PolymerElement {
    static get template() {
        return html`
            <style>
                :host {
                    width: 100%;
                }

                #delete {
                    margin-left: auto;
                }
            </style>
            <vaadin-horizontal-layout style="width: 100%;" themes=""
                                      theme="spacing padding">
                <vaadin-button id="save" theme="raised primary">
                    Save
                </vaadin-button>
                <vaadin-button id="cancel" theme="raised">
                    Cancel
                </vaadin-button>
                <vaadin-button id="delete" theme="raised tertiary error">
                    Delete
                </vaadin-button>
            </vaadin-horizontal-layout>`;
    }
    static get is() {
        return 'form-buttons-bar';
    }
}

customElements.define(FormButtonsBarElement.is, FormButtonsBarElement);
