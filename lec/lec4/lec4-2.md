# Constructors
## Summary
* Constructors are methods called by the `new` operator (during the allocation of space and initialization of the object).
* Java automatically creates a default constructor with no input if no constructor is already written for a class.
* We can write multiple constructors for a class through __method overloading__ (same method name, but different parameter signature).
  * The `"parameter signature"` is the __type, number, and order of the input values.__

## What is a Constructor?
* Recall how the `new` operator works:
`Die d = new Die();`
  1. Allocates space for the object.
  2. Initializes the object based on the inputs to `new` -> it does this by __calling an appropriate constructor method__ with the given input.
  3. Returns the address (memory location) for the object.

* A constructor is a special method that is called by the `new` operator to initialize a class.
* A constructor must have the same name as the class and no return type:

  ```
  public ClassName(inputs) {
  ```

* A constructor is __not inherited by classes__ that extend this class.  Each class must define its own constructors.

## Default Constructors
If you do not write a constructor, Java provides a default constructor that takes no input.

## Writing a Constructor
Here is a good constructor to specify the size of the die:

```
private int currentValue = 1;
private final int numberOfSides;

public Die(int numberOfSides) {
  this.numberOfSides = numberOfSides;
}
```

### Distinguishing between Fields and Local Variables With the Same Name
Note that the parameter variable and the instance field have the same name "numberOfSides". Java allows local variables inside methods to have the same name as fields.  
Inside the method, `numberOfSides` refers to the closest definition (the parameter).  If we wanted the field, we needed to use `this.numberOfSides`. Java does not allow two fields with the same name nor two local variables (including input parameter variables) with the same name.

---

Now we can use the constructor to create different die of different sizes:

```
	Die d8 = new Die(8);
	Die d20 = new Die(20);
```

### What happens to the default constructor?
However, this code will now give an error:  
`Die d6 = new Die();`

It worked before, what happened?
* Expected and input because of constructor (the default constructor was not created)

IMPORTANT: If you do not define a constructor, Java provides a default one that takes no input. Once you create a constructor for your class, you lose the default constructor.

It would be nice to still have a default constructor, so we must write our own.

## Method overloading
* __Idea:__ We can create multiple constructors through method overloading.
* We can __create multiple methods of the same name (including constructor methods) as long as their "parameter signature" differs.__
  * The `"parameter signature"` is the __type, number, and order of the input values.__
* Since we have a constructor that takes an int:  `Die(int)`, we can write another constructor as long as the input is not a single int.
  ```
    public Die() {
      this.numberOfSides = 6;
    }
  ```
