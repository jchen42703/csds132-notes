# Arrays and Generic Types
All you can do is __declare an Array with a generic.__
* You cannot create an array with a generic.
* You cannot create an array of a parameterized type.

# Why would you want to declare an `Array` with a generic?
```
// from lab
public class ArrayStuff {
  public static <T extends Comparable<? super T>>void insert(T[] list, int n, T k)
  int index;
  for (index = n; index > 0 && k.compareTo(list[index - 1]) < 0; index--) {
    list[index] = list[index - 1];
  list[index] = k;
  }
}
```
Also, we can't do:
```
// array of LinkedList<String>
LinkedList<String>[] lists = new LinkedList<String>[10]
```
But we can do:
```
public class StringList extends LinkedList<String> {};
StringList[] list = new StringList[10];
```
