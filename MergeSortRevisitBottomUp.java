import java.util.Arrays;

public class MergeSortRevisitBottomUp {
    private SortAnalyzer analyzer;

    public MergeSortRevisitBottomUp(SortAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    private void sort(Comparable[] items, Comparable[] aux) {
        int lo = 0;
        int size = 1;
        int mid = lo + size - 1; // 0
        int hi = mid + size; // 1
        int len = items.length;
        System.out.printf("[***] len: %d\n", len);

        while (size <= len) { // <= len is a strong check.
            System.out.printf("%s(lo, mid, hi, size): (%d, %d, %d [, %d])\n", lo == 0 ? "[BEGIN] " : "\t", lo, mid, hi, size);
            if (hi > len - 1) { // perform last merge and done
                System.out.println("*** last merge");
                merge(items, aux, lo, mid, len - 1);
                break;
            }

            merge(items, aux, lo, mid, hi);
            System.out.printf("\tsorted items: %s\n", Arrays.toString(items));
            lo = hi + 1;
            mid = lo + size - 1;
            hi = mid + size;

            if (hi >= len - 1) {
                if (mid > len - 1) mid = len - 1;
                System.out.printf("\t\t[INNER: BEGIN] (lo, mid, hi, size): (%d, %d, %d [, %d])\n", lo, mid, len - 1, size);
                merge(items, aux, lo, mid, len - 1);
                System.out.printf("\t\tsorted items: %s\n", Arrays.toString(items));

                size = 2 * size;
                lo = 0;
                mid = lo + size - 1;
                hi = mid + size;
            }
        }
    }

    private void copyToAux(Comparable[] items, Comparable[] aux, int lo, int hi) {
        while (lo <= hi) {
            analyzer.access(2); // 2 accesses
            aux[lo] = items[lo];
            lo++;
        }
    }

    private void merge(Comparable[] items, Comparable[] aux, int lo, int mid, int hi) {
        int k = lo;
        int leftIdx = lo;
        int rightIdx = mid + 1;

        copyToAux(items, aux, lo, hi);
        while (k <= hi) {
            if (rightIdx == hi + 1) {
                items[k] = aux[leftIdx++];
                analyzer.access(2);
            } else if (leftIdx == mid + 1) {
                items[k] = aux[rightIdx++];
                analyzer.access(2);
            } else if (aux[leftIdx].compareTo(aux[rightIdx]) <= 0) {
                analyzer.access(2);
                analyzer.compare();
                analyzer.access(2); // two additional compares in if condition
                items[k] = aux[leftIdx++];
            } else {
                analyzer.access(2);
                analyzer.compare();
                analyzer.access(2); // two additional compares in if condition
                items[k] = aux[rightIdx++];
            }
            k++;
        }
    }

    public void sort(Comparable[] items) {
        Comparable[] aux = new Comparable[items.length];

        analyzer.refresh();
        sort(items, aux);
    }


    public static void main(String[] args) {
//        Integer[] items = new Integer[]{23, 2, -4};
        Integer[] items = new Integer[]{23, 2, 4, -1, 0, 10, 9, 15, -3, 19, 11, 22, 31, -6, 19, 13, -2};

        SortAnalyzer analyzer = new SortAnalyzer();
        MergeSortRevisitBottomUp mergeSort = new MergeSortRevisitBottomUp(analyzer);
        mergeSort.sort(items);

        System.out.printf("[main] sorted items: %s\n", Arrays.toString(items));
    }
}
