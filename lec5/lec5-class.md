# The Java API (Application Program Interface)
An API is typically part of any pre-defined software package that you can use in your programs. It lists how you are to use the predefined programs.

The Java API lists all the pre-defined classes (and other types) in Java.
In the API you can find:
  * the package the class is in (what you need to import)
  the header for the class
  * a list of all non-private inner classes, constructors (what new uses to initialize the object), fields, and methods

Keep the API bookmarked because we will be using it a lot in the course.



## RULES FOR O-O CODING:
1. Create private fields
2. Create public getter/setter methods so that classes that extends this class can change behavior if they want.
3. Everywhere in our code that uses a value, we use the getter/setter methods instead of the fields.
4. Except in the constructor where, if we want a field to be initialized, we use the field directly.

## O-O TYPE RULES
1. Every instance is many types at the same time.
2. The "true" type is what the instance is at creation (from: new XXX() -> the true type is XXX).
3. The "current" type is what the instance is typecast as at this particular part of the code.
4. An instance may only call methods and fields that exist for the "current" type.
5. However, the version of the method used is the version of the "true" type.  (This applies only to instance methods!  Fields and static methods are still used from the current type)


Today we will see why those rules greatly simplifies our coding.

## Example 1
Let's create a method in `Employee` that compares employee's by how much money they make.

We created the method `earnsMoreThan` that compares the salaries of this employee to another `employee.o`
Suppose we have this:

```
public boolean earnsMoreThan(Employee e) {
  return this.getSalary() > e.getSalary();
}
```

__There is a problem: whoever created Employee assumed that all employee's have salaries.__ This is not the case, but we often have situations in Java where the person creating a type does not think of every situation and makes incorrect assumptions on how it is used. Because the `earnsMoreThan` method is following proper O-O coding (using the getter methods instead of the fields), __it is easy for other classes to adjust so that their classes properly work (can just change their getter method accordingly).__

We will have every employee type define for itself what "salary" means. For example, an hourly employee can decide that a salary is hours worked * rate per hour. A sales employee can decide salary is number of sales * commission, and so forth.

`HourlyEmployee` will "define" how salary works by overriding the salary method:

```
public double getSalary() {
  return this.getHoursWorked() * this.getHourlyRate();
}
```

 Because the __true type version of an instance method is always used, the hourly employee will always report its salary as the product of its hourly rate and hours worked.__
   - it does not matter if we typecast the hourly employee
   - it does not matter if we try to set the salary of the hourly employee
 It just works!

###  Use the fields directly:
```
public boolean earnsMoreThan(Employee e) {
  return salary > e.salary;
}
```

* If we did this, it would take more work to get hourly employee to work with the method.
* Since fields are not inherited and cannot be overridden, we are going to have to make sure hourly employee calls the inherited `setSalary` method everytime that its `hourlyrate` is changed or its number of hours worked is changed.
* Not only is that going to mean changes in at least two different places, it means that if `Employee` changes how `earnsMoreThan` is computed, we will have to change `HourlyEmployee`!

__TLDR;__ everytime you choose to not follow the O-O guidelines, you make it more challenging for other coders to extend and use your class. So if you want to violate the O-O guidelines, you should think carefully to make sure you have a good reason to do so.
