import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;
import java.util.Map;

public class ArrayPermutation {

    private static boolean arePermutations(int[] a, int[] b) {

        // Check if a and b have the same length
        if (a.length != b.length) {

            return false;
        }

        // Using HashMaps to store frequency of each number in a and b
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < a.length; i++) {

            // Increment frequency of a[i] in freqMap
            freqMap.put(a[i], freqMap.getOrDefault(a[i], 0) + 1);

            // Decrement frequency of b[i] in freqMap
            freqMap.put(b[i], freqMap.getOrDefault(b[i], 0) - 1);
        }

        // Check if the frequencies of all numbers in a and b are 0
        for (int frequency : freqMap.values()) {

            if (frequency != 0) {

                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        final int N = 50;
        final int range = 100;

        int[] array1 = new int[N];
        int[] array2 = new int[N];
        int[] array3 = new int[N];

        for (int i = 0; i < N; i++) {

            array1[i] = StdRandom.uniformInt(range);
            array2[i] = array1[i];
            array3[i] = StdRandom.uniformInt(range);
        }

        // Permute array2 using elements from array1
        StdRandom.shuffle(array2);

        System.out.println("Note: array2 is shuffled array1. "
                                   + "array3 is a different array.");

        System.out.println("Are array1 and array2 permutations of each other? "
                                   + arePermutations(array1, array2));

        System.out.println("Are array1 and array3 permutations of each other? "
                                   + arePermutations(array1, array3));
    }
}
