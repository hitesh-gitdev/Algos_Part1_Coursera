/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    java Percolation n
 *  Dependencies: algs4/WeightedQuickUnionUF.java algs4/StdIn.java
 *                algs4/StdOut.java
 *
 *  The Percolation class is a data type that models a percolation system.
 *  It takes n as a command-line argument and creates an n-by-n grid of sites.
 *  Based on the WeightedQuickUnionUF data type, it determines whether the
 *  system percolates or not. It opens a site at random (if not open already),
 *  and checks if the system percolates. If it does, it prints the number of
 *  open sites.
 ******************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF uf;
    private boolean[][] grid;
    private int openSiteCount = 0;
    private int gridLength;
    private byte[] backwash;
    private boolean hasPercolated = false;

    /**
     * Creates an n-by-n grid, with all sites initially blocked.
     *
     * @param n is the size of the grid
     * @throws IllegalArgumentException if n < 0
     */
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }

        gridLength = n;
        int gridSize = n * n;
        uf = new WeightedQuickUnionUF(gridSize);
        grid = new boolean[n][n];
        backwash = new byte[gridSize];
    }

    /**
     * Opens the site (row, col) if it is not open already
     *
     * @param row and col are the coordinates of the site
     * @throws IllegalArgumentException if the site (row, col) is out of bounds
     */
    public void open(int row, int col) {

        // validate the site coordinates
        validate(row, col);

        // return if the site is already open
        if (isOpen(row, col)) {
            return;
        }

        // declare the site as open
        grid[row - 1][col - 1] = true;

        // increment the count of open sites
        openSiteCount++;
        int currentSite = xyTo1D(row, col);

        // connect the site to the top and bottom sites if it is on the

        byte connStatus = 0;
        if (row == 1) {
            // Site is on the top row
            connStatus = 2;
        }
        else if (row == gridLength) {
            // Site is on the bottom row
            connStatus = (byte) (connStatus | 1);
        }

        // connect the site to its open neighbors
        // Connect to top open site
        if (row > 1 && isOpen(row - 1, col)) {
            // get neighbor root connection status
            connStatus = computeConnStatus(connStatus, row - 1, col);
            uf.union(currentSite, xyTo1D(row - 1, col));
        }

        // Connect to bottom open site
        if (row < gridLength && isOpen(row + 1, col)) {
            connStatus = computeConnStatus(connStatus, row + 1, col);
            uf.union(currentSite, xyTo1D(row + 1, col));
        }

        // Connect to left open site
        if (col > 1 && isOpen(row, col - 1)) {
            connStatus = computeConnStatus(connStatus, row, col - 1);
            uf.union(currentSite, xyTo1D(row, col - 1));
        }

        // Connect to right open site
        if (col < gridLength && isOpen(row, col + 1)) {
            connStatus = computeConnStatus(connStatus, row, col + 1);
            uf.union(currentSite, xyTo1D(row, col + 1));
        }

        // set the connection status of the site's root to connStatus
        backwash[uf.find(currentSite)] = connStatus;

        // check if the system percolates after opening the site
        if (connStatus == 3 || gridLength == 1) {
            hasPercolated = true;
        }
    }

    /**
     * Computes the connection status of a site with respect to its neighbor
     * given by row and col.
     *
     * @param connectionStatus is the current connection status of the site
     * @param row              is the row coordinate of a neighbor site
     * @param col              is the column coordinate of a neighbor site
     * @return the connection status of the site
     */
    private byte computeConnStatus(byte connectionStatus, int row, int col) {

        byte bwRootStatus = backwash[uf.find(xyTo1D(row, col))];
        return (byte) (connectionStatus | bwRootStatus);
    }

    /**
     * Checks if the site (row, col) is open or not.
     *
     * @param row and col are the coordinates of the site
     * @return {@code true} if the site (row, col) is open;
     * @throws IllegalArgumentException if the site (row, col) is out of bounds
     */
    public boolean isOpen(int row, int col) {

        validate(row, col);
        return grid[row - 1][col - 1];
    }

    /**
     * Checks if the site (row, col) is full or not.
     *
     * @param row and col are the coordinates of the site
     * @return {@code true} if the site (row, col) is full;
     * @throws IllegalArgumentException if the site (row, col) is out of bounds
     */
    public boolean isFull(int row, int col) {

        validate(row, col);
        int checkRoot = backwash[uf.find(xyTo1D(row, col))];
        return checkRoot > 1;
    }

    /**
     * Returns the number of open sites.
     *
     * @return the number of open sites
     */
    public int numberOfOpenSites() {

        return openSiteCount;
    }

    /**
     * Checks if the system percolates or not.
     *
     * @return {@code true} if the system percolates;
     */
    public boolean percolates() {

        return hasPercolated;
    }

    /**
     * Validates the site's (row, col) to be within bounds.
     *
     * @param row and col are the coordinates of the site to be validated
     * @throws IllegalArgumentException if the site (row, col) is out of bounds
     */
    private void validate(int row, int col) {

        if (row < 1 || row > gridLength) {
            throw new IllegalArgumentException(
                    "index " + row + " is not between 1 and " + gridLength);
        }

        if (col < 1 || col > gridLength) {
            throw new IllegalArgumentException(
                    "index " + col + " is not between 1 and " + gridLength);
        }
    }

    /**
     * Converts the 2D coordinates to 1D coordinate for WeightedQuickUnionUF
     *
     * @param row of the 2D site
     * @param col of the 2D site
     * @return 1D coordinate
     */
    private int xyTo1D(int row, int col) {

        return (row - 1) * gridLength + col - 1;
    }

    /**
     * Unit tests the Percolation data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // int n = StdIn.isEmpty() ? 1 : StdIn.readInt();

        Percolation perc = new Percolation(3);
        perc.open(1, 3);
        System.out.println("Percolates? - " + perc.percolates());

        perc.open(2, 3);
        System.out.println("Percolates? - " + perc.percolates());

        perc.open(3, 3);
        System.out.println("Percolates? - " + perc.percolates());
        System.out.println("Number of open sites - " + perc.numberOfOpenSites());
        System.out.println("Is site (1, 3) full? - " + perc.isFull(1, 3));
        System.out.println("Is site (2, 3) full? - " + perc.isFull(2, 3));
        System.out.println("Is site (3, 3) full? - " + perc.isFull(3, 3));

        perc.open(3, 1);
        System.out.println("Percolates? - " + perc.percolates());
        System.out.println("Number of open sites - " + perc.numberOfOpenSites());
        System.out.println("Is site (3, 1) full? - " + perc.isFull(3, 1));
    }
}
