# Contributing

## General
* Use meaningful names for variables, methods, classes, ...
* Don't just copy the public tests. Try to refactor or deviate as much as possible from them

### Project structure
* Templates that can be reused should be saved in `templates/`
    * Provide descriptive comments on how to integrate the template in another unit test
* The exercises can be found in the according folders `uebung##/` (e.g. `uebung01/`)
* Use TODO comments (`// @TODO improve xyz`) to identify work in progress tests

## Conventions
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
* Configuration variables should be declared directly under the class signature


## Keep in mind
The code here is published under a [public domain license](./LICENSE). Please make sure you are ok with that.

---

## Allgemein
* Verwende ausschlaggebende Namen für Variablen, Methoden, Klassen, ...
* Versuche von den gegebenen Public Tests abzuweichen

### Projektstruktur
* Vorlagen für Hilfsmethoden/... sollen in `templates/` gespeichert werden
    * Stell bitte eine Anleitung zum Verwenden der Vorlage als Kommentar bereit
* Die Tests werden in den jeweiligen Ordnern gespeichert `uebung##/` (z.B. `uebung01/`)
* Verwende TODO Kommentare (`// @TODO improve xyz`) um noch in Arbeit stehende Bereiche zu markieren

## Konventionen
* Jeglicher Programmtext und darin enthaltene Kommentare müssen auf Englisch verfasst werden
* Stelle sicher, dass du keinen Test hochlädst, der
    * nur auf deinem Computer funktioniert
    * nicht kompiliert werden kann
    * auf weiteren Abhängigkeiten beruht
    * Funktionalität aufgrund seiner unvollständigen Ausarbeitung beeinträchtigt
    * Lösungen zur Aufgabe enthält
* Jede Test Methode solte wie folgt benannt werden `custom_METHOD_TO_TEST`
    * `customTest_methodA()`
    * `customTest_methodB()`
* Konfigurationsvariablen sollten direkt nach der Klassensignatur deklariert werden
