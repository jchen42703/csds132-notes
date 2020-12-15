import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private LLNode<T> head;
    private int length;

    public LinkedList() {
        this.head = null;
    }

    /**
     * @return the head
     */
    public LLNode<T> getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(LLNode<T> head) {
        this.head = head;
        setLength(length());
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    public boolean isEmpty() {
        return getHead() == null;
    }

    public void addToEnd(T element) {
        if (isEmpty())
            setHead(new LLNode<T>(element, null));
        else {
            LLNode<T> nodeptr = getHead();
            while (nodeptr.getNext() != null) {
                nodeptr = nodeptr.getNext();
            } // when loop exits, nodeptr will point to the last node of the list
            nodeptr.setNext(new LLNode<T>(element, null));
        }
    }

    public int length() {
        int count = 0;
        LLNode<T> nodeptr = getHead();
        while (nodeptr != null) {
            count++;
            nodeptr = nodeptr.getNext();
        }
        return count;
    }

    public T deleteNextNode(LLNode<T> node) {
        T removedElement = node.getNext().getElement();
        node.getNext().setNext(null);
        node.setNext(node.getNext().getNext());
        return removedElement;
    }

    public T delete(int index) {
        // assumes index is valid
        int count = 0;
        LLNode<T> nodeptr = getHead();
        while (nodeptr != null) {
            if (count == (index - 1)) {
                deleteNextNode(nodeptr);
            } else {
                count++;
                nodeptr = nodeptr.getNext();
            }
        }
        return null;
    }

    public void reverse() {
        LLNode<T> prev = null;
        LLNode<T> current = getHead();
        LLNode<T> next = null;

        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        setHead(prev);
    }

    public T get(int index) throws NoSuchElementException {

        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty.");
        }

        if (index > getLength()) {
            throw new NoSuchElementException("The index is larger than the length of the list.");
        }

        int count = 0;
        LLNode<T> nodeptr = getHead();
        while (nodeptr != null) {
            if (count == index) {
                return nodeptr.getElement();
            } else {
                count++;
                nodeptr = nodeptr.getNext();
            }
        }
        return null;
    }

    public LLNode<T> getLLNodeAtIndex(int index) throws NoSuchElementException {

        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty.");
        }

        if (index > getLength()) {
            throw new NoSuchElementException("The index is larger than the length of the list.");
        }

        int count = 0;
        LLNode<T> nodeptr = getHead();
        while (nodeptr != null) {
            if (count == index) {
                return nodeptr;
            } else {
                count++;
                nodeptr = nodeptr.getNext();
            }
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(getHead());
    }

    /**
     * Iterator class for LinkedList
     */
    private class LinkedListIterator implements ListIterator<T> {
        // cursor to the current node position
        private LLNode<T> nodeptr;

        public LinkedListIterator(LLNode<T> firstNode) {
            nodeptr = getHead();
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

        /**
         * Checks if there is another element behind the current element.
         * 
         * @return true if there is another element behind the current element, false if
         *         there isn't another element (when iterating in the backwards
         *         direction)
         */
        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        /**
         * Returns the current element in the list and moves the cursor position
         * backwards.
         * 
         * @return the current (now, the next after this method is called) element in
         *         the list element in the list
         */
        @Override
        public T previous() {
            throw new UnsupportedOperationException();
        }

        /**
         * Removes from the list the last element that was returned by next() or
         * previous(). A.k.a. the lastAccessed element.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Inserts an element into the list immediately before the current element. If
         * the list contains no elements, the new element becomes the sole element on
         * the list.
         */
        @Override
        public void add(T e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T e) {
            throw new UnsupportedOperationException();
        }
    }

    public static class LLNode<T> {
        private LLNode<T> next;
        private T element;

        public LLNode(T element, LLNode<T> next) {
            this.element = element;
            this.next = next;
        }

        /**
         * @return the element
         */
        public T getElement() {
            return element;
        }

        /**
         * @param element the element to set
         */
        public void setElement(T element) {
            this.element = element;
        }

        /**
         * @return the next
         */
        public LLNode<T> getNext() {
            return next;
        }

        /**
         * @param next the next to set
         */
        public void setNext(LLNode<T> next) {
            this.next = next;
        }
    }
}