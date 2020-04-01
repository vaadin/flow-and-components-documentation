// tutorial::../documentation-themes/importing-style-sheets.asciidoc

// tag::register-styles[]
import { registerStyles, css } from '@vaadin/vaadin-themable-mixin/register-styles.js';

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
