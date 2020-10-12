# Conditional Statements, Strings, Fields, and Overriding Methods

In this lecture, we add another feature to `GeometricFrame`, and on the way, we learn about fields, conditional statements, and the `String` class. We ended the lecture by overriding a method.

## Summary
* Conditionals
* Fields/variables
* String and the `+` Operator
* Method overriding
  * `super.methodname()` and `super.fieldName` to access parent class overridden methods and parent fields
  * And an example using method overriding and the `super.methodname()` within a method

## Conditional statements
```
if (condition)
   then-statement
else
   else-statement
```

## Fields / Variables:
There are 4 kinds of variables, and the kind you create depends on where you create it:
1. __class (static) fields:__  the variable is stored in the class (one copy that all the objects share)
	- class fields exist for the duration of the program.
2. __instance (non-static) fields:__  a separate variable stored in each instance of the class.  Every instance has its own version.
	- an instance field exists as long as the containing instance exists
3. __local variables:__  a variable declared inside a method.  It exists from the moment created until the end of the compound statement it is declared it.
4. __method parameter:__ the variable(s) that store an input to the method.  It exists only in the body of the method.

## The String class
String is a pre-defined class in Java and one of the most important classes. Strings represent text as a sequence of characters. Strings have a special means for creating instances. String s = "Hello";
* __`+` operator__
  * Strings have a special operator, the `+` operator.  The result of + is a new String that concatenates the two operands together. If one operand is not a String, an appropriate String is created (through a sequence of object creation and  method calls) before the concatenation.
  * Ex:   "Hello" + "there"    -> "Hellothere"

## Overriding methods
* Overriding methods is the technique of Java where we re-define the behavior of an inherited method.
* We override an inherited method by writing a method that has exactly the __same access modifier, return type(*), name, and input types.__
  * (*) the return type can be narrower __if the return type is a non-primitive__
* An object with ALWAYS use the version of a method for its __true type.__

## Accessing overridden methods
* Java has a means to access methods, fields, and nested types of a class's super class - from within that class's body, and that is `super`.
* This is really useful when we override a method but we __still want access to the inherited method before we overrode it.__
* We can only use `super`. within the class body.  You can NOT use it to from outside a class to avoid calling an overridden method.


## OUR TASK
* In this class, we create the method `displayTheSize` to display the window dimensions on the title.
* The method will have an input to let us turn the size display on an off.

```
  JFrame has the following methods:
     void setTitle(String title)  -> sets the title of the window to the input title
     String getTitle()            -> returns the title of the window
```

To use these methods, we need instances of the String class.

First, we use what we know of `String` and `setTitle` to add the title:

```
	public void displayTheSize(boolean show) {
          if (show)
	    this.setTitle("(" + this.getWidth() + "x" + this.getHeight() + ")");
          else
            this.setTitle("");
	}
```

This lead to a couple problems.  the first was that it erased the title that was already on the window instead of adding to the title.  We fixed that by using `this.getTitle()` to get the current title:

```
	public void displayTheSize(boolean show) {
          if (show)
	     this.setTitle(this.getTitle() + " (" + this.getWidth() + "x" + this.getHeight() + ")");
          else
             this.setTitle(this.getTitle());
	}
```

Unfortunately, this solution keeps appending the size to the title each time it was called. We want to remember the original title without the size and then use the original title instead of this.getTitle(). We want to remember the original title after the method ends, so a local variable or method parameter is not the right type.
We want every window to remember its own original title, so we want a separate variable for each instance. Thus, we want an instance field. __A field declaration is just like a variable declaration, but adds an access modifier__

```
	private String originalTitle;
```

__Here is our next attempt:__

```
	private String originalTitle = "";    

	public void displayTheSize(boolean show) {
	  if (show)
	    this.setTitle(this.originalTitle + " (" + this.getWidth() + "x" + this.getHeight() + ")");
	  else
	    this.setTitle(this.originalTitle);
	}
```

But when do we want to remember the original title?
The first option (that almost worked), was to remember the original title when adding the size to the title the first time, or better, each time we go from no size displayed to a displayed size.

This requires us to remember if the size is displayed or not.

```
	private String originalTitle = "";    
        private boolean sizeIsDisplayed = false;

	public void displayTheSize(boolean show) {
          if (!sizeIsDisplayed)
            this.originalTitle = this.getTitle();

	  if (show) {
	    this.setTitle(this.originalTitle + " (" + this.getWidth() + "x" + this.getHeight() + ")");
            this.sizeIsDisplayed = true;
          }
	  else {
	    this.setTitle(this.originalTitle);
            this.sizeIsDisplayed = false;
          }
	}
```

This -almost- works.  Where it fails is if the size is displayed on the title, and then someone calls `setTitle` to change the title.  Now we both lose the title, but originalTitle will still be storing the previous title!

What we really want is to remember the "originalTitle" every time the `setTitle()` method is called. What we want is to change how `setTitle` works.

## Method Overriding
* We can change the behavior of the setTitle method by "overriding" it.  
* __A class inherits all the public and protected instance methods of the super class.__
* A class can replace an inherited method by overriding it.  We override a method by creating another method with
the same access modifier, return type, name, and input types and order (called the parameter signature). You can only override instance methods.  
* __If we override a method, inside the class we can still have access to the original method of the super class.__  To access the original method, we use    `super.methodName()` which acts just like `this.methodName()` except it is using the method of the __super class.__ You can also access fields of the super class with `super.fieldName`
* However, to all code outside of this class, the overriding method has replaced the inherited method, and there is no way that code outside of this class can access the original inherited method.

```
	/* we are overriding the setTitle method to change its behavior */
 	public void setTitle(String title) {
	  this.originalTitle = title;   // <- first we save the title into our field
	  super.setTitle(title);        // <- then we call the inherited setTitle method so that the title still gets displayed on the window
	}
```

Now, we have to make a change to `displayTheSize`.  Each time that method calls `this.setTitle`, it will now use our new overridden definition! That will mean it will save the title (including the coordinates!).  We can avoid that by having `displayTheSize` call the inherited `setTitle` instead of the our new `setTitle`.

Here is the code:

```
private String originalTitle = "";    
      private boolean sizeIsDisplayed = false;

public void displayTheSize(boolean show) {
  if (show) {
    super.setTitle(this.originalTitle + " (" + this.getWidth() + "x" + this.getHeight() + ")");
          this.sizeIsDisplayed = true;
        }
  else {
    super.setTitle(this.originalTitle);
          this.sizeIsDisplayed = false;
        }
}
```

Our code still is not perfect.  When you set a new title, you lose any coordinates displayed on the original title.
One last change:

We should have our new `setTitle` method add the coordinates back on the title if they are displayed.
How to do that?  By calling the `displayTheSize` method!

```
public void setTitle(String title) {
  this.originalTitle = title;                   // <- first we save the title into our field
  this.displayTheSize(this.sizeIsDisplayed);    // <- if we had the size on the title before changing the title, add it back on
}
```

Since the `displayTheSize` methds calls `super.setTitle`, the title will be correctly set.

The class now works great.  We can add the size or remove the size. Because we replaced (overrode) the inherited setTitle method, now whenever the setTitle is called, it will first save the original title and then set the size display (or not) depending on what the person using this code wants.

One more issue: every time we change the size, the new size is not reflected on the title until we call displayTheSize. That fix will have to come in a later class.
