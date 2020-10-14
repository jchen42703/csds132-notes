# Java interfaces and Multiple Inheritance

## Summary
* __Multiple inheritance:__ inheriting more than one class
* Java interfaces are used to deal with multiple inheritance (allowing you to specify an augmentation without having to create the whole class and methods + fields that may be duplicate with another parent class) and are to contain `public abstract` methods
  * Can contain:
    - static final fields
    - static public nested types
    - static methods
    - abstract public instance methods (method stubs)
    - starting in Java 9, private methods will be allowed
* To create an interface:
```
public interface MyInterface {
  void methodStub1(int x, int y);

  int methodStub2();
}
```
* Uses the `implements` keyword for inheriting an interface
```
public class Square extends Rectangle implements RegularPolygon {
  ....
}
```
* Since all instance methods of an interface MUST be public and abstract, the Java style is to drop those two modifiers (but you can keep them if you want).
* __Abstract Class v. Interface___
  * __Abstract class establishes "is a" relation between related classes and interface provides "has a" capability between unrelated classes.__
  * Interface is used when you have scenario that all classes has same structure but totally have different functionality.
    * For API contracts
  * Abstract class is used when you have scenario that all classes has same structure but some same and some different functionality.
    * For partial implementation

## Quiz Questions and Answers
* __Purpose of interface?__
  * It allows you to create a common parent type for classes that have the same properties, but those classes are already in different places in the class hierarchy and are not otherwise direct super classes or subclasses of each other.
* __If you have two classes that have the same properties, but the classes do not follow the "is-a" relation with each other, what should you do?__
  * Create a common parent type for the two classes that has those common properties. The parent type can be a class, abstract class, or an interface.
* __What rule must you follow when placing a non-static method in an interface?__
  * All non-static methods in an interface must be public, non-static method stubs.
* Suppose we have the following class definition: `public class A implements B {` and suppose we have two variable declarations: `A a;`, `B b;` __Which of the following two assignment statements are legal?__
```
   a = b;
   b = a;
```
  * Only `b = a`; Every object of type A is also type B, but not every object of type B is a type A. The other assignment requires an explicit typecast.
* __Can an interface be a true type or a current type?__
  * Current type only. You can not instantiate an interface using the new operator, and so the interface cannot be a true type. You can typecast to an interface.

## Introduction
Some object-oriented languages such as C++ allow a class to have more than one parent.  For example, class C can extend both classes A and B. This means C will inherit methods from both A and B.  __What if both A and B have a method m(), and inside class C we call method m()?  Whose method is called, A's or B's?__

This becomes really tricky if A and B both extend a super class X.  Suppose X has method m() and A and B both override m(). Now consider:  `X x = new C();`
* `x` has current type `X` so `x.m()` is a legal method call. But the __true type `C`__ determines which version of m is called.  __So which version is it, A's or B's?__

__In Java, each class can only extend one other class.__  Thus there is no ambiguity.  We can get something like multiple inheritance by allowing a class to implement more than one interface.

## Why Do We Need Multiple Inheritance?
Consider the Shape hierarchy from the last lecture. What if we want to add a `RegularPolygon` to the hierarchy?
* A RegularPolgon is-a Polygon
* A Square is-a RegularPolygon
* A Rectangle is not a RegularPolygon
* A Hexagon is a RegularPolygon

There is no way to correctly place RegularPolygon in the tree hierarchy for the classes and have all the "is-a" relations correct.

Instead, let use create `RegularPolygon` as an __interface.__ Now, we can have classes like `Octagon` and `Hexagon` extend Polygon, and have `Octagon`, `Hexagon` and `Square` "implement" `RegularPolygon`.


## Java Interfaces

A Java interface is a non-primitive type like a class, but it __cannot contain instance methods, fields, or constructors.__

__Specifically, an interface can contain:__
- static final fields
- static public nested types
- static methods
- abstract public instance methods (method stubs)
- starting in Java 9, private methods will be allowed

__The main purpose is to contain public abstract methods.__

To create an interface:

```
        public interface MyInterface {
          void methodStub1(int x, int y);

          int methodStub2();
        }
```

