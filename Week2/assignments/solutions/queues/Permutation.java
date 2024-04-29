import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *  Compilation:  javac Permutation.java
 *  Execution:    java Permutation
 *
 *  This program takes an integer k as a command-line argument; reads in a
 *  sequence of strings from standard input using StdIn.readString(); and prints
 *  exactly k of them, uniformly at random.
 *
 *  % java Permutation 3 < input.txt
 *  A
 *  B
 *  C
 ******************************************************************************/

public class Permutation {

    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);

        if (k < 0) {

            return;
        }

        RandomizedQueue<String> rq = new RandomizedQueue<>();

        // Read in strings from standard input and add to randomized queue.
        int count = 0;
        while (!StdIn.isEmpty()) {

            count++;
            String in = StdIn.readString();

            // Add to randomized queue if count is less than or equal to k.
            // If count is greater than k, randomly replace an item in the
            // randomized queue with the new item.
            if (count <= k) {

                rq.enqueue(in);
            }
            else if (StdRandom.uniformInt(count) < k) {

                rq.dequeue();
                rq.enqueue(in);
            }
        }

        // Ensure k is not greater than the size of the randomized queue.
        if (k > rq.size()) {

            k = rq.size();
        }

        // Print out k items from the randomized queue.
        for (int i = 0; i < k; i++) {

            System.out.println(rq.dequeue());
        }
    }
}
