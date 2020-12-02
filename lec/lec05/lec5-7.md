# Creating Strings with `StringBuilder`

## Summary
* Use `StringBuilder` to create new strings with the `append()` method to conserve memory (instead of using the `+` operator with regular `String`s and exploding the memory with new strings being created each time).
* `StringBuilder` can be converted to a `String` with `StringBuilder.toString()`.

## Problem with `+`
When building a string using a loop, we may be tempted to use the `+` operator.

```
String result = "";

	for (....) {
	  ....
	  result = result + c;
	  ....
	}
	return result;
```

However, this is not a good solution. __The + operator creates a new String each time.__ If we are dealing with a very long String, such as if we want to capitalize a DNA code of millions of characters, we will be creating a LOT of unnecessary Strings and __using up our memory.__

## `StringBuilder`
Java provides a `StringBuilder` class to create Strings. `StringBuilder` has all the same methods as `String` (`charAt`, `length`, etc) plus several others.
* Once useful one is `append` that adds new characters (or other values) to the end of the string being created.

### How to create an empty StringBuilder?
Just like you create any other initial instance:

```
StringBuilder result = new StringBuilder();
```

What do we return at the end?  We can't return result because it is not a String.  But Object has a method that returns a String representation, and every class inherits it from Object:	`result.toString();`
