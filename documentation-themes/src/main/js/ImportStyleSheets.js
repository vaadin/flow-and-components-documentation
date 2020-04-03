// tutorial::../documentation-themes/importing-style-sheets.asciidoc

// tag::import-register-styles-function[]
import { registerStyles } from '@vaadin/vaadin-themable-mixin/register-styles.js';
// end::import-register-styles-function[]
// tag::import-css-function[]
import { css } from '@vaadin/vaadin-themable-mixin/register-styles.js';
// end::import-css-function[]

// tag::register-styles[]

registerStyles('vaadin-text-field', css`
  /* Styles which will be imported into
     vaadin-text-field local style scope */
`);
// end::register-styles[]

// tag::register-styles-multiple[]
registerStyles('vaadin-select-overlay vaadin-combo-box-overlay', css`
  /* Styles which will be imported in
     vaadin-select-overlay and vaadin-combo-box-overlay
     local style scopes */
`);
// end::register-styles-multiple[]

// tag::export-css[]

export default css`
  h1 {
    font-weight: 300;
    font-size: 40px;
  }

  h2 {
    font-weight: 300;
    font-size: 32px;
  }

  h3 {
    font-weight: 400;
    font-size: 24px;
  }
`;
// end::export-css[]

// tag::import-shared-css[]
import sharedTypography from 'styles/shared-typography.css.js';
// end::import-shared-css[]
// tag::import-css[]

const style = document.createElement('style');
style.innerHTML = sharedTypography.toString();
document.head.appendChild(style);
// end::import-css[]

// tag::register-styles-include[]
registerStyles('vaadin-confirm-dialog-overlay', sharedTypography);
// end::register-styles-include[]

// tag::import-polymer[]
import { PolymerElement } from '@polymer/polymer/polymer-element.js';
import { html } from '@polymer/polymer/lib/utils/html-tag.js';
// end::import-polymer[]

// tag::custom-element[]

class MyView extends PolymerElement {
  static get template() {
    return html`
      ${sharedTypography}
      <h2>My view title</h2>
      ...
    `;
  }
  static get is() {
    return 'my-view';
  }
}
customElements.define(MyView.is, MyView);
// end::custom-element[]


// tag::client-side-theme[]
// Import a theme-specific component, for example <vaadin-button>
import '@vaadin/vaadin-button/theme/lumo/vaadin-button.js';

// Import the color style sheet if you are using some of
// the custom color properties Lumo offers
import '@vaadin/vaadin-lumo-styles/color.js';
// end::client-side-theme[]
