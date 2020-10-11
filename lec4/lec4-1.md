# Good Object-Oriented Coding, Part 1

## Summary
### Rules
* __OOP:__
  1. Any data that must be stored in a type should be stored in a private field.
  2. Any access of the data (by code outside the class) should be implemented using a public (or protected) method.
  3. Anytime we want to access the field, we should use the public method instead of the field.
* All local variables do not get default values.
* Fields, on the other hand, are given a default value of 0, false, or null, depending on its type.
* A `final` variable will not change its value after the first assignment.
  * i.e. 	`public static final int numberOfSides = 6;`
* __Review but important:__ A class has access to the static methods/fields of its super class, but it does not inherit them (cannot override them).

## OOP Rules
To take advantage of the nice features of the Java language, we should follow some rules when writing our types.  __Here are the first rules:__
 1. Any data that must be stored in a type should be stored in a private field.
 2. Any access of the data (by code outside the class) should be implemented using a public (or protected) method.
 3. Anytime we want to access the field, we should use the public method instead of the field.

## More O-O Design: Building a Class from Scratch

This lecture looks at the Die class from Section 4.1 of the Lewis textbook and improves it slightly.

If we want to model a game die, we need to create a class, and we need the properties of:
  1) rolling a die
  2) getting a die face / value
  3) setting the die face
For each behavior/property, we need to create an appropriate method.

What information will we need to remember for the die:
1. the current face value (we have to remember the result of a roll)
2. the size of the die (maybe we want a 4-sided die, a 6-sided die, etc.)

What should the class extend?  If there is nothing in the API that matches the Die type, we should just extend `Object`.
* __Note: do NOT extend a class unless it makes sense to say `Die` "is a" ... for the class you are extending.__
  * Ex: If `Die` extends `JFrame`, then we are saying that all game dice are `JFrames`.  That would indeed be bizarre.

```
	public class Die extends Object {     // if we do not include "extends Object", Java will do that by default
	  private int currentValue;

	  public int getValue() {
	    return this.currentValue;
  	  }
```

* Please note that the "this." is not needed.  If omitted, Java automatically adds it, but it is included here for completeness.
* Note that there is no confusion with having a method and a field of the same name because they are used differently.
* Also, we should add comments above each method and field so that someone reading the code understands the purpose:

```
	/* returns the current face value */
	public int getValue() {
	  return this.currentValue;
	}

        /* sets the value of the die */
	public void setValue(int newFace) {
	  this.currentValue = newFace;
 	}
```

* Also note, that `setValue` allows any value to be set.  A better solution would be to use an if statement so that if the value is between 1 and the maximum allowed value.  See the actual code example.)
* For the roll, we use the `random()` method of the `Math` class:
  * the `random()` method takes no input and returns a random double value in the range [0.0, 1.0).  A little math and knowing our types lets us convert the value in [0.0, 1.0) to a value in {1, 2, 3, 4, 5, 6}.

```
	public int roll() {
	  this.currentValue = (int)(Math.random() * 6.0 + 1);
          return this.currentValue;
	}
```

## Initial field/variable values

__All local variables do not get default values.__  When you create a local variable, the first use of the variable must be to assign a value to it.

__Fields, on the other hand, are given a default value of 0, false, or null, depending on its type.__ If we do not give `currentValue` an initial value, it will get value 0.  0 is not a good value for a die.  So we should give the field an initial value.

```
	private int currentValue = 1;
```

## Magic Numbers (for `Die`)

* The use of 6 is not good.  It is a "magic number". A "magic number" is a number that has a special meaning to the programmer.
* Here, you need to know that 6 is the size of the die.  
Magic numbers should be avoided and replaced with varables.
(Any number that is not 0 or 1 is usually magic.)

```
	public static int numberOfSides = 6;

	public int roll() {
	  this.currentValue = (int)(Math.random() * Die.numberOfSides + 1);
 	  return this.currentValue;
	}
```

That is better because we gave it a name. We can make this better.  The size of a die does not change, so let us indicate that in the field.

## Final
Marking something as final means its value will not be changed.
- __A final variable will not change its value after the first assignment.__
- A final method will not be overridden.
- A final class will not be extended.

If we want all die to have 6 sides, we can also make the __field "static"__ to indicate that it belongs to a class:

```
	public static final int numberOfSides = 6;

	public int roll() {
	  this.currentValue = (int)(Math.random() * Die.numberOfSides + 1);
 	  return this.currentValue;
	}
```

Notice that we made `numberOfSides` `public` instead of `private`.  That is okay because we are dealing with a static (i.e. class) field.

Unlike with instance methods and fields, static methods behave exactly the same as static fields in that __a class has access to the static methods/fields of its super class, but it does not inherit them.__

Finally, the method roll() violated one of our rules for O-O coding:

> It violates the last rule: we are accessing the currentValue field directly instead of the getter/setter methods we wrote.  Let's fix that:

```
	public int roll() {
	  this.setValue((int)(Math.random() * Die.numberOfSides + 1));
          return this.getValue();
	}
```

Now, it satisfies all of our rules.  Notice that we do not have any methods for `numberOfSides` because we (currently) do not intend for code outside this class to access that data.  If we did, then we should create a getter method and use that here.
