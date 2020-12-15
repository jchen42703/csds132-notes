# Java Collections
* Classes for storing data
  1. `LinkedList` (build in double linked list)
    * considers both next/previous elements
    * still slow to access elements, but fast to resize
  2. `ArrayList` (wrapper for `Array`)
    * provides automatic resizing
    * creates new array for us and copies the data
    * slow to resize, but fast access to elements
    * still slow to insert in the middle of array

# For each Loop
```
for (T value: Iterable<T>) {}
```
For example:
```
LinkedList<Integer> list = new LinkedList<Integer>();
list.add(1);
list.add(2);

for (Integer x: list) {
  System.out.println(x);
}
```
