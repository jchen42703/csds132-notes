# Java Memory Model, Part 1

## The Java Memory Model:

Java (and all programming languages) split the memory available for the program into two parts: the heap and the stack.

* The `stack` is __organized memory__ used for method calls.  In Java, the default is to set aside about 1MB for the stack (this can be changed when you launch Java).
* The `heap` is __unorganized memory__ used for data that needs to be more permanent.  In Java, all classes and objects are stored in the heap.

For every class your program uses, a class "object" is stored in the `heap`.  The class "object" stores:

* The super class of the class
* All constructors of the class.
* All methods (static and non-static) defined in the class.
* All static fields declared in the class.
* Any classes that are inside this class.

For every object (instance of a class) created by your program (e.g. by the new operator), space for the __instance is allocated in the heap.__
The instance record stores:
* The true type of the object.
* All non-static fields of all the polymorphic types of the object. (this class, the class this class extends, etc.)
  * The fields are stored in order starting from Object and down to the true type.

### Example of the Heap and Classes
For example, we have a program that uses our Employee and `HourlyEmployee` classes.

In the heap, Java will place "objects" for the Employee and `HourlyEmployee` classes.  There will also be an "object" for the Object class since that class is extended by Employee. There will also be an "object" for String since String is used in these classes.

The `Employee class` "object" in the heap stores:
* __super class:__ the address of the Object class "object"
* __constructors:__ there are three overloaded constructors:  `Employee(String)`, `Employee(String, double, int)`
* __methods:__  `getName(), setName(String), getNumber(), getSalary(), setSalary(double), earnsMoreThan(Employee), toString(), equals(Object)``
__static fields:__ `lastEmployeeNumber`

The `HourlyEmployee` class "object" in the heap stores:
* __super class:__ the address of the `Employee` class "object"
* __constructors:__ there is currently only one constructor: `HourlyEmployee(String)`
* __methods:__ `getHourlyWage(), setHourlyWage(double), getHoursWorked(), setHoursWorked(double),  getSalary()`
* __static fields:__ none

## Creating an object:
`Employee e1 = new Employee("Wonyoung")`

Space is allocated for an instance in the heap.  The instance object contains:
* true type:  the address of the Employee class "object"
* the fields of Object:  (whatever they are)
* the fields of Employee: name, number, salary

### How does __new__ work:
1. allocates space in the heap for the instance
2. calls the constructor for Employee.  In this case we call the Constructor that takes (String) as input.
   *  the first thing the constructor for Employee does is call super(), the constructor for Object.  That initializes all the Object fields.
   * next the constructor initializes the Employee fields to 0, null or false - this is important because salary is not being set in the constructor body.
   * now we execute the body of the constructor (if you look at the code, it assigns the name and number fields as well as the lastEmployeeNumber static field)
3. returns the location in the heap of the instance (this is the value that gets assigned to variable e1)

### Another example:
`Employee e2 = new Employee("Brian", 110000.00, 5)`
We overloaded the `Employee` constructor.  So it finds the appropriate constructor by looking for the one that matches the "parameter signature"  (String, double, int).

1. Allocate space for the object.  The object will have to hold the true type address, all the non-static fields of Object, and the 3 non-static fields of Employee.
2. Run Employee's constructor to initialize those fields.  In this case, it runs the constructor that takes (String, double, int) as inputs
	* The first line of this constructor is `"super()"` so the constructor of Object that takes no input is run to initialize the fields of Object
  *  The fields of `Employee` are given the default initializations of 0, 0.0, null, or false because we have no explicit initializations
	*  The rest of the constructor code is run to copy the input name address to the name field, copy the input salary to the salary field, copy the input number to the number field and update the `lastEmployeeNumber` static field.
3. The address of the Employee object is returned so it can stored in the variable e2.


### Another example:
`Employee e3 = new HourlyEmployee("Ben")`

Java does the following on "new HourlyEmployee("Ben"):
1. Space is allocated for an instance in the heap.  The instance object contains:
  * true type:  the address of the HourlyEmployee "object"
  * the fields of Object:  (whatever they are)
  * the fields of Employee: name, number, salary
  * the fields of HourlyEmployee: hourlyWage, hoursWorked
2. Run HourlyEmployee's constructor to initialize those fields
	  * The first line is "super(name)" so Employee's constructor that takes a single String as input is run
	    * The first line of Employee's constructor is "super()" so Object's constructor is run
	    * The fields of `Employee` are given their default values.
	    *  Now the rest of `Employee`'s constructor body is run to initialize the name and number fields.
	  * The fields of `HourlyEmployee` are given their default values
3. The address of the `HourlyEmployee` object is returned so it can be stored in variable e2.

## Polymorphism:  
* An object is multiple types: every type from its true type up the hierarchy to Object
  * __true type (run-time type):__ what the object is (as created by new).
    ( The true type never changes.
    * The true type of a value in a piece of code can often not be determined just by reading the code.  __(This is why "run-time type" is often used.  You can only know what the true type is by running or tracing the code and seeing what gets stored.)__
    * The true type determines what version of a method is executed.
  * __current type (compile-time type):__ how the object is being used - what has it typecast to?
    * The current type constantly __changes with each typecast.__
    * You can always tell what the current type is by reading the code -> just look at the variable declaration, any typecast, or the __return value of the method__  (This is why "compile-time type" is often used. The compiler can figure out the type by reading the code.)
    * The current type determines __whether a method call is legal and which fields can be accessed.__

Remember that the computer does not care about types (everything it sees is binary numbers).
We, the programmer, use types as a safety feature.  By setting a type, we are indicating what the value represents, and the Java compiler (Java being a strictly typed language) verifies that we are using the types correctly.

That means, the compiler verifies that we do not call a method unavailable to the type nor access a field unavailable to the type.

## Polymorphism and methods:
Typecasting has __no effect on what version of an instance method__ is called.  The instance method version is __always the version of the true type.__

Typecasting does affect __what field is accessed and what static method is called.__


Consider the two variables/instances: `Employee e1` and `Employee e3`. Both variables have type `Employee` so whatever is stored in those variables will have current type `Employee`.

However, we used the code:
```
Employee e1 = new Employee("Wonyoung")
Employee e3 = new HourlyEmployee("Ben")
```
So, because we can see the `new` operators, at this point it time we have the address to an instance with true type `Employee` in `e1`, and the address of an instance with true type `HourlyEmployee` in `e3`.

### What happens when we call a method?
`e1.setSalary(200000.0)`

(When the code was compiled, the compiler used the type of e1 to verify that `setSalary` with a single double is valid for that type.  i.e. is valid for Employee.)

__When Java sees a method call, Java does the following:__
1. Evaluates the left side of the dot to get an address.  (Here the needed address is stored in e1.)
2. Goes to that address in the heap (it is an object), and goes to that __object's true type__ in the heap. (Here, it will go to the Employee class.)
3. Run's through the class's list of methods to find one that matches.  (It will find `setSalary(double)` in this list.)
4. __If the method is not found,__ if goes to the class's `super` class, and repeats step 3.
In this case, it finds the `setSalary` method of Employee. It runs `this.salary = salary.`

The current type of "this" is `Employee` so it uses the salary field of Employee and it stores 200000.0 in that field.

#### Another example:
`e3.setSalary(250000.0)`

The compiler uses the type of `e3` to verify that the method `getSalary` with no input is valid for that type.

__When Java sees a method call, Java does the following:__
1. Evaluates the left side of the dot to get an address.  (Here the needed address is stored in e3.)
2. Goes to that address in the heap (it is an object), and goes to that object's __true type__ in the heap. (Here, it will go to the `HourlyEmployee` class. Note that the type of e3 does not matter.  It uses the address stored in e3 to find the method to run.)
3. Run's through the class's list of methods to find one that matches.  __(There is no such method in HourlyEmployee.)__
4. If the method is not found, if goes to the class's `super` class, and repeats step 3.
In this case, it finds the setSalary method of Employee.  It runs `this.salary = salary.`
The current type of "this" is `Employee` so it uses the salary field of `Employee` and it stores 250000.0 in that field. __(Note that if `HourlyEmployee` also had a salary field, that field would NOT be set by the `setSalary` method.  It is the field of Employee that gets set.)__

#### Another example:
`e1.getSalary()`

__Java does the following:__
1. Evaluates the left side of the dot to get an address.  (Here it is stored in e1.)
2. Goes to that address in the heap (it is an object), and goes to that object's __true type__ in the heap. (Here, it will go to the Employee class.)
3. Run's through the class's list of methods to find one that matches.  (It will find `getSalary` in this list.)
4. Step 4 is not needed because the method was found
In this case, it finds the method `getSalary()` in Employee, and so it returns the value stored in the salary field of Employee, and it returns 200000.0.

#### Another example:
`e3.getSalary()`

__Java does the following:__
1. Evaluates the left side of the dot to get an address.  (Here it is stored in e3.)
2. Goes to that address in the heap (it is an object), and goes to that object's true type in the heap. (Here, it will go to the `HourlyEmployee` class.)
3. Run's through the class's list of methods to find one that matches.  (It will find getSalary in this list.)
4. Step 4 is not needed because the method was found
In this case, it finds the method `getSalary()` that was overridden in `HourlyEmployee`, and so it returns the product of `getHourlyRate()` and `getHoursWorked()` and returns 0.

__Key point:__ the overridden method is ALWAYS run because it is the __true type of the instance that determines what method is run.__

## Polymorphism and static methods:
When you access a static method, you place the __class name or an instance__ to the left of the dot.  If you place an instance, __the Java compiler replaces it with the current type name.__ Since the class name is used, Java goes directly to that class to access the method.

## Polymorphism and fields:
The compiler uses the __current type__ to determine which field is accessed.  

When you typecast an `HourlyEmployee` as Employee, you are saying that you will be treating it as an Employee.  Java will only make the Employee part of the instance visible (that is the true type, Object's fields, and Employee's fields).  The fields of `HourlyEmployee` are still there, __but they can't be accessed when the current type is not `HourlyEmploye` (or something that extends `HourlyEmployee`.)__
