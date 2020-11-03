# Iterables and Generic Types

## Summary
* The `Iterable` and `Iterator` interfaces help you loop over data types that contain data.
* `Iterator`: used for types that help you loop over the data type that contains the data
  * In the notes below, we created `LinkedListIterator` which helps iterate over a `LinkedList`
  * __Must override:__
    * `boolean hasNext()`: returns `true` if there are more elements in the list
    * `T next()`: returns the __next element in the list__, and "iterates" so the next time we call next(), we get the next element of the list
* `Iterable`: contains the iterator; The data type that contains the data should implement this interface.
  * __Must override:__
    * `Iterator<T> iterator()`: This method __returns the iterator for the abstract data type.__
* Make sure your generics match, especially for the `iterator()` method to match the generic type of the `LinkedList`

## Loops and Abstract Data Types
 We can try to think of everything that a program that uses our `LinkedList` will want to do, but that is impossible.

Instead, other programs will need to write their own loops to run through the linked list. This creates a problem: to run through the linked list requires having a node pointer, but __if our linked list is to be a abstract data type, we do not want to require outside classes to have to deal with the linked list implementation details.__

Java provides a pair of interfaces that let us provide a means for other code to loop through our linked list while still hiding the implementation details.  These interfaces are `Iterable` and `Iterator`.

* `Iterable`:  indicates we can loop through the data stored in an instance
* `Iterator`:  the instance that performs the routines needed to do the loop

## The Iterator interface

The `Iterator` interface is used to generalize the idea of a loop.

Basically, something that is type `Iterator` (implements `Iterator`) can be used as a loop.

The `Iterator` interface has two non-default instance methods (that means we have to override these):
* `boolean hasNext()` -> returns true if there are more elements in the list
* `T next()` -> returns the next element in the list, and "iterates" so the next time we call next(), we get the next element of the list
  * (Notice from the API page that the Iterator interface takes a generic, so when we implement it, we need to specify the generic. Here we will specify the generic of `Iterator` by creating a generic in our class that implements the `Iterator`.)

Assuming these two methods have been properly overridden, we can now write a loop using just the Iterator interface.

```
Iterator<?> iterator =  ?????                   // pretend we have some way to get an iterator
	while (iterator.hasNext()) {
	  System.out.println(iterator.next());
	}
```
Note that the iterator lets us write loops for an abstract data type.

If we create an Iterator for our `LinkedList` class, the programmer writing the loop only needs to know how Iterators work and not any details about the `LinkedList` nodes.

Recall how we write a loop for a `LinkedList`:
```
LLNode<?> nodeptr = list.getFront();      // assuming getFront is public!
while (nodeptr != null) {
  System.out.println(nodeptr.getElement());    // print each element
        nodeptr = nodeptr.getNext();
}
```

To write the `Iterator` class, we need to go back to our loop using `LLNode`s.  The key parts of the loop are:
  1. __initalization:__  We have to set up the loop.  Here the code is `LLNode<T> nodeptr = getFirstNode()`
  2. __condition:__ We need to indicate when loop should continue and when it should stop:  `nodeptr != null`
  3. __retrieval:__ We need to get the next element from the list `nodeptr.getElement()`
  4. __increment:__ We need to move to the next element in the list `nodeptr = nodeptr.getNext()`

Now, we need to create a class for our linked list that implements the `Iterator` interface.
* What should the `hasNext` do?  The condition of the loop (`nodeptr != null`).
* What should the `next` do?  Both the retrieval (`return nodeptr.getElement()`) and the increment (`nodeptr = nodeptr.getNext()`)
* That leaves the initialization step (`nodeptr = getFirstNode()`).  Where should that happen?  The constructor!

```
public class LinkedListIterator<T> implements Iterator<T> {
    private LLNode<T> nodeptr;

      public LinkedListIterator(LLNode<T> firstNode) {
      nodeptr = firstNode;
    }

    @Override
    public boolean hasNext() {
      return nodeptr != null;
    }

    @Override
    public T next() {
      T element = nodeptr.getElement();
      nodeptr = nodeptr.getNext();
      return element;
    }
  }
}
```

