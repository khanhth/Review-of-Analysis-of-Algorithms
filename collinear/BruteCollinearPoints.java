import java.util.ArrayList;
import java.util.LinkedList;

import org.w3c.dom.Node;

public class BruteCollinearPoints {
    private final Point[] points;
    private LinkedList<LineSegment> segments;
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        this.points = points;

        if (points == null) throw new IllegalArgumentException();

        for (int p = 0; p < this.points.length; p++) {
            for (int q = 0; q < this.points.length; q++) {
                if (q == p) continue;
                for (int r = 0; r < this.points.length; r++) {
                    if (r == q || r == p) continue;
                    for (int s = 0; s < this.points.length; s++) {
                        if (s == r || s == q || s == p) continue;
                        this.throwExceptionIfNullOrDuplicate(points[p], points[q], points[r], points[s]);
                        if (this.points[p].slopeOrder().compare(this.points[q], this.points[r])
                        == this.points[p].slopeOrder().compare(this.points[r], this.points[s])) {
                            this.segments.add(new LineSegment(this.points[p], this.points[s]));
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
 