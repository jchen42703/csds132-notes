# Abstract Classes and Creating a Class Hierarchy

## Summary
* To use inheritance well, we want to move each attribute as high up the hierarchy as we can (to the simplest class as possible, so it can be easily reused by child classes).
* An abstract class is a class that cannot be instantiated.  It is used to enforce common behavior of all its child classes.
* An abstract method is a method with no body (i.e. it is a method stub).  All classes inherit the abstract method, but unless the class is abstract, it must override the abstract method to give it a body.
* An abstract class can contain everything that a normal class can (including constructors!), plus it can contain abstract methods.
```
	public abstract class Shape {
	  public abstract double area();

	  public abstract double perimeter();

	  public boolean isLargerThan(Shape s) {
	    return this.area() > s.area();
	  }
	}
```
* Can extend an abstract class with another abstract class (like Python), so can inherit abstract methods
* __OOP Rules to Know__
  1. Form a hierarchy using the "is-a" and "has-a" rules.
  2. __"is-a":__
     1. If type B "is-a" type A, then we make B extend A.  You must be sure that __everywhere type A is used type B can also be used.__  If that is not true, then B is not also type A.
     2. __If B extends A, then B should add features to A or restrict features of A.__  B should not block or remove features of A.
  3. __"has-a":__
     1. Use "has-a" to decide on the methods for the class, and push common methods as high up the hierarchy as possible.
     2. Use __private fields__ to store the data and public or protected getter/setter methods as needed.
     3. Reduce redundancy. Remember that __all fields of the class and parent classes exist in the object.__  Use the __getter/setter methods to access the values of the parent class.__
  4. __Outside of the constructors, always use the getter/setter methods instead of the fields when accessing values of an object.__
  5. __Do not use getter/setter methods inside the constructor.__  The purpose of the constructor is to initialize the object correctly, and if you use methods, a class that extends your class could override the methods and accidentally break how your class works by preventing proper initialization of the instance.

## Intro
Suppose we want to create a program that does high school geometry?  We first need to identify the types of things that must be modelled in the program:
  lines, points, area, polygons, perimeter, triangles, squares, circles, circumference, etc..

## How do we organize this into classes?
1. is-a rule:  If A is-a B then we make A and B classes where A extends B.
     Examples:  Square is-a Rectangle.
                Square is-a Polygon
    Triangle is-a Polygon
    Rectangle is-a Shape
    Circle is-a Ellipse

So, the is-a relation suggests the following hierarchy:

        Polygon         Ellipse
      /        \          |
  Rectangle   Triangle  Circle
      |
    Square

2. has-a rule:  If A has-a B, then make B a method/methods in A.
Examples: Square has-a area
  Polygon has-a numAngles
  Circle has-a area

To use inheritance well, we want to move each attribute as high up the hierarchy as we can.
Since all the above classes have an area, this suggests that we should make a common parent at the top of the hierarchy so we can define area there, and if the other classes need to change how area is defined, they can "override" the area method.


                 Shape
               /       \
        Polygon         Ellipse
      /        \          |
  Rectangle   Triangle  Circle
      |
    Square


numAngles does not belong in Shape because ellipse and circle does not have sides.  Instead, the highest we will move that attribute is into Polygon.
We will place length and width into Rectangle as well as into Triangle. It would be nice to move them up to a common ancestor as well, but Polygon may not be the right choice.  
What does length and width mean for an arbitrary polygon?


## Abstract Classes
The only reason we created the Shape class was to have a common ancestor for things that all shapes have like area and perimeter. We do not want to be able to create an instance of "Shape" without specifying what type of Shape.  So we can make Shape be an abstract class.

An abstract class is a class that cannot be instantiated.  It is used to enforce common behavior of all its child classes.

In this case, we want to be able to say that polygons, circles, rectangles, triangles, and squares are all "shapes", and all shapes have area. To get the last behavior, we make area a method of the abstract class. However, we do not know how to compute the area without knowing what type of shape it is, so we make area an "abstract method".

An abstract method is a method with no body (i.e. it is a method stub).  All classes inherit the abstract method, but unless the class is abstract, it must override the abstract method to give it a body. (Otherwise, we could call the method without defining what it does!)

```
	public abstract class Shape {
	  public abstract double area();

	  public abstract double peremeter();
	}
```

An abstract class can contain -everything- that a normal class can (including constructors!), plus it can contain abstract methods. So, I can add normal methods too.

```
	public abstract class Shape {
	  public abstract double area();

	  public abstract double peremeter();

	  public boolean isLargerThan(Shape s) {
	    return this.area() > s.area();
	  }
	}
```

Notice that by defining abstract area inside abstract Shape, then we can use isLargerThan to compare all the types that are under Shape in the class hierarchy.

Polygon should also be an abstract class because we do not know how to define the area of an arbitrary polynomial.
Polygon class will be an abstract class with a constructor.  The constructor will set the number of sides to the polygon.

