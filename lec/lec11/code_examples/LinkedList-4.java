import java.util.NoSuchElementException;

/**
 * A class to represent a linked list of nodes.  The list is Iterable to indicate that we can iterate over the data in the list.
 */
public class LinkedList<T> implements Iterable<T> {
  /** the first node of the list, or null if the list is empty */
  private LLNode<T> firstNode;
  
  /**
   * Creates an initially empty linked list
   */
  public LinkedList() {
    firstNode = null;
  }
  
  /**
   * Returns the first node.
   */
  protected LLNode<T> getFirstNode() {
    return firstNode;
  }

  /**
   * Changes the front node.
   * @param node  the node that will be the first node of the new linked list
   */
  protected void setFirstNode(LLNode<T> node) {
    this.firstNode = node;
  }

  /**
   * Add an element to the front of the linked list
   */
  public void addToFront(T element) {
    setFirstNode(new LLNode<T>(element, getFirstNode()));
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
  public static <S extends Comparable<? super S>> void insertInOrder(S element, LinkedList<S> list) {
    // 1. the element goes at the front of the list
    if (list.isEmpty() || (element.compareTo(list.getFirstNode().getElement()) < 0))
      list.addToFront(element);
      
    // 2. the element goes after the front of the list
    else {
      LLNode<S> nodeptr = list.getFirstNode();
      while (nodeptr.getNext() != null && nodeptr.getNext().getElement().compareTo(element) < 0)
        nodeptr = nodeptr.getNext();
      // we stop when the next element is larger than element or we are at the last node
      nodeptr.setNext(new LLNode<>(element, nodeptr.getNext()));
    }
  }
  
  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int lengthSoFar = 0;
    LLNode<T> nodeptr = getFirstNode();
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
    LLNode<T> nodeptr = getFirstNode();
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
  public LinkedListIterator<T> iterator() {
    return new LinkedListIterator<>(getFirstNode());
  }

  
  /**
   * Remove every other node from the input list of nodes
   * @param list1 the first node of a list of nodes
   * @return the first node of the list of nodes that were removed
   */
  private static <S> LLNode<S> split(LLNode<S> list1) {
    LLNode<S> list2 = list1.getNext();
    LLNode<S> nodeptr = list2;
    LLNode<S> prevptr = list1;
    
    while (nodeptr != null) {
      prevptr.setNext(nodeptr.getNext());
      prevptr = nodeptr;
      nodeptr = nodeptr.getNext();
    }
    
    return list2;
  }
  
  /**
   * Code to merge two lists of sorted nodes into one list
   * @param list1  the first node of a sorted list of nodes
   * @param list2  the second node of a sorted list of nodes
   * @return the first node of the sorted list
   */
  private static <S extends Comparable<? super S>> LLNode<S> merge(LLNode<S> list1, LLNode<S> list2) {
    LLNode<S> finalList;
    
    // determine the first node of final list
    if (list1.getElement().compareTo(list2.getElement()) < 0) {
      finalList = list1;
      list1 = list1.getNext();
    }
    else {
      finalList = list2;
      list2 = list2.getNext();
    }
    
    // repeat choosing the node with the smallest value to add to the end of the list
    LLNode<S> endptr = finalList;
    while (list1 != null && list2 != null) {
      if (list1.getElement().compareTo(list2.getElement()) < 0) {
        endptr.setNext(list1);
        list1 = list1.getNext();
      }
      else {
        endptr.setNext(list2);
        list2 = list2.getNext();
      }
      endptr = endptr.getNext();
    }
    
    // one of list1 or list2 is empty, so stick the other on the end
    if (list1 == null)
      endptr.setNext(list2);
    else
      endptr.setNext(list1);
  
    return finalList;
  }
 
  /**
   * take the first node of a list of nodes, sort, and return the first node of the sorted list
   * @param list  the first node of a list of nodes to sort
   * @return the first node of the sorted list
   */
  private static <S extends Comparable<? super S>> LLNode<S> mergeSort(LLNode<S> list) {
    if (list.getNext() == null)
      return list;
    
    // 1. split the list
    LLNode<S> list2 = split(list);
    
    // 2. sort each half
    list = mergeSort(list);
    list2 = mergeSort(list2);
    
    // 3. combine the sorted lists and return
    return merge(list, list2);
  }
  
  /**
   * merge sort the linked list
   * @param list the linked list to sort
   */
  public static <S extends Comparable<? super S>> void mergeSort(LinkedList<S> list) {
    list.setFirstNode(mergeSort(list.getFirstNode()));
  }
}