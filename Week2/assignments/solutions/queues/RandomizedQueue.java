import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue
 *
 *  A randomized queue is similar to a stack or queue, except that the item
 *  removed is chosen uniformly at random from items in the data structure.
 *
 *  This implementation uses a resizing array. The iterator iterates over
 *  items in random order.
 *
 *  % java RandomizedQueue
 *  ------------------------------
 *  Is empty: true
 *  Added item to the queue: 1
 *  Added item to the queue: 2
 *  Added item to the queue: 3
 *  Added item to the queue: 4
 *  Size of queue: 4
 *  Items in queue (random order 1):
 *  4
 *  2
 *  1
 *  3
 *  Items in queue (random order 2):
 *  3
 *  1
 *  4
 *  2
 *  Sample item 1: 3
 *  Sample item 2: 1
 *  Removed item from queue: 3
 *  Removed item from queue: 1
 *  Size of queue: 2
 *  Is empty: false
 *  Caught exception while adding null item: Item cannot be null.
 *  Caught exception while removing from empty queue: Queue is empty.
 *  Caught exception while sampling from empty queue: Queue is empty.
 *  Caught exception while removing from iterator: Remove from iterator
 *  operation not supported.
 *  Caught exception while iterating: No more items to return.
 *  ------------------------------
 ******************************************************************************/

public class RandomizedQueue<Item> implements Iterable<Item> {

    // underlying resizing array
    private Item[] queue;

    // number of elements in queue
    private int qSize;

    /**
     * Construct an empty randomized queue of items.
     */
    public RandomizedQueue() {

        queue = (Item[]) new Object[1];
        qSize = 0;
    }

    /**
     * Check if the randomized queue is empty.
     *
     * @return true if the randomized queue is empty, false otherwise
     */
    public boolean isEmpty() {

        return qSize == 0;
    }

    /**
     * Return the number of items on the randomized queue.
     *
     * @return the number of items on the randomized queue
     */
    public int size() {

        return qSize;
    }

    /**
     * Add the item to the randomized queue.
     *
     * @param item the item to add to the randomized queue
     */
    public void enqueue(Item item) {

        if (item == null) {

            throw new IllegalArgumentException("Item cannot be null.");
        }

        // resize the underlying array if necessary
        if (qSize == queue.length) {

            resize(2 * queue.length);
        }

        // add the item to the next available slot
        queue[qSize++] = item;
    }

    /**
     * Remove and return a random item from the randomized queue.
     *
     * @return a random item from the randomized queue
     */
    public Item dequeue() {

        if (isEmpty()) {

            throw new java.util.NoSuchElementException("Queue is empty.");
        }

        // generate a random index between 0 and size - 1
        int randomIndex = StdRandom.uniformInt(qSize);

        // remove the item at the random index
        final Item item = queue[randomIndex];

        // move the last item in the queue to the random index
        if (randomIndex < qSize - 1) {

            queue[randomIndex] = queue[qSize - 1];
        }

        // set the last item in the queue to null to avoid loitering
        queue[--qSize] = null;

        // resize the underlying array if necessary
        if (qSize > 0 && qSize == queue.length / 4) {

            resize(queue.length / 2);
        }

        return item;
    }

    /**
     * Return a random item from the randomized queue.
     *
     * @return a random item from the randomized queue
     */
    public Item sample() {

        if (isEmpty()) {

            throw new java.util.NoSuchElementException("Queue is empty.");
        }

        // generate a random index between 0 and size - 1
        int randomIndex = StdRandom.uniformInt(qSize);

        return queue[randomIndex];
    }

    /**
     * Resize the underlying array to the specified new size.
     *
     * @param newSize the new size of the underlying array
     */
    private void resize(int newSize) {

        if (newSize <= 0) {

            throw new IllegalArgumentException(
                    "New size must be greater than 0.");
        }

        Item[] copy = (Item[]) new Object[newSize];

        for (int i = 0; i < qSize; i++) {

            copy[i] = queue[i];
        }

        queue = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        return new RandomizedQueueIterator();
    }

    /**
     * Iterator class to iterate over items of a randomized queue in random
     * order.
     */
    private class RandomizedQueueIterator implements Iterator<Item> {

        private int[] indices = new int[qSize];
        private int count = 0;

        /**
         * Construct iterator copy array and shuffle in uniform random order.
         */
        public RandomizedQueueIterator() {

            for (int i = 0; i < qSize; i++) {

                indices[i] = i;
            }

            StdRandom.shuffle(indices);
        }

        /**
         * Check if there are more items to return.
         *
         * @return true if there are more items to return, false otherwise
         */
        public boolean hasNext() {

            return count < qSize;
        }

        /**
         * Return the next item in the randomized queue.
         *
         * @return the next item in the randomized queue
         * @throws java.util.NoSuchElementException if there are no more items
         */
        public Item next() {

            if (!hasNext()) {

                throw new java.util.NoSuchElementException(
                        "No more items to return.");
            }

            return queue[indices[count++]];
        }

        public void remove() {

            throw new UnsupportedOperationException(
                    "Remove from iterator operation not supported.");
        }
    }

    // unit testing (required)

    /**
     * Unit tests the RandomizedQueue data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        // test isEmpty() method
        System.out.println("Is empty: " + rq.isEmpty());

        // test enqueue() method
        rq.enqueue(1);
        System.out.println("Added item to the queue: 1");
        rq.enqueue(2);
        System.out.println("Added item to the queue: 2");
        rq.enqueue(3);
        System.out.println("Added item to the queue: 3");
        rq.enqueue(4);
        System.out.println("Added item to the queue: 4");

        // test size() method
        System.out.println("Size of queue: " + rq.size());

        // test iterators and their randomness
        System.out.println("Items in queue (random order 1):");
        for (Integer item : rq) {

            System.out.println(item);
        }

        System.out.println("Items in queue (random order 2):");
        for (Integer item : rq) {

            System.out.println(item);
        }

        // test sample() method
        System.out.println("Sample item 1: " + rq.sample());
        System.out.println("Sample item 2: " + rq.sample());

        // test dequeue() method
        System.out.println("Removed item from queue: " + rq.dequeue());
        System.out.println("Removed item from queue: " + rq.dequeue());
        System.out.println("Size of queue: " + rq.size());

        // test isEmpty() method
        System.out.println("Is empty: " + rq.isEmpty());

        // test exceptions - enqueue() method
        try {

            rq.enqueue(null);
        }
        catch (IllegalArgumentException e) {

            System.out.println("Caught exception while adding null item: "
                                       + e.getMessage());
        }

        // test exceptions - dequeue() method
        while (!rq.isEmpty()) {

            rq.dequeue();
        }

        try {

            rq.dequeue();
        }
        catch (java.util.NoSuchElementException e) {

            System.out.println("Caught exception while removing from "
                                       + "empty queue: " + e.getMessage());
        }

        // test exceptions - sample() method
        try {

            rq.sample();
        }
        catch (java.util.NoSuchElementException e) {

            System.out.println("Caught exception while sampling from "
                                       + "empty queue: " + e.getMessage());
        }

        // test exceptions - iterator remove() method
        try {

            rq.iterator().remove();
        }
        catch (UnsupportedOperationException e) {

            System.out.println("Caught exception while removing from "
                                       + "iterator: " + e.getMessage());
        }

        // test exceptions - next() method
        try {

            Iterator<Integer> it = rq.iterator();
            while (it.hasNext()) {

                it.next();
            }

            it.next();
        }
        catch (java.util.NoSuchElementException e) {

            System.out.println("Caught exception while iterating: "
                                       + e.getMessage());
        }
    }
}
