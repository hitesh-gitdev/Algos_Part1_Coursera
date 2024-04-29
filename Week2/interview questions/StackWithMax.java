import edu.princeton.cs.algs4.Stack;

/******************************************************************************
 *  Compilation:  javac StackWithMax.java
 *  Execution:    java StackWithMax
 *
 *  A stack that supports push, pop, and max operations in constant time.
 ******************************************************************************/

public class StackWithMax<T extends Double> {

    private Stack<T> stack = new Stack<>();
    private Stack<T> maxStack = new Stack<>();

    public void push(T item) {
        stack.push(item);
        if (maxStack.isEmpty() || Double.compare(item, maxStack.peek()) >= 0) {

            maxStack.push(item);
        }
    }

    public T pop() {

        if (stack.isEmpty()) {
            throw new java.util.NoSuchElementException("Stack is empty");
        }

        T item = stack.pop();
        if (Double.compare(item, maxStack.peek()) == 0) {

            maxStack.pop();
        }
        return item;
    }

    public T getMax() {

        if (maxStack.isEmpty()) {
            throw new java.util.NoSuchElementException("Stack is empty");
        }
        return maxStack.peek();
    }

    public static void main(String[] args) {

        StackWithMax<Double> stackWithMax = new StackWithMax<>();
        stackWithMax.push(11.0);
        stackWithMax.push(43.0);
        stackWithMax.push(53.0);
        stackWithMax.push(53.0);
        stackWithMax.push(23.0);
        System.out.println("Current Max: " + stackWithMax.getMax());
        System.out.println("pop: " + stackWithMax.pop());
        System.out.println("Current Max: " + stackWithMax.getMax());
        System.out.println("pop: " + stackWithMax.pop());
        System.out.println("Current Max: " + stackWithMax.getMax());
        System.out.println("pop: " + stackWithMax.pop());
        System.out.println("Current Max: " + stackWithMax.getMax());
    }
}
