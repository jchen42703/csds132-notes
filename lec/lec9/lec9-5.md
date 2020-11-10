# Linked Lists and Loops

__Looping through a linked list has its own special form. We need a "node pointer" to point to remember where we are in the list.__

We also need to decide if we should end pointing to the last node of the list, or after the last node of the list.

Example, create a method inside `LinkedList` that returns the number of elements stored in the list.

```
	public int length() {
	  int count = 0;
	  LLNode<T> nodeptr = getFirstNode();
	  while (nodeptr != null) {
	    count++;
	    nodeptr = nodeptr.getNext();
	  }
	  return count;
	}
```

Notice the behavior of the above code:  we start the `nodeptr` at the first node of the list, and each time through the loop, we increment the node pointer to the next node of the list.

Here is another loop example with linked lists.  Inside the `LinkedList` class, let us write a method `addToEnd`.
This is almost the same as the loop we wrote for `length()`, but the stopping condition is different.  Can you see why?

```
	public void addToEnd(T element) {
	  LLNode<T> nodeptr = getFirstNode();
	  while (nodeptr.getNext() != null) // when loop exits, nodeptr will point to the last node of the list
	    nodeptr = nodeptr.getNext();
	  nodeptr.setNext(new LLNode<T>(element, null));
	}
```

There is one case where this method will crash with a `NullPointerException`: if the list is empty, `getFirstNode()` will return null, and `nodeptr.getNext()` will throw a `NullPointerException`.

The error is caused by the list having no elements, so we have to make a special case for that.

```
	public boolean isEmpty() {
	  return getFirstNode() == null;
	}

	public void addToEnd(T element) {
	  if (isEmpty())
	    addToFront(element);
	  else {
	    LLNode<T> nodeptr = getFirstNode();
	    while (nodeptr.getNext() != null) {
	       nodeptr = nodeptr.getNext();
	    }   // when loop exits, nodeptr will point to the last node of the list
	    nodeptr.setNext(new LLNode<T>(element, null));
	  }
	}
```

NOTE: We should use `isEmpty()` and not `length()` to test for an empty list.  Note that `length()` is __not__ a fast operation. It has to run through the entire list to calculate the length!

On the other hand, `isEmpty()` is fast because it just tests first. We could have used `if (getFirstNode() == null)` directly, but it is better to abstract this idea into its own method.

Checking to see if the list is empty is very likely something that we will want to do in lots of places, so a nice helper method will make the code easier to read and maintain.  

## An example using recursion.

Let us create a method `lengthFromHere` inside the `LLNode` class that _returns the length of the list_ __after__ this node.
The method will use the recursion technique.  This is a simple way to come up with an algorithm, but it is not the most efficient to run.

To come up with the recursive algorithm, you just need to consider two cases:  what is the answer if this is the last node of the list, and what is the answer if it is not?

```
public int lengthFromHere() {
  if (this.getNext() == null)     // there are no nodes after this one
    return 0;
  else                            // otherwise, ask the next node what the length after it is, and add 1
    return getNext().lengthFromHere() + 1;
}
```
Recursion is a very simple way to code, and it is efficient in some languages, but unfortunately it is not efficient in Java.
  * __Why?__
    * In Java, a stack frame is placed on the stack with each method call.  __If our linked list is really long, the recursion will place so many frames on the stack that it will use up the memory.__
    * Still, recursion is good if you know that the length of the recursion will not be too long. So, it is a good technique to master so you have it as a possibility when faced with a tough problem.
