[![Build Status](https://travis-ci.org/brettwooldridge/kuill.svg?branch=master)](https://travis-ci.org/brettwooldridge/kuill)

# kuill

This project uses code originally from the [android-quill-delta](https://github.com/volser/android-quill-delta) project.  The dependency
on Google's GSON library was replaced with the more common Jackson JSON library, and the build was changed to be non-Android specific.

This library should work for both Kotlin and Java users, with the caveat that Java users will need the Kotlin runtime jar in order to run.

This library provides a Model representation of the [Quill](https://quilljs.com) editor ["Delta"](https://quilljs.com/docs/delta/) document model, as well as methods and operatators required to read and manipulate the model.
