# Wrapper Classes

For each primitive type (including `void`), Java provides a __wrapper class__ that __allows you to store the primitive inside an object.__  This __allows us to use primitives where the code is expecting Objects.__  For the most part, the wrapper class is the same name as the primitive, but with a capital letter:
```
     Double d = new Double(4.3);
```
except for `Integer` and `Character`.

Starting with Java 5, Java will __automatically convert between the wrapper class and the primitive__, when needed and when it is clear what type is needed.
So, the following are legal:
```
	Integer x = 5;
	int y = new Integer(10);
	Integer p = 4;
	p = p + 1;
```

* Warning: despite how it is written, things are not exactly as they seem.  
  * The line `Integer p = 4` creates a new object of type Integer and stores the value 4 in it.  The line is really: `Integer p = new Integer(4)`
  * In the next line, the expression `p + 1` requires an `int` so the `Integer p` is replaced by the `int` value `4`.
  The second part of that line: `p = ` requires that the `int` value `5` be placed in an `Integer`, and so a new `Integer` object is created containing 5, and `p` stores the address of that object,
   So this line is really: `p = new Integer(p.intValue() + 1);` The result is that `p` now has the __address of a different Integer object.__

* __What about:	`new Integer(5) == new Integer(5)`?__
  * The `==` operator is not a primitive only operator, so the `intValue()` method is not called. As a result, the __addresses of the Integer objects are compared__, and since these are two different objects stored at two different locations, the == evaluates to false.
