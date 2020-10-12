# Two important methods of `Object` and Overriding methods

## Employee Example
An example class: Employee
  The Employee will have names, salaries, and numbers.

  We use the proper Java coding:
```
	public Employee {              // the extends Object is automatically added

	  private String name;
	  private double salary;
	  private int number;

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }
```

* Note that the body of `getName` returns `name`.  We also could have written "return this.name;", and it would be exactly the same.  __Java automatically prefixes "this."__ in front of a method call or field access if you do not provide it.
* __Distinguising between fields and local variables with the same name (i.e. `name` in `setName`):__
  * Note that the body of `setName` has another variable (the input parameter) that is called `name`.  This is ok because we can distinguish the input parameter variable "name" from the field "this.name". Here we have to explicity use the "this." because Java does not automatically add it.  Why?  Because there is already a variable called name, and since that declaration is closer, Java will assume "name" just refers to the input parameter.
* Why did we do the assignment in `setName`?  Input parameters as well as any variable declared inside a method only exists as long as the method is run.  So once setName is done, the name variable along with its contents are lost.  __To keep the contents around, we copy it into the field that exists as long as the object does.__


### The Constructor

In class, we decided that we want to require an employee to have a name a salary and a number. However, we decided that employee numbers may be assigned automatically. We do this by writing a constructor method that takes a String as input.  In writing a constructor, we __lose Java's default constructor that takes no input.__

```
	public Employee(String name, double salary) {
	  this.name = name;
	  this.salary = salary;
	  this.number = ?????
	}
```

How do we get the number to be assigned automatically?
We need the class itself to save the next employee number to be used. That means we need a static field!

```
 	private static int last = 0;

	public Employee(String name, double salary) {
	  this.name = name;
	  this.salary = salary;
	  this.number = Employee.lastEmployeeNumber + 1;
	  Employee.lastEmployeeNumber = this.number;
	}
```

## Overriding methods of Object

`Employee` extends `Object` and inherits the methods of `Object.`  There are two important methods we should override because their default behavior is not very useful.

### String toString()
* Returns a string representing the object

In our case, we want it to be the employee number followed by the employee name

```
public String toString() {
  return getNumber() + ": " + getName();
}
```

### boolean equals(Object o)
* __Equals v. ==__
  * If you use the `==` operator on non-primitive values, it is __comparing the addresses of the values.__
    * Thus, == only returns true if two objects are the exact same object.
    * For example, "Hi" == "Hi" __only returns true if the two Strings are really the same string stored at the same location in memory.__  If you create a String s that contains "Hi" but is stored at a different location in memory, then s == "Hi" will return false.
  * The `equals` method is provided to examine the contents of the object to determine if they are __structurally equal.__
    * This is why we should always use `equals` when comparing the contents of two `String` objects.
* If not overridden, the default `equals` method in `Object` just does an `==` test.


---
## Overriding
* __To override any method, we must exactly match the method parameter signature.__
* Many Java textbooks and on-line references do not give the correct way to write an `equals`.
* If you search online, you will often find the suggestion to use
  ```
  public boolean equals(Employee e) {
  ```
  but this __does not match the parameter signature of the method in `Object`__, and so it is __overloading__, and not overriding!

###  __Why is overloading instead of overriding a problem?__
* It's bad practice because the behavior will change depending on the __type of the input object.__ We want the `equals` method to be all encompassing or explicitly not encompassing.
* We can change the version of the method we call by typecasting:
  ```
  Employee e1 = new Employee(10, "Mekayla");
  Employee e2 = new Employee(10, "Mekayla");
  ```
  * `e1.equals(e2)`  and   `e1.equals((Object)e2)` will call different versions of the method!  One for input `Employee`, and one for input `Object`. (In fact, the first would return true, and the second will return false!)

### `@Override`
* It's easy to overload instead of override in Java, so we use the annotation `@Override` to explicitly tell the compiler that we are overriding and if we overload, a compiler error will be thrown.
* A correct equals method that says this Employee is equal to the input if the input is an Employee with the same employee number and the same name:

```
	@Override
        public boolean equals(Object o) {
          if (o instanceof Employee) {
            Employee e = (Employee)o;
            return this.getNumber() == e.getNumber() && this.getName().equals(e.getName());
          }
          else
            return false;
        }
```
### `instanceof`
* `instanceof`:  returns true if object that is the left operand can be typecast as the type that is the right operand.  (I.e. is the object's true type equal or "below" the given type in the hierarchy.)
* If we do not use `instanceof`, the method will generate a TypeCastException when typecasting a non-Employee to Employee.
* Now we only have __one__ version of `equals`!
* `getClass()` v. `instanceof`
  * `getClass()`: gets true type
  * `instanceof`: encompassing all typecastable types
* In general, we want to code using the current type as much as possible and ignoring the true type.
