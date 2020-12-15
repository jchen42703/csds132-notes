# Table of Contents

## Module 4: More on Classes, Constructors and Conditionals

* [4-1: Good Object-Oriented Coding, Part 1](lec4-1.md)
  * [OOP Rules](lec4-1.md/#oop-rules)
  * [Initial field/variable values](lec4-1.md/#summary)
  * [`final` keyword](lec4-1.md/#final)
* [4-2: Constructors](lec4-2.md)
  * [Summary](lec4-2.md/#summary)
  * [Method Overloading](lec4-2.md/#method-overloading)
* [4-3: Conditionals](lec4-3.md)
* [4-4: Intro to Strings](lec4-4.md)
  * Only talks about the `+` operator
* [4-5: `toString()` and `equals` and Overriding methods](lec4-5.md)
  * [Overriding methods of Object](lec4-5.md/#overriding-methods-of-object)
    * `equals()` v. `==`
  * [Overriding](lec4-5.md/#overriding)
    * [Why is overloading instead of overriding a problem?](lec4-5.md/#overriding)
    * `@Override`
    * `instanceof` and `getClass()`
* [4-6: Constructor calls with `super()` and `this()`](lec4-6.md)
  * [Summary](lec4-6.md/#summary)
* [4-Class: Conditional Statements, Strings, Fields, and Overriding Methods](lec4-class.md)
  * Review of conditionals, fields/variables, strings, and method overriding
  * [`super.methodname()` and `super.fieldName` to access parent class overridden methods and parent fields](lec4-class.md/#method-overriding)

## Module 5: Loops and Strings and a review of overriding

* [5-1: Fields / Variables](lec5-1.md)
  * Basically the same as `4-Class; Fields/Variables`, but more specific with default values.
* [5-2: Introduction to Loops](lec5-2.md)
  * [Summary](lec5-2.md/#summary)
* [5-3: While Loop Example 1:  Euclid's Greatest Common Divisor algorithm](lec5-3.md)
* [5-4: Newton's Method Loop](..)
  * Omitted because useless
* [5-5: Strings and the `length()` and `charAt` methods](lec5-5.md)
* [5-6: `isPalindrome` method from scratch example](...)
  * Omitted because useless
* [5-7: Creating Strings with `StringBuilder`](lec5-7.md)
* [5-8: For Loop Example 2](...)
  * Omitted because useless (just a string creation with a for loop example)
* [5-Class: Review of OOP Guidelines](lec5-class.md)
  * [Example of why you should use getters and setters instead of the fields directly](lec5-class.md/#example-1)

## Module 6: Java Arrays

* [6-1: Arrays](lec6-1.md)
* [6-2: Loops and Arrays](lec6-2.md)
* [6-3: Appending elements to an array](lec6-3.md)
* [6-4: Arrays and Typecasting](lec6-4.md)
* [6-5: Linear Searching in an Array](lec6-5.md)
* [6-6-1: Binary Search, More on Reasoning About Loops](lec6-6-1.md)
* [6-7: Loop Testing](lec6-7.md)

## Module 7: Java Multidimensional Arrays and Memory Model

* [7-1: Multidimensional Arrays](lec7-1.md)
* [7-2: Java Memory Model, Part 1](lec7-2.md)
* [7-3: The Java Memory Model, part 2](lec7-3.md)
* [7-4: The `main` method](lec7-4.md)

## Module 8: Abstract Classes and Interfaces

* [8-1: Abstract Classes and Creating a Class Hierarchy](lec8-1.md)
  * [Summary](lec8-1.md/#summary)
* [8-2: Java interfaces and Multiple Inheritance](lec8-2.md)
  * [Summary](lec8-2.md/#summary)
  * [Quiz Q&A](lec8-2.md/#quiz-questions-and-answers)
* [8-3: Java Shortcuts](lec8-3.md)
  * [Binary Operator Shortcuts (`+=`)](lec8-3.md/#binary-operator-shortcuts)
  * [Conditional Operator (`?`, `:`)](lec8-3.md/#conditional-operator)
  * [`switch`](lec8-3.md/#switch-statements)
  * [Increment and decrement shortcuts (`i++`, `++i`, `i--`, `--i`)](lec8-3.md/#increment-and-decrement-shortcuts)
    * [Increment and loops (`i++` v. `++i`)](lec8-3.md/#increment-and-loops)

## Module 9: Linked Lists and Generic Types

* [9-1: Linked Lists and Generic Types (`LLNode`)](lec9-1.md)
  * [Summary](lec9-1.md/#summary)
* [9-2: Wrapper Classes](lec9-2.md)
* [9-3: Continuing with Linked Lists](lec9-3.md)
  * Recommend this for good snippets in:
    * [Insertion at the ends of nodes in a Linked List](lec9-3.md/#insertion-at-ends-of-nodes-in-a-linked-list)
    * [Insertion between Nodes](lec9-3.md/#insertion-between-nodes)
* [9-4: Abstract Data Types, Creating a `LinkedList` and A Short Note on Exceptions](lec9-4.md)
  * [Abstract Data Types](lec9-4.md/#abstract-data-types)
  * [Creating a `LinkedList` class](lec9-4.md/#creating-a-linkedlist-class)
  * [Short Note on Exceptions](lec9-4.md/#short-note-on-exceptions)
* [9-5: Linked Lists and Loops](lec9-5.md)
  * Good coding snippets for looping through a `LinkedList`

## Module 10: Generic Types, Iterable and Comparable

* [Arrays and Generic Types]('Arrays-and-Generic-Types.md')
* [Generics, Static Methods, Wildcards]('Generics-Static-Methods-Wildcards.md')
* [Java Collections and the For Each Loop]('Java-Collections-and-For-Each-Loop.md')
* [Java `Comparable` Interface]('Javas-Comparable-Interface.md')
* [Restricting Generics]('Restricting-Generics.md')
* [Typecasting with Generic Types]('Typecasting-with-Generic-Types.md')
* [Iterables and Generic Types]('lec10-class.md')

## Module 11: Nested Types, Anonymous Classes, Exceptions

* [11-1: Sorting, Comparator, and an Intro to Nested Classes](lec11-1.md)
* [11-2: Non-static Nested Classes](lec11-2.md)
* [11-3: Anonymous Classes](lec11-3.md)
* [11-4: Review and Generics with Static and Non-Static Nested Classes](lec11-4.md)
  * Note: I combined this with lec11-5 because it was also review and discussed generics with nested classes.
* [11-class: Exceptions](lec11-class.md)
  * Goes over checked (`try/catch`) and unchecked exceptions with examples

## Module 12: JavaFX GUI

* [12-1: Java Collections](lec12-1.md)
* [12-2: Event Driven Programming Paradigm and Java Interfaces](lec12-2.md)
* [12-3: Debugger](lec12-3.md)
* [12-4: Anonymous Classes](lec12-4.md)

## Module 13: Advanced Java Features

* [Java 8 Shortcuts for Anonymous classes: The "lambda" expression and the "method reference" expression.](lec13-1.md)
* [Java 8 Shortcuts for Anonymous classes, Part 2: The "method reference" expression.](lec13-2.md)
* [Java Optional](lec13-3.md)
* [Another Example Using Lambdas and Java 8 "Function" Types](lec13-4.md)

## Module 14: More Advanced Java

* [14-1: Threads in the Java API classes](lec14-1.md)
* [14-2: Threads and Java `Swing`](lec14-2.md)
* [14-3: Threads and `JavaFX`](lec14-3.md)
* [14-4: `JavaFX` and the Main Thread](lec14-4.md)
* [14-5: Java Packages](lec14-5.md)
* [14-6: Java JAR Files](lec14-6.md)
* [Live Lecture: Java Reflection](lec14-class.md)
