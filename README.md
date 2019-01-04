# Vaadin Flow and Component Java APIs Documentation

This is the documentation for Vaadin Flow, the new Java web framework available in Vaadin platform.
It also contains documentation on the Java APIs for Vaadin's web components.

This documentation is available in [vaadin.com/docs](https://vaadin.com/docs/v10/flow/Overview.html).
Any issues and contributions can be added here, or in vaadin.com/docs via the _report issues_ or _edit page_ actions.

This branch is Vaadin 10 (Flow version 1.0). See other branches for other framework versions:

 - `master` branch is for Flow version 1.3 (or 2.0) that will go into upcoming Vaadin 13
 - `V12` branch is for Flow version 1.2 (Vaadin 12)
 - `V10` branch is for Flow version 1.0 (Vaadin 10 & 11)

 ALL FIXES should always target the master branch and we will pick the bugfixes to correct branches from there. 

## Structure

The documentation is split into the following modules:

- `docs-helpers` - sources for validating the code snippets to match the documentation
- `tutorial-*` - standalone tutorial projects that can be run individually
- `documentation` - the documentation files written in .asciidoc and tests

For verifying the changes made to the documentation, run `mvn verify` on the root level project.

### Adding new tutorials

When a new tutorial is added, the end result should be included as a new module that needs to be built before the `documentation` module.
The tutorial should use the `@CodeFor("path/file.asciidoc")` annotation available from the `docs-helpers` dependency to map the source files to the tutorial code snippets in the `documentation` module.
If a tutorial needs extra helper classes to create, those should be added into `TestTutorialCodeCoverage#HELPER_CLASSES` field as exclusions so that the checks won't fail on unused class.

Any added tutorial module can be included as a dependency to the `documentation` module,
so that the code snippets from the documentation can be verified. This requires couple steps:
- For web app projects with `war` packaging:
  - the `<appendClasses>` configuration must be enabled for the `maven-war-plugin`
  - The dependency should be to `documentation` added with `<classifier>classes</classifier>`
- The `TestTutorialCodeCoverage` class needs to know the source folder location (see static variables)
