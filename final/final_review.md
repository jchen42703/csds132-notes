# Final Review

## 1. Basics of Java

1. Definitions:
  * `JVM`: Java Virtual Machine
  * `JRE`: Java Runtime Environment
  * `JDK`: Java Development Kit

2. Access modifiers specify how a method, class, variable, or data member can be called by another class within the same class, within the same package subclass, within the same package non-subclass, within a different package subclass, and within a different package non subclass.
  * `public`: can be accessed by all.
  * `private`: can only be accessed within the same class
  * `protected`: can be accessed in all cases except for in a different package non-subclass.
  * `default`: can only be accepted within the same package

3. `==` is generally used to compare primitive types and `.equals()` is generally used to compare non-primitive types. This is because `==` compares addresses for non-primitive-types, which is often not very helpful. On the other hand, `.equals()` can be overridden and be made to compare the non-primitive type in the way the user wants.

4. Checked exceptions must be caught with `try {} catch (Exception e) {}`, while unchecked exceptions can just be thrown with `throws`. This is because checked exceptions are checked at compile-time, while unchecked exceptions are checked at runtime.

5. If the variable is a field, then its accessibility depends on its access modifier. However, if it is a local variable, then that access modifier is default and the variable's scope ends when the encompassing compound statement it is declared in is finished executing.

6. `public static void main(String[] args)`
  * `public`: Can be accessed in the same class, same package classes, and different package classes.
  * `static`: the `main` method is stored within the class such that there is only 1 copy of this method for the encompassing class
  * `void`: the method does not return a value
  * `main()`: Is the method name
  * `String[]`: The type of the argument `args` must be an array of `String`.
  * `args`: The argument name

## 2. Classes

1. Primitive types have values stored in memory, while non-primitive types have addresses or references stored in memory.

2. Methods, fields, constructors can go inside classes. Also, there are getters and setters to access the `private` fields for proper OOP programming. Also, each field and method should be appropriately commented.

3. Instance fields/methods can only be called after an instance of the class has been initialized. On the other hand, static fields/methods are stored within the class and can be called without having to initialize an instance of the class. There is only one copy of the static field/method that all classes of that type share.

4. Enums are a type that store constants (`public final static`). Enums cannot extend other classes and cannot be used to create objects (like normal classes can).

## 3. Arrays and Strings

1. `array[index]`
2. First: `array[0]`, last: `array[array.length - 1]`
3. `string.charAt(index)`
4. `StringBuilder` allows you to create strings without having to create a new string everytime with the `+` operator. This conserves memory, and `.toString()` can convert a `StringBuilder` instance to a `String`.
5.

## 4. Memory in Java
1. Memory
  * `The heap stores all classes and objects.`
    * The super class of the class
    * All constructors of the class.
    * All methods (static and non-static) defined in the class.
    * All static fields declared in the class.
    * Any classes that are inside this class.
    * Class instances (true type, instance fields)
    * `new` allocates space in the heap for the class instance, calls the constructor and returns the address of the new object
  * `The stack frame is used for method calls.`
    * Stores local variables and method parameters.
    * Keeps track of the execution order.
2. The garbage collector is the automatic management of memory that Java uses. When objects in memory are no longer needed, the garbage collector automatically finds those objects and deletes them from memory.

3. Garbage collection allows Java to be more memory efficient. For instance, variables created in a compound objects in local variables that would otherwise not be deleted would be deleted at the end of that stack frame.

## 5. Hierarchy, Abstract Classes, and Interfaces

1. To override a method, you must keep the same parameter signature and method name. To overload a method, you have the same method name, but can change the parameter signature.
  * parameter signature is the __type, number, and order of the input values.__
  * You use overriding when you want to change only the body of the method.
  * You use overloading when you want to change the method parameters and the body.

2. Final, private, and static methods cannot be overridden.

3. The `super` keyword allows you to access parent class methods.
  * in the constructor, you can call `super(...)` to call the superclass constructor to reduce repeat code.
  * you can also call `super.methodName()` to call a superclass method. Note, you cannot do this with `private` methods.

4. A class can only extend one class and implement as many interfaces as you need.
  * `public class A implements C,D {...}`

5. Abstract classes cannot be instantiated.

6. Interfaces should be used to handle multiple inheritance scenarios or when you want to inherit, but inherited fields are not important. Abstract classes are used for primarily `is-a` and `has-a` relations and for establishing common behavior among child classes.

7. The Comparator interface is useful when you want to compare two objects. The Iterator interface is useful when you to make the object be iterable.

## 6. Linked Lists
For 1-4, see [`practice_code/LinkedList.java`]('practice_code/LinkedList.java')

5. A `DoubleLinkedList` can be traversed in both directions. The disadvantage is that it requires an extra pointer for each operation to be maintained. See: https://www.geeksforgeeks.org/doubly-linked-list/

6. Arrays should be used over a LinkedList when you need to frequently access elements within the array at specific indices. LinkedLists should be used over Arrays when you need to frequently change the size of the list (adding, deleting and inserting elements).

## 7. Generics

1. A generic type is a place holder (usually a single capital letter) that indicates that the type will be specified later.

2. Restricting Generics
  * `T extends S` means that T must be S or narrower.
  * `T super S` means that T must be S or wider.

3. When you do not care about the type, you can use a wildcard `?`. This is usually when you don't utilize the generic type within the method body or class OR when it is not important to the overall functionality of said method or class.

4. `public static <T extends Comparable<T>> T max(T a, T b, T c)`
  * `public`: can be accessed by same package classes and different package classes.
  * `static`: only one copy of this method is stored within the class and can be called without instantiation of said class
  * `<T extends Comparable<T>>` means that `T` must be `Comparable` with its own type (so must be a child class of Comparable)
  * `T`: the returned value should be of type `T`
  * Implementation of the `max` method is in `practice_code`.
