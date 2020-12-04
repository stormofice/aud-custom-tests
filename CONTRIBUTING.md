# Contributing

##General
* Use meaningful names for variables, methods, classes, ...
* Don't just copy the public tests. Try to refactor or deviate as much as possible from them

###Structure
* Templates that can be reused should be saved in `templates/`
    * Provide descriptive comments on how to integrate the template in another unit test
* The exercises can be found in the according folders `uebung##/` (e.g. `uebung01/`)
* Use todo comments (`// @TODO improve xyz`) to identify work in progress tests

##Conventions
* All code and comments should be written in English
* Make sure that you don't push a test, which
    * only works on your machine
    * doesn't compile
    * needs further dependencies
    * breaks functionality because of its unfinished state
    * contains the solution to the exercise
* Every test method should start with `custom_METHOD_TO_TEST`
    * `customTest_methodA()`
    * `customTest_methodB()`


## Keep in mind
The code here is published under a [public domain license](./LICENSE). Please make sure you are ok with that.