Notice the use of the generic.  __`Iterator` takes a generic (we can see that by looking at the API page for Iterator.)__
So, we declare a generic in the `LinkedListIterator` header (the `LinkedListIterator<T>`), and now that we have a generic `T` declared, we use it when we implement `Iterator` (`Iterator<T>`). __That forces the value returned by the `next` method to have the type of what we are iterating over.__  (The type that is stored in the linked list.)

(You may notice that we violated the OO-rules of using getter/setter methods.  I chose not to do that so that the connection between the Iterator methods and the linked list loop is easy to see.  We probably should add in getter/setter methods.  Otherwise, creating a class that extends `LinkedListIterator` will be more challenging to get correct.)

However, the API for Iterator says that we need to throw a `NoSuchElementException` if `next()` is called when there are no more elements in the linked list. We did not have time to add that in lecture, but it is easy to add an if statement to __see if the nodeptr is null, and if it is throw the exception.__

Now that we created the a class that implements Iterator, how do we connect that class to the `LinkedList` class?  By using the `Iterable` interface.


## The `Iterable` Interface
* `Iterable` is an interface of the API. The `Iterable` type represents an object that __contains data that we can loop (or iterate) over.__
* By having the `LinkedList` class implement `Iterable`, we are indicating that we can iterate over the elements of the Linked List. __Every class that is an abstract data type and can store multiple elements should implement the `Iterable` interface.__
* Notice (from the API page) that the `Iterable` interface takes a generic. __We will use the same generic that is stored in the `LinkedList`.__
* The `Iterable` interface has 1 (non-default) method:
```
Iterator<T> iterator()
```
  * This method __returns the iterator for the abstract data type.__ Again, we are going to make sure that the generic used by iterator matches the generic stored in the linked list. And how do we do that?  By having the `Iterable` interface use exactly the same generic.

```
	public class LinkedList<T> implements Iterable<T> {

   Now we have to override the iterator method inherited from Iterable:
	  @Override
	  public Iterator<T> iterator() {
              // we need to return an appropriate object that implements Iterator
	  }

   What should the iterator method return?  An instance of the LinkedListIterator that we created above!

	  @Override
	  public Iterator<T> iterator() {
	    return new LinkedListIterator<T>(getFirstNode());
	  }
```
Note that when we override a method, the name and parameter signature must be identical.
The return type, if non-primitive, is allowed to be narrower than the overridden method's return type.
So we could also write:
```
	  @Override
	  public LinkedListIterator<T> iterator() {
	    return new LinkedListIterator<T>(getFront());
	  }
```

## Using the Iterable/Iterator interfaces:

Now, we can write a loop outside of the `LinkedList` class.  (We could not before because the `getFirstNode()` method is protected.)

__Ex:__
```
	   LinkedList<String> list = new LinkedList<String>();
	   list.addToFront("Cleveland");
	   list.addToFront("Cincinnati");
	   list.addToFront("Columbus");


	   Iterator<String> it = list.iterator();
	   while (it.hasNext())
               System.out.print(it.next() + " ");

           System.out.println();
```

## Foreach loops:
`Foreach` loops are a Java shortcut for Iterable classes and for arrays.
* The form of a `foreach` loop is:          
  ```
  for (T i : Iterable<T>)
  ```
  and it reads as
  > foreach type T in iterable

* For example, if list is a `LinkedList<Integer>`, we could have:
  ```
  for (Integer i : list)
  ```
  which reads as "foreach Integer in list"

  * Here is an example:
  ```
	LinkedList<Integer> list = new LinkedList<Integer>();
	list.addToFront(1);
	list.addToFront(2);
	list.addToFront(3);
	for (Integer x : list) {
	  System.out.print(x + " ");
	}
  ```
  Note that the foreach loop is just a shortcut.  Java takes the foreach loop on Iterable and coverts it to use the iterator:
  ```
	for (Double element : list)
  ```
  is automatically converted by the compiler to:
  ```
  	for (Iterator<Double> it = list.iterator(); it.hasNext(); ) {
   	  Double element = it.next();
  ```

* This was not covered in lecture, but the foreach loop also works with arrays even though array are not Iterable type.
```
	double[] a = {1, 2, 3, 4, 5};
	for (double x : a) {
	  System.out.println(x * x + " ");
	}
```
* The `foreach` loop on array is also a shortcut that is automatically converted by the compiler to a normal for loop
```
	for (String s : a)
   is the same as
	for (int index = 0; index < a.length; index++) {
	  String s = a[index];
```
