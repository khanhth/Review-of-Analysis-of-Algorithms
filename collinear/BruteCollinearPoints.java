import java.util.LinkedList;

public class BruteCollinearPoints {
    private LinkedList<LineSegment> segments;
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        for (int p = 0; p < points.length; p++) {
            for (int q = 0; q < points.length; q++) {
                if (q == p) continue;
                for (int r = 0; r < points.length; r++) {
                    if (r == q || r == p) continue;
                    for (int s = 0; s < points.length; s++) {
                        if (s == r || s == q || s == p) continue;
                        this.throwExceptionIfNullOrDuplicate(points[p], points[q], points[r], points[s]);
                        if (points[p].slopeOrder().compare(points[q], points[r])
                        == points[p].slopeOrder().compare(points[r], points[s])) {
                            this.segments.add(new LineSegment(points[p], points[s]));
                        }
                    }
                }
            }
        }
    }

    public void throwExceptionIfNullOrDuplicate(Point p, Point q, Point r, Point s) {
        if (p == null || q == null || r == null || s == null) throw new IllegalArgumentException();

        if (p.equals(q) || p.equals(r) || p.equals(s) || q.equals(r) || q.equals(s) || r.equals(s)) {
            throw new IllegalArgumentException();
        }
    }
    // the number of line segments
    public int numberOfSegments()  {
        return this.segments.size();
    }
    // the line segments
    public LineSegment[] segments() {
        int size = this.segments.size();
        LineSegment[] segments = new LineSegment[size];
        for (int i = 0; i < size; i++) {
            segments[i] = this.segments.get(i);
        }

        return segments;
    }
 }
 