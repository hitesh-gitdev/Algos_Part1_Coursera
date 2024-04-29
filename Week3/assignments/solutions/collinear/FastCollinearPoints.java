import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/******************************************************************************
 *  Compilation:  javac FastCollinearPoints.java
 *  Execution:    java FastCollinearPoints input.txt
 *  Dependencies: none
 *
 *  A Fast solution to find collinear points in a set of points. This solution
 *  uses the slope order to find collinear points. The time complexity is
 *  O(n^2 log n) and the space complexity is O(n).
 *  The input file contains the number of points followed by the x and y
 *  coordinates of each point in the following format:
 *  4
 *  19000 10000
 *  18000 10000
 *  32000 10000
 *  21000 10000
 *
 *  The output is the line segments that contain 4 or more points in the
 *  following format:
 *  (19000, 10000) -> (32000, 10000)
 *  (18000, 10000) -> (32000, 10000)
 *
 ******************************************************************************/

public class FastCollinearPoints {

    private static final int MIN_POINT = 4;
    private LineSegment[] lineSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        if (points == null) {

            throw new IllegalArgumentException("null argument");
        }

        Point[] pointsCopy = points.clone();
        int n = pointsCopy.length;

        // sanity null checks
        for (int i = 0; i < n; i++) {
            if (pointsCopy[i] == null) {
                throw new IllegalArgumentException("null point");
            }
        }

        Arrays.sort(pointsCopy);

        // Check for duplicates
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pointsCopy[i].compareTo(pointsCopy[j]) == 0) {
                    throw new IllegalArgumentException("duplicate points");
                }
            }
        }

        // Initialize the line segments
        List<LineSegment> lines = new ArrayList<>();

        for (Point p : pointsCopy) {

            Point[] pointsBySlope = pointsCopy.clone();
            Arrays.sort(pointsBySlope, p.slopeOrder());

            obtainLines(p, n, pointsBySlope, lines);

            lineSegments = lines.toArray(new LineSegment[lines.size()]);
        }
    }

    private void obtainLines(Point p, int n, Point[] pointsBySlope,
                             List<LineSegment> lines) {

        int i = 1;

        // Find collinear points and line segment
        while (i < n) {

            List<Point> collinearPoints = new ArrayList<>();
            double slope = p.slopeTo(pointsBySlope[i]);

            do {

                collinearPoints.add(pointsBySlope[i++]);
            } while (i < n && p.slopeTo(pointsBySlope[i]) == slope);

            if (collinearPoints.size() >= MIN_POINT - 1
                    && p.compareTo(collinearPoints.get(0)) < 0) {

                lines.add(
                        new LineSegment(
                                p,
                                collinearPoints.get(collinearPoints.size() - 1)
                        )
                );
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {

        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {

        return lineSegments.clone();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {

            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (Point p : points) {

            p.draw();
        }

        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {

            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();
    }
}
