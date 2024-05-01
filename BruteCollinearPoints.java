import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private Point[] points;
    private LineSegment[] segments;
    private Point[] segmentStartPoints;
    private Point[] segmentEndPoints;
    private int numberOfSegments = 0;

    public BruteCollinearPoints(Point[] pts)  {   // finds all line segments containing 4 points
        points = pts;
        int len = points.length;
        segmentStartPoints = new Point[len];
        segmentEndPoints = new Point[len];

        for (int i = 0; i < len; i++) {
            Point point = points[i];
            for (int j = 0; j < len; j++) {
                if (i == j) continue;
                for (int k = 0; k < len; k++) {
                    if (k == i || k == j) continue;
                    for (int l = 0; l < len; l++) {
                        if (l == i || l == j || l == k) continue;
                        if (point.slopeTo(points[j]) == point.slopeTo(points[k]) &&
                                point.slopeTo(points[k]) == point.slopeTo(points[l])) {
                            addSegment(i, j, k, l);
                        }
                    }
                }
            }
        }

        segments = new LineSegment[numberOfSegments];

        for (int h = 0; h < numberOfSegments; h++) {
            segments[h] = new LineSegment(segmentStartPoints[h], segmentEndPoints[h]);
        }
    }

    public int numberOfSegments() {        // the number of line segments
        return numberOfSegments;
    }

    private int[] segmentExtPoints(int i, int j, int k, int l) {
        int min = i;
        int max = i;

        if (points[min].compareTo(points[j]) > 0) {
            min = j;
        }
        if (points[min].compareTo(points[k]) > 0) {
            min = k;
        }
        if (points[min].compareTo(points[l]) > 0) {
            min = l;
        }

        if (points[max].compareTo(points[j]) < 0) {
            max = j;
        }
        if (points[max].compareTo(points[k]) < 0) {
            max = k;
        }
        if (points[max].compareTo(points[l]) < 0) {
            max = l;
        }

//        StdOut.printf("\n\tmin: %d\tmax: %d\n", min, max);
//        StdOut.printf("\tminPoint: %s\tmaxPoint: %s\n", points[min].toString(),
//                points[max].toString());

        return new int[]{min, max};
    }

    private void addSegment(int i, int j, int k, int l) {
//        StdOut.printf("\n\n[***] points: %s\n\n\ti, j, k, l: (%d, %d, %d, %d)",
//                Arrays.toString(points), i, j, k, l);
        int[] extremes = segmentExtPoints(i, j, k, l);


        Point minPoint = points[extremes[0]];
        Point maxPoint = points[extremes[1]];

        for (int h = 0; h < numberOfSegments; h++) {
            Point startPoint = segmentStartPoints[h];
            Point endPoint = segmentEndPoints[h];
            if (startPoint == null) {
                break;
            }

            if (startPoint.compareTo(minPoint) == 0 && endPoint.compareTo(maxPoint) == 0) {
//                StdOut.printf("\t[SKIP]..........\n\t\tsegmentStartPoints: %s" +
//                                "\n\t\tsegmentEndPoints: %s\n",
//                        Arrays.toString(segmentStartPoints),
//                        Arrays.toString(segmentEndPoints));
                return;
            }
        }

        segmentStartPoints[numberOfSegments] = minPoint;
        segmentEndPoints[numberOfSegments] = maxPoint;
        numberOfSegments++;
//        StdOut.printf("\t[ADD]******\n\t\tsegmentStartPoints: %s\n\t\tsegmentEndPoints:" +
//                " %s\n", Arrays.toString(segmentStartPoints),
//                Arrays.toString(segmentEndPoints));
    }

    public LineSegment[] segments() { // the line segments
        return segments;
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
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
//            segment.draw();
        }
//        StdDraw.show();


    }
}
