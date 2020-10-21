# Linked Lists and Generic Types

## Summary
* __Linked lists__ are lists of nodes that point to the the next chronological node.
  * Easily to increase/decrease size as needed. (insertions/deletions easy + quick to do)
  * Fast access only to a few elements.
  * Linked list is already in the Java API.
  * Use `Array` when data is fetched often, but use `LinkedList` when insertion/deletions are often
* __Generic Type__
  * __A generic type is a place holder (usually a single capital letter) that indicates that the type will be specified later.__ You can specify more than one generic type if needed (remember, __generic types will not accept another class just because it is narrower__)
  ```
  public class LLNode<T> {
    public class Box<K,T> {
  ```
  * The generic type is used by the compiler so __it only affects the current type.__

## Arrays v. Linked Lists
* __Arrays:__ A collection of variables of the same type stored in contiguous memory.
  * __Benefits:__  Very fast access to any arbitrary element.
  * __Downside:__  Can not change size after created and can't insert or delete without needing to shift values.
    * To change the size, we must create a new array and copy all the data over.

* __Linked Lists:__ A data structure that stores values of the same type
  * __Benefits:__  Can easily increase or decrease size as needed.  Can easily insert or delete at any location.
  * __Downside:__  Fast access to only a few elements.
---
* A linked list is formed of "nodes".  Each node contains an element and a pointer to the next node of the list.
The __first node__ of the list is called the __"head"__ of the list.
```
          +---+---+    +---+---+    +---+---+
  head -> | 1 | *-+--> | 2 | *-+--> | 3 | *-+--x
          +---+---+    +---+---+    +---+---+
```
* NOTE:  `LinkedList` is a class of the Java API.  However, we are going to implement our own class so you can understand how linked lists work.
  * The `LinkedList` class of the API is identical to our class except that each box also has an array pointing to the box that comes before it. Such a linked list is sometimes called a __"double linked list".__
---

## Generic Type
* __What type should the element be?__  Whatever type we want to store in the list.  This seems to imply that we will need to create a different linked list class for each possible type we want to store, but starting in Java 5, we can specify a __"generic type."__  

__A generic type is a place holder (usually a single capital letter) that indicates that the type will be specified later.__
```
	public class LLNode<T> {
```
indicates that there is a generic type associated with `LLNode`.  __When we create an instance of the `LLNode`, we will specify what the type `T` is.__

A class __can have as many generic types as you wish associated with it.__  For example, if had a class called Box with two generic types, we would declare the class as:    `public class Box<K,T> {`   where `K` and `T` are the two letters used as placeholders for the two generic types.

Inside `LLNode`, we can use `T` just like any other type:

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

The use of `LLNode<T>` in the `setNext` and the constructor and the next field forces Java to require that the `LLNode` that next points to must hold the same type as this node holds.  __That way we can force every node of the list to hold the same type.__
  * This was impossible before generics.  Instead, we would need to either specify a separate list class for each type or we would have to store `Object` and then use lots of `instanceof` expressions and typecasts to enforce that only one type of Object is stored.

When we create an instance of `LLNode`, we will specify what the type will be.  For example, we can store `JFrame`:
```
   LLNode<JFrame> node = new LLNode<JFrame>(new JFrame(), null);
```
or we can store `String`:
```
   LLNode<String> node2 = new LLNode<String>("Hi", null);
```

### Types and generics:
The generic type is used by the compiler so it only affects the current type.  The compiler makes sure that the types that you specify match.
```
     new LLNode<String>("Hi", null);   <- legal!  "Hi" is type String and that matches the specified generic.

     new LLNode<String>(new JFrame(), null);  <- ILLEGAL!  JFrame() is not a String.  The generic was specified to be String, and so the element's type must match.

     LLNode<String> node = new LLNode<Object>("Hi", null);  <- ILLEGAL!  The type of variable node is LLNode<String> so only LLNode's that have the generic specified as String can be assigned to the variable.
```
```
     LLNode<Object> node = new LLNode<String>("Hi", null);  
```
`ILLEGAL!`  The type of variable node is `LLNode<Object>` so only `LLNode`'s that have the generic specified as Object can be assigned to the variable. __It does not matter that String is narrower than Object!__

__WHY IS THIS NOT ALLOWED?__ If this were allowed, we would have a big problem because the following line is legal because all the types match:
```
	    node.setNext(new LLNode<Object>(new JFrame(), null));
```
`node` has the generic specified as `Object`, and so the `setNext` takes values of type `LLNode<Object>`. However, `node` was storing an `LLNode<String>`, and now that `LLNode<String>` has an `LLNode<Object>` assigned to it's next field.  __A violation of the types!__

Why didn't node know it was storing an `LLNode<String>`? __Because the generics only apply to the current type (the compile-time type).__  When the code is running, Java does not
know what the current type is, only the true type.  The true type does not include the generic.  The true type is just `LLNode`.
