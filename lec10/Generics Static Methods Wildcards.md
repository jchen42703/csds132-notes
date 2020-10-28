# Generics, Static Methods, Wildcards
`public class LinkedList<T> {}`

```
LinkedList<String> = new LinkedList<String>()
```
* Generic specified when the instance is created
* Generic can only be used with __instance fields, methods, and nested types.__

## Generic Type in Method Header
* If we want to use a generic with a static method, we must declare a generic for the method.
* There are two places where you can declare the generic:
  1. In the class or interface header.
  2. __In the method header, right before the return type__
    * `public static <T> void printList(LinkedList<T>) {}`
    * `public static <T> T get(LinkedList<T>, int index)`
      * Returns type `T`

## Generic Wildcard
* when specifying a parameter type, if you don't care what the type is, you can use `?` <-- That's a wildcard.

For example:
```
import java.util.LinkedList;
public class ListStuff {
  /** method to print the contents of a LinkedList */
  public static <T> void printList(LinkedList<T> list) {
    for (T element: list) {
      System.out.println(element);
    }
  }
}
```
Here, we can actually use a wildcard because we're only printing out values and every `Object` has the `toString` method that `println` calls. Hence, the type doesn't matter and we can use a wildcard.
```
public static void printList(LinkedList<?> list) {
  ...
  for (Object element: list) {...}
}
```
