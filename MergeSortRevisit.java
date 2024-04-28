import java.util.Arrays;

public class MergeSortRevisit {

    private SortAnalyzer analyzer;

    public MergeSortRevisit(SortAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    private void sort(Comparable[] items, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(items, aux, lo, mid);
        sort(items, aux, mid + 1, hi);
        merge(items, aux, lo, mid, hi);
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

    private void copyToAux(Comparable[] items, Comparable[] aux, int lo, int hi) {
        while (lo <= hi) {
            analyzer.access(2); // 2 accesses
            aux[lo] = items[lo];
            lo++;
        }
    }

    public void sort(Comparable[] items) {
        int lo = 0;
        int hi = items.length;
        Comparable[] aux = new Comparable[items.length];

        analyzer.refresh();
        sort(items, aux, lo, hi - 1);
    }

    public static void main(String[] args) {
        Integer[] items = new Integer[]{23, 2, 4, -1, 0, 10, 9, 15, -3, 19, 11, 22, 31, -6, 19, 13, -2};

        SortAnalyzer analyzer = new SortAnalyzer();
        MergeSortRevisit mergeSort = new MergeSortRevisit(analyzer);
        mergeSort.sort(items);

        // Print some analytics
        int N = items.length;
        float NLogN = (float) (N * Math.log(N)/Math.log(2));
        System.out.printf("[stat] N: %d, N*lg(N): %f, 6*N*lg(N): %f, " +
                "compares: %d, accesses: %d\n", N, NLogN, 6*NLogN,
                analyzer.getCompares(), analyzer.getAccesses());


        System.out.printf("[main] items: %s\n", Arrays.toString(items));

        mergeSort.sort(items);
        System.out.printf("[stat] N: %d, N*lg(N): %f, 6*N*lg(N): %f, " +
                        "compares: %d, accesses: %d\n", N, NLogN, 6*NLogN,
                analyzer.getCompares(), analyzer.getAccesses());
    }
}
