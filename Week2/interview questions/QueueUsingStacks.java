/******************************************************************************
 *  Compilation:  javac QueueWithStacks.java
 *  Execution:    java QueueWithStacks
 *
 *  A queue that supports enqueue and dequeue operations in constant
 *  amortized time using two stacks.
 ******************************************************************************/

import edu.princeton.cs.algs4.Stack;

public class QueueUsingStacks<T> {

    private Stack<T> itemStack;
    private Stack<T> auxStack;

    public QueueUsingStacks() {

        itemStack = new Stack<>();
        auxStack = new Stack<>();
    }

    public void enqueue(T item) {

        itemStack.push(item);
    }

    public T dequeue() {

        if (itemStack.isEmpty() && auxStack.isEmpty()) {

            throw new java.util.NoSuchElementException("Queue is empty");
        }

        if (auxStack.isEmpty()) {

            while (!itemStack.isEmpty()) {

                auxStack.push(itemStack.pop());
            }
        }

        return auxStack.pop();
    }

    public static void main(String[] args) {

        QueueUsingStacks<Integer> queue = new QueueUsingStacks<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());
    }
}