* since all methods stubs in an interface have to be `public` and `abstract`, we can drop the `public abstract` part
* An interface can extend 0 or more interfaces.  (You place "extends ..." just like you do with a class, and for multiple interfaces, you separate them with commas.)

To use an interface in a class: a class can `implement` 0 or more interfaces.

```
public class MyClass implements MyInterface {
   // here MyClass inherits the abstract methods methodStub1 and methodStub2
   // because a class cannot have abstract methods, we must override these method stubs with methods with bodies
}
```

Here is another example:

```
public class Square extends Rectangle implements RegularPolygon {
  ....
}
```

* If you will implement __more than one interface__, separate the interface names with commas.
* Implementing an interface is exactly like extending a class.
* So, a class that implements an interface __inherits all the methods (or in this case method stubs) of the interface.__
  * A class (that is not abstract) cannot contain method stubs.  So, the class __must override each of the method stubs from the interface.__

Note how this simplifies the multiple inheritance problem above.  __A class may inherit a method stub from more than one parent, but it can only inherit an instance method that contains a body from its class parent.__  

* NOTE: Students tend to get mixed up on "extends" vs. "implements".  They mean exactly the same thing. Java uses "implements" for the specific situation where we are indicating that a class as an interface as a supertype. All other situations where we are indicating a supertype, we use "extends".


## Interface Example

We created the RegularPolygon interface
```
	public interface RegularPolygon {

  The first thing we added is a method to compute the area of a regular polygon.  There are two ways we could do that.  Since interfaces allow static methods, we did so:

	public static double areaOfRegularPolygon(RegularPolygon p) {
	   ... a formula using p.getNumberAngles() and p.getSideLength()
	}
```

We could also make a __non-static abstract method__, but give it a default method body:

```
	public default double getArea() {
	    ... a formula using this.getNumberAngles() and this.getSideLength()
	}
```

* Note one benefit of interfaces: __we can create a method that only works on `RegularPolygons`__ by specifying that as an input. Since interfaces are a non-primitive type, we can make the current type of an object be a `RegularPolygon` as long as the true type implements the `RegularPolygon` interface.

Next, note that we need the instance methods `getNumberAngles` and `getSideLength`.  How do we make sure that `RegularPolygon` has them? __We add them as abstract methods.__

Since all instance methods of an interface MUST be public and abstract, the Java style is to drop those two modifiers (but you can keep them if you want).

```
	public interface RegularPolygon {

	  int getNumberAngles();

	  double getSideLength();

	  public static double areaOfRegularPolygon(RegularPolygon p) {
	     ... a formula to compute the area using getNumberAngles and getSideLength ....
	  }

	  public default double getArea() {
	     ... a formula using this.getNumberAngles() and this.getSideLength()
	  }
 	}
```

Now, anything that is a `RegularPolygon` will inherit the `getNumberAngles` and `getSideLength` method stubs and (if not an abstract class), must override them to make them normal methods.

Notice one trick I used.  `Polygon` already has `getNumberAngle` s as a normal method.  So, anything that implements `RegularPolygon` will already get a normal `getNumberAngles` method inherited from `Polygon` and so will not have to override the method stub.

The classes that implement `RegularPolygon` will still have to override the `getSideLength` method stub.

```
	public class Hexagon extends Polygon implements RegularPolygon {

	    private double sideLength;

	    public Hexagon(double sideLength) {
	      super(6);
	      this.sideLength = sideLength;
	    }

	    public double getSideLength() {       <-- this method is required by RegularPolygon so I decided to name my getter/setters appropriately
	       return sideLength;
	    }

	    public void setSideLength(double sideLength) {
	       this.sideLength = sideLength;
	    }

	    public double perimeter() {       <- this method is required by Shape
	      return getNumberAngles() * getSideLength();
	    }

	    public double area() {            <- this method is require by Shape, but we have a static method in the interface for the area
	      return RegularPolygon.areaOfRegularPolygon(this);    <-  remember that "this" is a variable storing the object this method is acting on
              -OR-
	      return this.getArea();
	    }
	}
```
