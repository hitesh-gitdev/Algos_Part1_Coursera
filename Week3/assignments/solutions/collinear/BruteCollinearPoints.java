import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/******************************************************************************
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java BruteCollinearPoints
 *  Dependencies: none
 *
 *  A Bruteforce solution to find collinear points in a set of points. This
 *  solution uses the slope order to find collinear points. The time complexity
 *  is O(n^4) and the space complexity is O(n).
 *
 ******************************************************************************/

public class BruteCollinearPoints {

    private final LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

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

        List<LineSegment> lineSegmentList = new ArrayList<>();

        for (int i = 0; i < n - 3; i++) {

            Point p = pointsCopy[i];
            for (int j = i + 1; j < n - 2; j++) {

                Point q = pointsCopy[j];
                double slopePQ = p.slopeTo(q);
                for (int k = j + 1; k < n - 1; k++) {

                    Point r = pointsCopy[k];
                    double slopePR = p.slopeTo(r);
                    if (slopePQ != slopePR) {

                        continue;
                    }

                    for (int m = k + 1; m < n; m++) {

                        Point s = pointsCopy[m];
                        double slopePS = p.slopeTo(s);

                        if (slopePQ == slopePS) {

                            lineSegmentList.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }

        lineSegments = new LineSegment[lineSegmentList.size()];
        int i = 0;
        for (LineSegment lineSegment : lineSegmentList) {

            lineSegments[i++] = lineSegment;
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

    // test client
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {

            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();
    }
}
