import java.util.Iterator;

/******************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque
 *
 *  <p>A double-ended queue or deque (pronounced "deck") is a generalization
 *  of a stack and a queue that supports adding and removing items from either
 *  the first or the last of the data structure.
 *
 *  The deque implementation in this class uses a doubly linked list to store
 *  items. This allows for constant time operations for adding and removing
 *  items from either end of the deque. The iterator iterates over items in
 *  order from first to last.</p>
 *
 *  % java Deque
 *  ------------------------------
 *  Added item 1 to the first
 *  Added item 2 to the first
 *  Added item 3 to the last
 *  Size: 3
 *  Items in deque (first to last):
 *  2
 *  1
 *  3
 *  Removed item from first: 2
 *  Removed item from last: 3
 *  Size: 1
 *  Items in deque (first to last):
 *  1
 *  Removed item from first: 1
 *  Size: 0
 *  Is deque empty? true
 *  Caught exception while removing from first: Deque is empty.
 *  Caught exception while removing from last: Deque is empty.
 *  Caught exception adding item to the first: Item cannot be null.
 *  Caught exception adding item to the last: Item cannot be null.
 *  Caught exception while removing item: Operation not supported.
 *  Caught exception while iterating: No more items to return.
 *  ------------------------------
 ******************************************************************************/

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    /**
     * Node class to represent a node in a doubly linked list.
     * Having a previous pointer allows for constant time removal of
     * the last element, with the added benefit of being able to iterate
     * in reverse order. However, this requires extra space for an additional
     * pointer.
     */
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    /**
     * Construct an empty deque.
     */
    public Deque() {

        first = null;
        last = null;
        size = 0;
    }

    /**
     * Check if the deque is empty (contains no elements).
     *
     * @return true if the deque is empty, false otherwise
     */
    public boolean isEmpty() {

        if (first == null && last == null) {

            return true;
        }

        return false;
    }

    /**
     * Return the number of items on the deque.
     *
     * @return the number of items on the deque
     */
    public int size() {

        return size;
    }

    /**
     * Add an item to the first of the deque.
     *
     * @param item the item to be enqueued to the first of the deque
     * @throws IllegalArgumentException if the item is null
     */
    public void addFirst(Item item) {

        if (item == null) {

            throw new IllegalArgumentException("Item cannot be null.");
        }

        final Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        size++;

        if (size == 1) {

            last = first;
        }
        else {

            oldFirst.prev = first;
        }
    }

    /**
     * Add an item to the last of the deque.
     *
     * @param item the item to be enqueued to the last of the deque
     * @throws IllegalArgumentException if the item is null
     */
    public void addLast(Item item) {

        if (item == null) {

            throw new IllegalArgumentException("Item cannot be null.");
        }

        final Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        size++;

        if (size == 1) {

            first = last;
        }
        else {

            oldLast.next = last;
        }
    }

    /**
     * Remove and return the item from the first of the deque.
     *
     * @return the item removed from the first of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public Item removeFirst() {

        if (isEmpty()) {

            throw new java.util.NoSuchElementException("Deque is empty.");
        }

        final Item item = first.item;
        first = first.next;

        if (first != null) {

            first.prev.next = null;
            first.prev = null;
        }
        else {

            last = null;
        }

        size--;

        return item;
    }

    /**
     * Remove and return the item from the last of the deque.
     *
     * @return the item removed from the last of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public Item removeLast() {

        if (isEmpty()) {

            throw new java.util.NoSuchElementException("Deque is empty.");
        }

        final Item item = last.item;
        last = last.prev;

        if (last != null) {

            last.next.prev = null;
            last.next = null;
        }
        else {

            first = null;
        }

        size--;

        return item;
    }

    /**
     * Obtain iterator to iterate over items in order from first to last.
     *
     * @return an iterator over items in order from first to last
     */
    public Iterator<Item> iterator() {

        return new DequeIterator();
    }

    /**
     * Iterator class to iterate over items of a deque in order from first to
     * last.
     */
    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        /**
         * Check if there are more items to return.
         *
         * @return true if there are more items to return, false otherwise
         */
        public boolean hasNext() {

            return current != null;
        }

        /**
         * Return the next item in the deque.
         *
         * @return the next item in the deque
         * @throws java.util.NoSuchElementException if there are no more items
         */
        public Item next() {

            if (current == null) {

                throw new java.util.NoSuchElementException(
                        "No more items to return.");
            }

            final Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {

            throw new UnsupportedOperationException("Operation not supported.");
        }
    }

    /**
     * Test the deque implementation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        System.out.println("Added item 1 to the first");
        deque.addFirst(2);
        System.out.println("Added item 2 to the first");
        deque.addLast(3);
        System.out.println("Added item 3 to the last");
        System.out.println("Size: " + deque.size());

        System.out.println("Items in deque (first to last):");
        for (int item : deque) {

            System.out.println(item);
        }

        System.out.println("Removed item from first: " + deque.removeFirst());
        System.out.println("Removed item from last: " + deque.removeLast());
        System.out.println("Size: " + deque.size());

        System.out.println("Items in deque (first to last):");
        for (int item : deque) {

            System.out.println(item);
        }

        System.out.println("Removed item from first: " + deque.removeFirst());
        System.out.println("Size: " + deque.size());
        System.out.println("Is deque empty? " + deque.isEmpty());

        // Test exceptions - remove from first
        try {

            deque.removeFirst();
        }
        catch (java.util.NoSuchElementException e) {

            System.out.println("Caught exception while removing from first: "
                                       + e.getMessage());
        }

        // Test exceptions - remove from last
        try {

            deque.removeLast();
        }
        catch (java.util.NoSuchElementException e) {

            System.out.println("Caught exception while removing from last: "
                                       + e.getMessage());
        }

        // Test exceptions - add null item to first
        try {

            deque.addFirst(null);
        }
        catch (IllegalArgumentException e) {

            System.out.println("Caught exception adding item to the first: "
                                       + e.getMessage());
        }

        // Test exceptions - add null item to last
        try {

            deque.addLast(null);
        }
        catch (IllegalArgumentException e) {

            System.out.println("Caught exception adding item to the last: "
                                       + e.getMessage());
        }

        // Test exceptions - remove from empty deque using iterator
        try {

            Iterator<Integer> it = deque.iterator();
            it.remove();
        }
        catch (UnsupportedOperationException e) {

            System.out.println("Caught exception while removing item: "
                                       + e.getMessage());
        }

        // Test exceptions - iterate over empty deque
        try {

            Iterator<Integer> it = deque.iterator();
            it.next();
        }
        catch (java.util.NoSuchElementException e) {

            System.out.println("Caught exception while iterating: "
                                       + e.getMessage());
        }
    }
}
