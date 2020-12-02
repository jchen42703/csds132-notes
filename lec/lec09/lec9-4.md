# Abstract Data Types
An abstract data type is a data structure that guarantees certain behaviors to the user, but it keeps its implementation details hidden.

By hiding the implementation details, we are able to change them if we discover a better way to do things without breaking the code that uses the data structure. We also can prevent code that uses the data structure from accidentally breaking the data structure.

__Examples:__
* __Strings are an ADT.__  You are guaranteed certain behavior such as accessing a character from a location and appending strings, but you are not told how they are implemented (though they are probably implemented as an array of chars).
* __JFrames are an ADT.__  You are guaranteed certain behavior such as changing its size, displaying it on the screen, but you do not know exactly how the Java Swing developers chose to implement the window.
---
* We will create the `LinkedList` as an abstract data type.  The list is going to be a list of `LLNodes`, but we will keep the details away from the users of the `LinkedList` so that code that uses the `LinkedList` can not accidentally break the list.

# Creating a LinkedList class
A `LinkedList` will store a list of `LLNodes`. The only field we need is to store the node that is the first node of the list. We call this the "first" or "head" of the linked list.

There are several ways we can implement the linked list. We decided that if a list is empty, its front should be a null pointer, to indicate that there are no nodes in the list.

Another option would be to create a special node that acts as a "caboose" to the linked list.  No implementation technique is wrong as long as the list works properly.

If we correctly create the `LinkedList` as an abstract data type, we should be able to switch between implementations and
code that uses the `LinkedList` class will still operate exactly the same.

Note that the `LinkedList` will need to use a generic to specify the type that will be stored in the list, and we want each LLNode in the list to use the same generic.

```
	public class LinkedList<T> {
	  private LLNode<T> firstNode;

	  public LinkedList() {
	    firstNode = null;
	  }
```

Now, let create a method to add an element to the front of the list.
```
	  public void addToFront(T element) {
            firstNode = new LLNode<T>(element, firstNode);
	  }
```
If we want to allow polymorphism and extending for this class, we should use getter/setter methods for `firstNode`, but __we don't want to make them public because that would let any code using the `LinkedList` (rather than any code that "is" a LinkedList) to need to understand how the head of the linked list works.__  In particular, the getter/setter methods return an `LLNode`.
  * __Having code outside the `LinkedList` know about `LLNodes` would violate the "keep implementation details hidden" nature of an abstract data type.__

The solution is to make the getter/setter methods __"protected".__  Recall that protected means it can be used in this class or any class that extends this class.  (Why would private not work? Hint: getter/setter's are used so to making extending the class easier.)
  * Note: There is literally `protected` keyword for this, but not mentioned in the lecture.
```
	  public void addToFront(T element) {
            setFirst(new LLNode<T>(element, getFirst());
	  }
```
How about testing if a list is empty?
```
	public boolean isEmpty() {
 	  return getFirst() == null;
	}
```
How about removing an element at the the front?
```
	public T removeFromFront() {
```
We need to move the front from the current node to the next node. But before we do that, we should save the value stored in the front so we can return it at the end.
```
	public T removeFromFront() {
	  T save = getFirstNode().getElement();
	  setFirst(getFirstNode().getNext();
	  return save;
	}
```
But, what if the list is empty, then the front will be null, and we will get a `NullPointerException`!

It is not a good idea to throw a NullPointerException because that will not mean anything to the programmers who are using our code.

The problem is that the list is empty.  That should not have to deal with a null value that is specific to our implementation of the linked list. We will still throw an exception, but now we will throw a more meaningfully named exception: `NoSuchElementException`

**************
## Short Note on Exceptions
__We will cover them in more detail later.__
*  An exception is another way to return from a program.  It is "less elegant" than the normal return so we use it for special situations such as errors
*  You have already seen some exceptions: `NullPointerException`, `ArrayIndexOutOfBoundsException`
*  Exception is just a class of Java so it can be created like any other class, but to "return" with an exception value, we "throw" the exception
*  __There are 2 kinds of exceptions: checked exceptions and unchecked exceptions.__
  * The difference is that for checked exceptions, we have to be explicit about how our code is handling the exception.
* For example, if our code is going to throw the exception, we must indicate that in the method header.
---
The `NoSuchElementException` is in the `java.util` class, and it is a checked exception so we must indicate that the method may throw it in the method header
```
	public T removeFromFront() throws NoSuchElementException {
	  if (isEmpty())
	    throw new NoSuchElementException();
	  else {
	    T save = getFirstNode().getElement();
	    setFirst(getFirstNode().getNext());
	    return save;
	  }
	}

	public boolean isEmpty() {
	  return getFirstNode() == null;
	}
```
