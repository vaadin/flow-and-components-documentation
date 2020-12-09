# Vaadin Flow and Component Java APIs Documentation

This is the documentation for Vaadin Java framework versions 10+
It also contains documentation for the Java APIs for Vaadin's web components.

This documentation is available in [vaadin.com/docs](https://vaadin.com/docs/flow/Overview.html).
Any issues and contributions can be added here, or in vaadin.com/docs via the _report issues_ or _edit page_ actions.

Branching scheme for different Vaadin versions:

 - `master` branch is for the NEXT major version of Vaadin
     - It might be in prerelease stage. For example, now it is Vaadin 17
     - New Vaadin major versions are branched from `master` branch when the stable/beta release is done
 - `V16` branch for Vaadin 16 (Flow version 3.1)
 - `V15` branch for Vaadin 15 (Flow version 3.0)
 - `V14-next` branch for the next minor release of Vaadin 14 LTS
     - Any newer minor releases for 14 will be branched from `V14-next` branch when the stable/beta release is done
     - When a new minor release is done, the previous minor release documentation branch is deprecated
 - `V14.1` branch for the current stable version of Vaadin 14 LTS (Flow version 2.1)
 - `V13` branch is for Vaadin 13 **deprecated** (Flow version 1.4)
 - `V12` branch is for Vaadin 12 **deprecated** (Flow version 1.2)
 - `V10` branch is for Vaadin 10 LTS & 11 (latter is **deprecated**) (Flow version 1.0)

When making changes, you should first try to make them on top of the `master` branch.
The changes are later on cherry-picked to correct versions.
In case the change only applies to a previous version but not the next version, like Vaadin 14, you should then target the correct branch, which in this case would be `V14-next`.

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
