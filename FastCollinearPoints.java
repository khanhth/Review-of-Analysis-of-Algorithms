import java.util.Arrays;

public class FastCollinearPoints {
    private static LineSegment[] segments;
    private int numberOfSegments = 0;
    private Point[] startSegmentPts;
    private Point[] endSegmentPts;
//    private boolean print = true;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw  new java.lang.IllegalArgumentException("argument to" +
                    " FastCollinearPoints constructor is null");
        }
        int len = points.length;
        startSegmentPts = new Point[len];
        endSegmentPts = new Point[len];
        Point[] auxPoints = new Point[len];
        System.arraycopy(points, 0, auxPoints, 0, len);
        for (Point origin : points) {
            if (origin == null) {
                throw  new java.lang.IllegalArgumentException("argument to " +
                        "FastCollinearPoints constructor contains null entries");
            }
            Point startSegmentPoint = origin;
            Point endSegmentPoint = origin;
//            if (print) System.out.printf("\n[***] Origin: %s", origin);
            sort(auxPoints, origin);
//            if (print) System.out.printf("\n\tSorted: %s\n",
//                    Arrays.toString(points));
            for (int i = 1; i < len - 2; i++) {
                if (origin.slopeTo(auxPoints[i]) == origin.slopeTo(auxPoints[i + 1]) &&
                        origin.slopeTo(auxPoints[i + 1]) == origin.slopeTo(auxPoints[i + 2])
                ) {
                    if (checkDuplicate(new Point[]{origin, auxPoints[i], auxPoints[i + 1], auxPoints[i + 2]})) {
                        throw new IllegalArgumentException("duplicate points found!");
                    }
//                    StdOut.printf("\t[update] start, end: (%d, %d).", startSegmentIdx, endSegmentIdx);
//                    StdOut.print("\n\t\t[START_IDX update]");
                    if (startSegmentPoint.compareTo(auxPoints[i]) > 0) {
//                        StdOut.printf("\t1. %d -> %d.", startSegmentIdx, i);
                        startSegmentPoint = auxPoints[i];
                    }
                    if (startSegmentPoint.compareTo(auxPoints[i + 1]) > 0) {
//                        StdOut.printf("\t2. %d -> %d.", startSegmentIdx, i + 1);
                        startSegmentPoint = auxPoints[i + 1];
                    }
                    if (startSegmentPoint.compareTo(auxPoints[i + 2]) > 0) {
//                        StdOut.printf("\t3. %d -> %d.", startSegmentIdx, i + 2);
                        startSegmentPoint = auxPoints[i + 2];
                    }

//                    StdOut.print("\n\t\t[END_IDX update]");
                    if (endSegmentPoint.compareTo(auxPoints[i]) < 0) {
//                        StdOut.printf("\t1. %d -> %d.", endSegmentIdx, i);
                        endSegmentPoint = auxPoints[i];
                    }
                    if (endSegmentPoint.compareTo(auxPoints[i + 1]) < 0) {
//                        StdOut.printf("\t2. %d -> %d.", endSegmentIdx, i + 1);
                        endSegmentPoint = auxPoints[i + 1];
                    }
                    if (endSegmentPoint.compareTo(auxPoints[i + 2]) < 0) {
//                        StdOut.printf("\t3. %d -> %d.", endSegmentIdx, i + 2);
                        endSegmentPoint = auxPoints[i + 2];
                    }
//                    if (print) StdOut.printf("\n\t\t[UPDATED] New segment %s\n",
//                            new LineSegment(startSegmentPoint, endSegmentPoint));
                    if (i + 2 == len - 1) {
                        updateSegments(auxPoints, startSegmentPoint, endSegmentPoint);
                    }
                } else {
                    updateSegments(auxPoints, startSegmentPoint, endSegmentPoint);
                    startSegmentPoint = endSegmentPoint = origin;
                }
            }
        }

        FastCollinearPoints.segments = new LineSegment[numberOfSegments];
