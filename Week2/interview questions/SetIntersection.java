/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class SetIntersection {

    private static class Point2D implements Comparable<Point2D> {

        private final double x;
        private final double y;

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point2D point) {

            final int compareX = Double.compare(point.x, this.x);
            final int compareY = Double.compare(point.y, this.y);

            if (compareX > 0) {

                return -1;
            }
            else if (compareX < 0) {

                return 1;
            }
            else if (compareY > 0) {

                return -1;
            }
            else if (compareY < 0) {

                return 1;
            }

            return 0;
        }
    }

    public int countIntersection(Point2D[] a, Point2D[] b) {

        int count = 0;
        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {

            if (a[i].compareTo(b[i]) == 0) {

                count++;
                i++;
                j++;
            }
            else if (a[i].compareTo(b[j]) < 0) {

                i++;
            }
            else {

                j++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        SetIntersection intersection = new SetIntersection();

        Point2D[] a = new Point2D[5];
        Point2D[] b = new Point2D[6];

        // initialize array a
        a[0] = new Point2D(1.0, 2.0);
        a[1] = new Point2D(2.0, 3.0);
        a[2] = new Point2D(3.0, 4.0);
        a[3] = new Point2D(4.0, 5.0);
        a[4] = new Point2D(5.0, 6.0);

        // initialize array b
        b[0] = new Point2D(2.0, 2.0);
        b[1] = new Point2D(5.0, 2.0);
        b[2] = new Point2D(3.0, 4.0);
        b[3] = new Point2D(5.0, 6.0);
        b[4] = new Point2D(4.0, 5.0);
        b[5] = new Point2D(1.0, 2.0);

        System.out.println("Number of intersections using sorting: "
                                   + intersection.countIntersection(a, b));
    }
}
