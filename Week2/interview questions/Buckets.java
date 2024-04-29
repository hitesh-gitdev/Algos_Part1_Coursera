import edu.princeton.cs.algs4.StdRandom;

public class Buckets {

    private enum Pebble {
        RED, WHITE, BLUE
    }

    private Pebble[] pebbles;

    public Buckets(int size) {

        pebbles = new Pebble[10];

        for (int i = 0; i < pebbles.length; i++) {

            pebbles[i] = Pebble.values()[StdRandom.uniformInt(3)];
        }
    }

    public Pebble color(int i) {

        return pebbles[i];
    }

    public void swap(int i, int j) {

        Pebble temp = pebbles[i];
        pebbles[i] = pebbles[j];
        pebbles[j] = temp;
    }

    public void sortPebbles() {

        if (pebbles == null || pebbles.length == 0) {

            return;
        }

        int lo = 0;
        int hi = pebbles.length - 1;
        int i = 0;

        while (i <= hi) {

            if (color(i) == Pebble.RED) {

                swap(i, lo);
                i++;
                lo++;
            }
            else if (color(i) == Pebble.BLUE) {

                swap(i, hi);
                hi--;
            }
            else {

                i++;
            }
        }
    }

    public void printPebbles() {

        for (int i = 0; i < pebbles.length; i++) {

            System.out.println(pebbles[i]);
        }
    }

    public static void main(String[] args) {

        Buckets buckets = new Buckets(10);

        System.out.println("Pebbles before sorting:");
        buckets.printPebbles();

        buckets.sortPebbles();

        System.out.println("Pebbles after sorting:");
        buckets.printPebbles();
    }
}
