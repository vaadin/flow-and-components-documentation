import {PolymerElement, html} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-form-layout/vaadin-form-layout.js';
import '@vaadin/vaadin-text-field/vaadin-text-field.js';
import '@vaadin/vaadin-text-field/vaadin-text-area.js';
import '@vaadin/vaadin-checkbox/vaadin-checkbox.js';

import './form-buttons-bar.js';

class UserForm extends PolymerElement {
    static get template() {
        return html`
            <style>
                :host {
                    display: flex;
                    flex-direction: column;
                    flex: auto;
                    height: 100%;
                }

                vaadin-form-layout {
                    flex: auto;
                    overflow: auto;
                    padding: var(--lumo-space-s, 5px);
                }

                #buttons {
                    background-color: #fafafa;
                }

                #comments {
                    width: 100% !important;
                }

                #action-buttons {
                    width: 100% !important;
                    margin: 0;
                    background-color: #fafafa;
                }
            </style>
            <vaadin-form-layout id="form">
                <vaadin-text-field id="email" label="Email (login)" colspan="2"></vaadin-text-field>
                <vaadin-text-field id="first-name" label="First Name"></vaadin-text-field>
                <vaadin-text-field id="last-name" label="Last Name"></vaadin-text-field>
                <vaadin-text-area id="comments" label="Comments"></vaadin-text-area>
            </vaadin-form-layout>
            <form-buttons-bar id="action-buttons"></form-buttons-bar>`;
    }

    static get is() {
          return 'user-form';
    }
}

customElements.define(UserForm.is, UserForm);
