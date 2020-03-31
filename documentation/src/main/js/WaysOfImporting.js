// tutorial::importing-dependencies/tutorial-ways-of-importing.asciidoc

import '../styles/custom-css.js';
import './javascript-file.js';

// tag::client-side-theme[]
// Import a theme-specific component, for example <vaadin-button>
import '@vaadin/vaadin-button/theme/lumo/vaadin-button.js';

// Import the color style sheet if you are using some of
// the custom color properties Lumo offers
import '@vaadin/vaadin-lumo-styles/color.js';
// end::client-side-theme[]