```
	public abstract class Polygon extends Shape {
	  private int numAngles;

	  public Polygon(int numAngles) {
	    this.numAngles = numAngles;
	  }

	  public int getNumAngles() {
	    return numAngles;
	  }
	}
```

Notice that Polygon does not need to override the area method.  It inherits the abstract method, and because Polygon is abstract, it is allowed to have abstract methods.

Now, we will create the `Rectangle` class.  Note that `Rectangle` is required to have a constructor because the Polygon DOES NOT have a constructor that takes no input.

Because Rectangle is not abstract, we must override any abstract methods Rectangle inherits.  In this case, it inherits area.  To compute the area, we need the length and width, and thus we should have a constructor that sets those values.

```
	public class Rectangle extends Polygon {
	  private double length;
	  private double width;

	  public Rectangle(double length, double width) {
	    super(4);
	    this.length = length;
	    this.width = width;
	  }

	  /* include getter and setter methods for length and width */

	  @Override
	  public double area() {
	    return getLength() * getWidth();
	  }

	  @Override
	  public double getPermeter() {
	    return 2 * (getLength() + getWidth());
	  }

	}
```

### Note: why did we use `this.length = length` instead of `setLength()` in the constructor?  
Because `setLength()`, or rather `this.setLength()` may not call the setLength method in `Rectangle`.  
  * It will call the `setLength` method of the __true type.__ As a result, the length field __might not get set.__ To make sure the length and width fields are always set, we need to put the assignment statements in the constructor and not call methods that could be overridden.

Note that we are using the getter methods inside the body for area.  Doing so will make our work for Square (and any other class that extends Rectangle) a lot simpler. `Square` will not have to change how area is computed because `Square` will simply override the getter and setter methods to make sure that a square has the same width as length.

```
	public class Square extends Rectangle {
	  public Square(double size) {
	    super(size, size);
	  }

	  /* need to override setLength and setWidth to make sure that this object is always has equal width and length (i.e. it is always a square) */
	}
```

There are two ways to do this:
#### Version 1:  Override the setWidth and setWidth methods so that both the width and length are always set the same

```
	  @Override
	  public void setWidth(double width) {
	    super.setWidth(width);
	    super.setLength(width);
	  }

	  @Override
	  public void setLength(double length) {
	    this.setWidth(length);
	  }
```

* Note the use of "super" above in super.getWidth(width);  `super` is a special word that means to use the method (or field) of the parent class.  This is the only way we can have a class call a method that was overridden. __If we did not use "super", then the true type's version of `setWidth` would be called.__

### Version 2:  We will use only the Rectangle's length to determine both the length and width of Square

```
	  @Override
	  public void setWidth(double width) {
	    this.setLength(width);
	  }

	  @Override
	  public double getWidth() {
	    return this.getLength();
	  }

	  (In this version, we can change the constructor to be:   super(size, 0);  because we are using only the length of Rectangle for the square and not the width)
```

Which method is better?  Neither!  They both work the same.  However, note that if we had Rectangle's area method use "length * width" instead of "getLength() * getWidth()", then Version 2 would not work! That does not mean Version 1 is better because there are other things we could have done in Rectangle using fields instead of methods that could have broken Version 1 as well.


# SUMMARY: IMPORTANT RULES FOR PROPER OBJECT-ORIENTED PROGRAMMING IN JAVA
   1. Form a hierarchy using the "is-a" and "has-a" rules.
   2. "is-a":
      2a. If type B "is-a" type A, then we make B extend A.  You must be sure that everywhere type A is used type B can also be used.  If that is not true, then B is not also type A.
	  For example, we can use Square anywhere Rectangle is expected, and it makes sense.  Likewise, we can use Square and Rectangle anywhere that expects a Shape, and that makes sense.
	  It would not make sense to use Triangle anywhere that expects a Rectangle.
      2b. If B extends A, then B should add features to A or restrict features of A.  B should not block or remove features of A.
	  Square does not block the ability to change the width or length of Rectangle, but it restricts the change so that both dimensions change together.
   3. "has-a":
      3a. Use "has-a" to decide on the methods for the class, and push common methods as high up the hierarchy as possible.
      3b. Use private fields to store the data and public or protected getter/setter methods as needed.
      3c. Do not save the same information multiple times.  Remember that all fields of the class and parent classes exist in the object.  Use the getter/setter methods to access the values of the parent class.
   4. Outside of the constructors, always use the getter/setter methods instead of the fields when accessing values of an object.  
      This will allow any classes that extend your class to easily override methods to specify necessary behavior.
      (If you do not want public getter/setter methods, then at least use protected ones.)
   5. Do not use getter/setter methods inside the constructor.  The purpose of the constructor is to initialize the object correctly, and if you use methods, a class that extends your class could override the
      methods and accidentally break how your class works by preventing proper initialization of the instance.
