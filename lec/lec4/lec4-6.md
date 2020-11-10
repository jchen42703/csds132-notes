# Constructor Calls

## Summary
* __Constructor calls:__
  * `super()`: possibly with input in the parentheses.  This calls (i.e. executes) the constructor of the parent class (the class this class extends).
  * `this()`:  possibly with input in the parentheses.  This calls a constructor of the same class.
  * Defaults to `super()` because the purpose of a constructor is to initialize an object and due to the principle of polymorphism, when we initialize a child class, we should first initialize itself as a parent class.
* use the `this()` constructor call when one constructor resembles another constructor and you want to avoid redundant/duplicate code.

## How Constructors Work:
1. The first line of a constructor must be a call to another constructor of another class (i.e. super class or this class).  (This is also the only place in the code were we can have a constructor call.)
  * __These are the two possibilities:__
    * `super()` <- possibly with input in the parentheses.  This calls (i.e. executes) the constructor of the parent class (the class this class extends).
    * `this()` <- possibly with input in the parentheses.  This calls a constructor of the same class.
  * If you do not explicitly have a constructor call as the first line of your constructor body, __Java automatically places super(), with no input, there.__
    * WHY?  Recall polymorphism. An object of type Employee is also type Object. An object of type GeometricFrame is also JFrame, Frame, Window, Container, Component, and Object.
      * __The purpose of the constructor is to initialize the object.  Before we can initialize the objects as Employee, it must first initialize itself as Object.__
2. The constructors do the following when they are run:
    1. The first line of the constructor that calls another constructor is executed.  (Recall that Java adds super() if you omit this line.)
    2. All fields of the instance are initialized.
    3. The rest of the constructor body is executed.

 Note point 2.2 above.  Java basically takes any assignment statements on your fields and places that code after the constructor call that is the first line of your constructor and before the rest of the constructor code.  This is important to remember for the situations where you care about the order that things are being done in your program.

## Example
Let's create a class that extends Employee:

```
	public class HourlyEmployee extends Employee {
	}
```

This class will not compile as written!  The reason is that we did not write a constructor so Java provided a default constructor.
The default constructor takes not input and calls super() with no input.  Something like this:

```
	   public HourlyEmployee() {
	      super();
	   }
```

Now we see why HourlyEmployee fails to compile.  The first line of the HourlyEmployee constructor is `super()`; `super()` calls the constructor of `Employee` that takes no input.  __But there is no constructor of Employee that takes no input.__ The `Employee` constructor requires a String and a double as input.  So, we have to have a String and double as a parameters to super();

One thing we could do is to create a constructor that calls super("Some Dumb String", 0); And the code will compile because the types not match.  However, this is does not fit what we want Employee to be. The purpose of the Employee constructor was to require a name for each Employee.  We should write our code for HourlyEmployee
to follow this idea, require a name as input, and then pass this name along to the Employee constructor.

Here is the correct code.

```
public class HourlyEmployee extends Employee {

  public HourlyEmployee(String name) {
    super(name, 0);
  }
}
```

`HourlyEmployee` inherits everything from `Employee`:

```
	> Employee e = new Employee("Harold", 1000)
	> e.geteNumber()
	1
	> e.getName()
	"Harold"
	> HourlyEmployee h = new HourlyEmployee("Joe")
	> h.getName()
	"Joe"
	> h.getNumber()
	2
```

### Using `this()`
`this()` calls a constructor of the same class.  __We can use it to avoid having duplicate code.__ Consider the two constructors of Employee:

```
public Employee(String name, double salary) {
   this.name = name;
   this.salary = salary;
   this.number = Employee.lastEmployeeNumber + 1;
   Employee.lastEmployeeNumber = this.number;
}

public Employee(int number, String name, double salary) {
   this.name = name;
   this.salary = salary;
   this.number = number;
   if (this.number > Employee.lastEmployeeNumber)
     Employee.lastEmployeeNumber = this.number;
}
```

Other than how they handle the employee number, the two are the same.  So we can have the first (the one that sets the employee number automatically) call the other (the one that sets it explicitly).

```
	public Employee(String name, double salary) {
	  this(Employee.lastEmployeeNumber + 1, name, salary);
	}
```

Now, if we need to change how employees are initialized, there is only one constructor body with the code that we have to change.
