package week03_CollinearPoints;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 * @author leongfeng created on 2017/10/28.
 */
public class Point implements Comparable<Point> {
    private int x;
    private int y;
    private Comparator<Point> comparator;
    public Point(int x, int y){
        this.x = y;
        this.y = y;
    }

    /**
     * draws the point.
     */
    public void draw(){
        StdDraw.point(x, y);
    }

    /**
     * draws the line segment from this point to that point.
     * @param that
     */
    public void drawTo(Point that){
        StdDraw.line(x, y, that.x, that.y);
    }

    /**
     * the slope between this point and that point.<br/>
     * formula: slope = (y1 - y0)/(x1 - x0)；<br/>
     * if y1 == y0 (horizontal line), return 0;<br/>
     * if x1 == x0 (vertical line), return positive infinity;<br/>
     * if slope == 0 (collinear line：共线，斜率相同) segment, return degenerate line.
     * @param that
     * @return
     */
    public double slopeTo(Point that){
        if (this.y == that.y){
            return 0;
        }
        if (this.x == that.x){
            return Double.POSITIVE_INFINITY;
        }
        double slope = (this.y - that.y) / (this.x - that.x);
        // have same slope
        if (slope == 0){
            return Double.NEGATIVE_INFINITY;
        }
        return slope;
    }

    /**
     * compare two points by slopes the make with this point.<br/>
     * formula: (y1 - y0)/(x1 - x0) < (y2 - y0)/(x2 - x0) => (x1, y1) < (x2, y2).
     * @return
     */
    public Comparator<Point> slopeOrder(){
        return new PointComparator();
    }

    private static boolean less(Point p1, Point p0){
        return p1.compareTo(p0) < 0;
    }

    /**
     * compare y-coordinates,  breaking ties by their x-coordinates.<br/>
     * formula: <code>if y0 < y1 or (yo = y1 and x0 < x1), then (x0, y0) < (x1, y1)</code>.
     * @param that
     * @return 1 or 0
     */
    @Override
    public int compareTo(Point that) {
        if (this.y > that.y){
            return 1;
        }
        if (this.y == that.y && this.x > that.x){
            return 1;
        }
        return 0;
    }

    private class PointComparator implements Comparator<Point>{

        /**
         * p1 < p2(if slope of p1 < p2 return -1);
         * @param p1
         * @param p2
         * @return
         */
        @Override
        public int compare(Point p1, Point p2) {
            double slope = p1.slopeTo(p2);
            if (slope > 0){
                return 1;
            }
            if (slope < 0){
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Point p0 = new Point(0, 2);
        Point p1 = new Point(0, 5);
        StdOut.println(less(p1, p0));
        p0 = new Point(2, 0);
        p1 = new Point(5, 0);
        StdOut.println(less(p1, p0));
        p0 = new Point(1, 1);
        p1 = new Point(2, 4);
        StdOut.println(p0.slopeTo(p1));
    }
}
