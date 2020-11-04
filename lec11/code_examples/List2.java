import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * A class to represent a linked list of nodes.  
 * The nodes are a non-static nested class.
 * The list is Iterable to indicate that we can iterate over the data in the list.
 */
public class List2<T> implements Iterable<T> {
  /** the first node of the list, or null if the list is empty */
  private Node firstNode;
  
  /**
   * Creates an initially empty linked list
   */
  public List2() {
    firstNode = null;
  }
  
  /**
   * Returns the first node.
   */
  protected Node getFirstNode() {
    return firstNode;
  }

  /**
   * Changes the front node.
   * @param node  the node that will be the first node of the new linked list
   */
  protected void setFirstNode(Node node) {
    this.firstNode = node;
  }

  /**
   * Add an element to the front of the linked list
   */
  public void addToFront(T element) {
    setFirstNode(new Node(element, getFirstNode()));
  }
  
  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getFirstNode() == null);
  }
  
    /**
   * Remove and return the first node of the list
   * @return the first node of the list
   * @throws NoSuchElementException if the list is empty
   */
  public T removeFromFront() throws NoSuchElementException {
    if (!isEmpty()) {
      T save = getFirstNode().getElement();
      setFirstNode(getFirstNode().getNext());
      return save;
    }
    else
      throw new NoSuchElementException();
  }
  
  /**
   * insert an element into a sorted list, insert into the correct location
   * @param element the element to insert
   * @param list a linked list with the elements already in sorted order
   */
  public static <S extends Comparable<? super S>> void insertInOrder(S element, List2<S> list) {
    // 1. the element goes at the front of the list
    if (list.isEmpty() || (element.compareTo(list.getFirstNode().getElement()) < 0))
      list.addToFront(element);
      
    // 2. the element goes after the front of the list
    else {
      List2<S>.Node nodeptr = list.getFirstNode();
      while (nodeptr.getNext() != null && nodeptr.getNext().getElement().compareTo(element) < 0)
        nodeptr = nodeptr.getNext();
      // we stop when the next element is larger than element or we are at the last node
      nodeptr.setNext(list.new Node(element, nodeptr.getNext()));
    }
  }
  
  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int lengthSoFar = 0;
    Node nodeptr = getFirstNode();
    while (nodeptr != null) {
      lengthSoFar++;
      nodeptr = nodeptr.getNext();
    }
    return lengthSoFar;
  }
  
  /**
   * Determines whether an element is stored in the list
   * @param element  the element to search for in the list
   * @return true if and only if the parameter element is in the list
   */
  public boolean contains(T element) {
    Node nodeptr = getFirstNode();
    while (nodeptr != null) {
      if (nodeptr.getElement().equals(element))
        return true;
      nodeptr = nodeptr.getNext();
    }
    return false;
  }
  
  /**
   * Returns an iterator that iterates, or loops, over the data of the list
   * @return an iterator for the linked list
   */
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node nodeptr = List2.this.getFirstNode();
      
      public boolean hasNext() {
        return nodeptr != null;
      }
      
      public T next() throws NoSuchElementException {
        if (!hasNext())
          throw new NoSuchElementException();
        
        T save = nodeptr.getElement();
        nodeptr = nodeptr.getNext();
        return save;
      }
    };
  }
  
  
  
  
  /** A class that implements a linked list node as a non-static nested class */
  public class Node {
    
    // stores the element
    private T element;
    
    // stores the next node in the list
    private Node next;
    
    /**
     * Creates the linked list node
     * @param element the element to store in the node
     * @param next the next node in the list
     */
    public Node(T element, Node next) {
      this.element = element;
      this.next = next;
    }
    
    /**
     * Retrieve the element
     * @return the element
     */
    public T getElement() {
      return element;
    }
    
    /** 
     * Retrieve the next node in the list
     * @return the next node
     */
    public Node getNext() {
      return next;
    }
    
    /**
     * Change the element in the node
     * @param element the new element to store
     */
    public void setElement(T element) {
      this.element = element;
    }
    
    /**
     * Change the next node in the list
     * @param node the node that should be the next node after this one
     */
    public void setNext(Node node) {
      this.next = node;
    }
  }
}