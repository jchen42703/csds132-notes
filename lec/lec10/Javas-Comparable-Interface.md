# Java's Comparable Interface
* An object is __comparable__ if it can be ranked by `>` or `<`.
* Java indicates what types are comparable by having them implement the `Comparable` interface.

## Comparable Interface
* Only has one method `compareTo`
  * Input must have generic type `T` in `Comparable<T>`
  * Returns negative, zero, or positve
  * i.e. `"A". compareTo("B");` returns `-1`
* Comparing Strings
  * Not really doing alphabetical comparisons, but kinda
  * i.e. `"zebra.compareTo("yeti");` returns `1` because `z` is after `y`
  * but `"A".compareTo("a");` returns `-32`
    * Java considers lowercase and alphabetical different from each other.

## Example with `Employee`
Let's try to compare employees to order them by name!
```
public class Employee extends Object implements Comparable<Employee> {
  ...
  /**
    * Compare this employee with the input employee and order them by name.
    * @return negative, 0, positive if this employee ordered before, the same as, or after the input employee
  */
  public int compareTo(Employee employee) {
    return this.getName().compareTo(employee.getName());
  }
}
```

## Example with `LinkedList`
Create a method that inserts (in order) elements into a sorted `LinkedList`. So, the elements must be comparable.
```
public class LinkedList<T> {
  ...
  public void insertInOrder(T element) {
    // 1. the element goes at front of list
    if (isEmpty() || (element.compareTo( getFirstNode().getElement())) < 0) {
      ...
    }
  }
}
```
We can't do `element.compareTo` because not all types `T` have the `compareTo` method.

Let's try this:
```
public class LinkedList<T> {
  ...
  public static <S extends Comparable<S>> void insertInOrder(S element, LinkedList<S> list) {
    // 1. the element goes at front of list
    if (list.isEmpty() || (element.compareTo( list.getFirstNode().getElement())) < 0) {
      list.addToFront(element);
    }

    // 2. the element goes after the front of the list
    else {
      LLNode<S> nodeptr = list.getFirstNode();
      while (nodeptr.getNext() != null && nodeptr.getNext().getElement().compareTo(element) < 0) {
        nodeptr = nodeptr.getNext();
        // we stop when the next element > element or we are at the last element
        nodeptr.setNext(new LLNode<>(element, nodeptr.getNext()))
        // note: LLNode<> is a shortcut because the type is inferred from the surrounding code (i.e. the header)
      }
    }
  }
}
```
There is a problem:
* The generic type for `Comparable` in `<S extends Comparable<S>>` should not be `S` because we won't be able to use `insertInOrder` and the `compareTo` statements within it with super classes.
  * For example, if we try to insert `insertInOrder` with a super class `HourlyEmployee`, it won't work because `HourlyEmployee` inherits the `implements Comparable<Employee>` but `HourlyEmployee` doesn't match `Employee`.
* So the fix is to just do:
  * `public static <S extends Comparable<? super S>> void insertInOrder`
  * Lets you compare not only `S` but also super classes of `S`
