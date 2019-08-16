import {PolymerElement, html} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-grid/vaadin-grid.js';
import './user-form.js';

class MainView extends PolymerElement {
    static get template() {
        return html`
            <style>
                #main-container {
                    width: 100%;
                    height: 100%;
                    display: flex;
                    flex-direction: column;
                }

                @media screen and (min-width: 600px) {
                    #main-container {
                        flex-direction: row;
                    }
                }

                #users-grid {
                    flex: 1;
                    overflow-y: scroll;
                    height: 100%;
                    background-color: #fafafa;
                }

                #user-form {
                    flex: 1;
                    height: 100%;
                    border-left: 1px solid #d4d4d4;
                    border-top: 1px solid #d4d4d4;
                    background: #ededed;
                }
            </style>
            <div id="main-container">
                <vaadin-grid id="users-grid"></vaadin-grid>
                <user-form id="user-form"></user-form>
            </div>`;
    }

    static get is() {
          return 'main-view';
    }
}

customElements.define(MainView.is, MainView);
