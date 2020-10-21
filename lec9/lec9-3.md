# Continuing with Linked Lists

## Insertion at ends of nodes in a Linked List
Recall the `LLNode` class:

```
	public class LLNode<T> {
	  private T element;
	  private LLNode<T> next;

	  public LLNode(T element, LLNode<T> next) {
	    this.element = element;
	    this.next = next;
	  }

	  public T getElement() {
	    return element;
	  }

	  public LLNode<T> getNext() {
	    return next;
	  }

	  public void setNext(LLNode<T> next) {
	    this.next = next;
	  }
	}
```

Now, let us create the linked list:
```
          +---+---+
  list -> | 1 | *-+--x
          +---+---+   
```
>  `LLNode<Double> list = new LLNode<Double>(1.0, null);`

Now, let us add 2 after 1.  Creating the node is the same, but we have to change the value of the first node's next pointer to point to the second node:
```
          +---+---+    +---+---+
  list -> | 1 | *-+--> | 2 | *-+--x
          +---+---+    +---+---+
```
> `list.setNext(new LLNode<Double>(2.0, null));`

Now, let us add 0 before 1.  We need to create a box that points to 1.  Where is 1's box currently stored?  In list.  Then we must change the value of list to point to the new box:
```
          +---+---+    +---+---+    +---+---+
  list -> | 0 | *-+--> | 1 | *-+--> | 2 | *-+--x
          +---+---+    +---+---+    +---+---+
```
>  `list = new LLNode<Double>(0, list)`

We can do this because the constructor also lets us `setNext` with the second argument. So, use the constructor when we want to insert one element before what `list` we currently have.

---
## Insertion between Nodes

Place 1.5 between 1 and 2 in the above list. We must be careful to do things in the right order, or we will break the list.
1. Create a new node for 1.5.
2. Move the next pointer for the 1.5 node to point to the 2 node.
3. Move the next pointer for the 1 node to point to the 1.5 node.
* __If we did step 3 before step 2, we would lose all access to the 2 node.__
* What is the name of the next pointer of 1? `list.next.next`
* To do step 2, we need to change the list's `next` pointer to `list.next.next`, i.e. what the 1's box is pointing at.
* To do step 3, we need to change `list.next.next` to point to the new box.

__Looks like:__
1. `LLNode<Double> newNode = new LLNode<Double>(1.5, null);`
2. `newNode.setNext(list.getNext().getNext());`
3. `list.getNext().setNext(newNode);`

__Or in one line:__
```
list.getNext().setNext(new LLNode<Double>(1.5, list.getNext().getNext());
```
