/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats n trials
 *  Dependencies: algs4/StdOut.java StdRandom.java StdStats.java
 *                Stopwatch.java Percolation.java
 *
 *  The PercolationStats class performs a series of computational experiments
 *  on an n-by-n grid to determine the percolation threshold. It takes n and
 *  trials as command-line arguments and runs trials number of experiments on
 *  an n-by-n grid. It calculates the mean, standard deviation, and 95%
 *  confidence interval of the percolation threshold.
 *
 *  % java PercolationStats 200 100
 *  mean                    = 0.5929934999999997
 *  stddev                  = 0.00876990421552567
 *  95% confidence interval = [0.5912745987737567, 0.5947124012262428]
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private int trials;
    private int n;
    private double[] pThresholds;

    /**
     * Performs trials number of experiments on an n-by-n grid
     *
     * @param n      is the size of the grid
     * @param trials is the number of experiments
     * @throws IllegalArgumentException if n or trials < 0
     */
    public PercolationStats(int n, int trials) {

        if (n <= 0) {
            throw new IllegalArgumentException("Grid size must be greater than 0");
        }

        if (trials <= 0) {
            throw new IllegalArgumentException("Number of trials must be greater than 0");
        }

        this.trials = trials;
        this.n = n;
        pThresholds = new double[trials];

        // Run trials number of experiments on an n-by-n grid
        Percolation percolation;
        for (int i = 0; i < trials; i++) {

            percolation = new Percolation(n);
            int openSites = 0;
            int gridSize = n * n;
            while (!percolation.percolates()) {

                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                if (percolation.isOpen(row, col)) {
                    continue;
                }
                percolation.open(row, col);
                openSites++;
            }
            pThresholds[i] = (double) openSites / gridSize;
        }
    }

    /**
     * Sample mean of percolation threshold
     *
     * @return the mean of the percolation thresholds
     */
    public double mean() {

        return StdStats.mean(pThresholds);
    }

    /**
     * Sample standard deviation of percolation threshold
     *
     * @return the standard deviation of the percolation thresholds
     */
    public double stddev() {

        return StdStats.stddev(pThresholds);
    }

    /**
     * Low endpoint of 95% confidence interval
     *
     * @return the low endpoint of the 95% confidence interval
     */
    public double confidenceLo() {

        return (mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    /**
     * High endpoint of 95% confidence interval
     *
     * @return the high endpoint of the 95% confidence interval
     */
    public double confidenceHi() {

        return (mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    /**
     * Test client
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats pStats = new PercolationStats(n, trials);

        System.out.println("mean                    = " + pStats.mean());
        System.out.println("stddev                  = " + pStats.stddev());
        System.out.println("95% confidence interval = ["
                                   + pStats.confidenceLo() + ", "
                                   + pStats.confidenceHi() + "]");
    }
}
