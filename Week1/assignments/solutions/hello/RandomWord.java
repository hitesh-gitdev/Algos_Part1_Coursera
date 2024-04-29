/******************************************************************************
 *  Compilation:  javac RandomWord.java
 *  Execution:    java RandomWord < words.txt
 *
 *  Reads a sequence of words from standard input and prints one of those words
 *  uniformly at random.
 *
 *  It uses the Knuth’s method: when reading the ith word, select it with
 *  probability 1/i to be the champion, replacing the previous champion.
 *  After all words are read, the surviving champion is printed.
 *
 *  % java RandomWord
 *  heads tails
 *  tails
 *
 *  % more animals8.txt
 *  ant bear cat dog
 *  emu fox goat horse
 *
 *  % java RandomWord < animals8.txt
 *  emu
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {

        // Randomly choose a word from standard input and print it.
        int count = 0;
        String champion = "";

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            count++;

            // Knuth's method to select a champion
            if (StdRandom.bernoulli(1.0 / count)) {
                champion = word;
            }
        }

        StdOut.println(champion);
    }

}