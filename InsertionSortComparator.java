import java.util.Arrays;
import java.util.Comparator;

public class InsertionSortComparator {
    private SortAnalyzer analyzer;

    public InsertionSortComparator(SortAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void sort(Object[] items, Comparator comparator) {
        for (int i = 0; i < items.length; i++) {
            // Starting at j = i + 1 instead of j = i can
            // reduce number of compares (i.e. checking if j < items.length).
            for (int j = i; j > 0 && less(comparator, items[j], items[j - 1]); j--) {
                analyzer.compare();
                analyzer.access(2);
                exch(items, j, j - 1);
            }
        }
    }

    private static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

    public void exch(Object[] items, int i, int j) {
        analyzer.exchange();
        Object tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    public static void main(String[] args) {
        Student[] items = new Student[]{
                new Student(2, 105),
                new Student(3, 102),
                new Student(8, 107),
                new Student(1, 103),
                new Student(6, 108),
                new Student(4, 104),
                new Student(5, 106),
                new Student(9, 109),
                new Student(7, 101),
        };

        SortAnalyzer analyzer = new SortAnalyzer();
        InsertionSortComparator sorter = new InsertionSortComparator(analyzer);
        System.out.printf("[Unsorted] items: %s\n\n", Arrays.toString(items));

        sorter.sort(items, Student.BY_ID);
        System.out.printf("[sorted] items (by ID): %s\n\n", Arrays.toString(items));

        sorter.sort(items, Student.BY_SECTION);
        System.out.printf("[sorted] items (by Section): %s\n\n", Arrays.toString(items));
    }
}
