# The `String` class

* `String` is a pre-defined class in Java and one of the most important classes.
  * `Strings` represent text as a sequence of characters.
  * `Strings` have a special means for creating instances.  While we can create instances using the `new` operator (just as with any class), we can also just place the desired string inside double quotes.

    >"Hello"    <- this is a shortcut to creating a new String using the appropriate sequence of characters as input.

    * Note that "Hello" acts just like the `new` operator, it evaluates to the address of the String object storing h,e,l,l,o.

We can use the object just like any other object.  We can store it: `String s = "Hello";`

## + Operator with Strings
Strings have a special operator, the `+` operator.  The result of `+` is a new `String` that concatenates the two operands together. If one operand is not a `String`, an appropriate `String` is created (through a sequence of object creation and  method calls) before the concatenation.

```
    Ex:   "Hello" + "there"    -> "Hellothere"
	  "Hello " + "there"   -> "Hello there"
          "x = " + 3           -> "x = 3"
```

Note: you must be careful when mixing Strings and numeric primitives with the +.  When is the `+` meant to be a String concatenation and when is it meant to be a normal addition?

"x = " + 3 + 5  will return "x = 35"  (+ is evaluated left to right) but 3 + 5 + " = y"  will return "8 = y"