//        System.arraycopy(segments, 0, FastCollinearPoints.segments, 0, numberOfSegments);
        for (int j = 0; j < numberOfSegments; j++) {
            FastCollinearPoints.segments[j] = new LineSegment(startSegmentPts[j], endSegmentPts[j]);
        }
    }

    private static boolean checkDuplicate(Point[] points) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4 && j != i; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    return true;
                }
                for (int k = 0; k < 4 && k != j && k != i; k++) {
                    if (points[i].compareTo(points[k]) == 0 || points[j].compareTo(points[k]) == 0) {
                        return true;
                    }
                    for (int l = 0; l < 4 && l != k && l != j && l != i; l++) {
                        if (points[i].compareTo(points[l]) == 0 || points[j].compareTo(points[l]) == 0 ||
                                points[k].compareTo(points[l]) == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void updateSegments(Point[] points, Point startSegmentPt, Point endSegmentPt) {
//        System.out.printf("\n\tstartPoints, endPoints: (%s, %s)", Arrays.toString(startPts), Arrays.toString(endPts));
        if (startSegmentPt == endSegmentPt) {
//            if (print) StdOut.printf("\n\t\t[.] SKIP (SAME start and end points): %s ->" +
//                    " %s\n", startSegmentPt.toString(), startSegmentPt.toString());
            return;
        }

        boolean exists = false;
        for (int i = 0; i < numberOfSegments; i++) {
            if (startSegmentPts[i] == endSegmentPts[i]) {
                break;
            }
            if (startSegmentPt == startSegmentPts[i] && endSegmentPt == endSegmentPts[i]) {
                exists = true;
                break;
            }
        }
        if (!exists) {
//            if (print) StdOut.printf("\n\n\t[+][+][+] ADD NEW: %s\tUpdated. no. of segs: %d\n\n",
//                    new LineSegment(startSegmentPt, endSegmentPt), numberOfSegments+1);

            if (numberOfSegments == startSegmentPts.length) {
                resize();
            }

            startSegmentPts[numberOfSegments] = startSegmentPt;
            endSegmentPts[numberOfSegments] = endSegmentPt;
            numberOfSegments++;
        }
//        else {
//            if (print) StdOut.printf("\n\t\t\t[.] SKIP (exists): %s\n",
//                    new LineSegment(startSegmentPt, endSegmentPt));
//        }
    }

    private void resize() {
        int len = startSegmentPts.length;
        Point[] newStart = new Point[len*2];
        Point[] newEnd = new Point[len*2];
        System.arraycopy(startSegmentPts, 0, newStart, 0, len);
        System.arraycopy(endSegmentPts, 0, newEnd, 0, len);
        startSegmentPts = newStart;
        endSegmentPts = newEnd;
    }

    private void sort(Point origin, Point[] points, Point[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(origin, points, aux, lo, mid);
        sort(origin, points, aux, mid + 1, hi);
        merge(origin, points, aux, lo, mid, hi);
    }

    private void merge(Point origin, Point[] points, Point[] aux, int lo, int mid, int hi) {
        int k = lo;
        int leftIdx = lo;
        int rightIdx = mid + 1;

        copyToAux(points, aux, lo, hi);
        while (k <= hi) {
            if (rightIdx == hi + 1) {
                points[k] = aux[leftIdx++];
            } else if (leftIdx == mid + 1) {
                points[k] = aux[rightIdx++];
            } else if (less(origin, aux[leftIdx], aux[rightIdx])) {
                points[k] = aux[leftIdx++];
            } else {
                points[k] = aux[rightIdx++];
            }
            k++;
        }
    }

    private void copyToAux(Point[] points, Point[] aux, int lo, int hi) {
        while (lo <= hi) {
            aux[lo] = points[lo];
            lo++;
        }
    }

    private void sort(Point[] points, Point origin) {
        int lo = 0;
        int hi = points.length;
        Point[] aux = new Point[points.length];

        sort(origin, points, aux, lo, hi - 1);
    }

    private void exch(Point[] points, int i, int j) {
        Point tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private static boolean less(Point origin, Point v, Point w) {
        return origin.slopeOrder().compare(v, w) < 0;
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        return FastCollinearPoints.segments;
    }
}
