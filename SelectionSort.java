import java.util.Arrays;

public class SelectionSort {
    private class Analyzer {
        public int N;
        public int ops = 0;
    }

    public void analyze() {
        if (analyzer.N == 0) System.out.println("Sorting's not been started.");
        else System.out.printf("[*] N: %d, N^2: %d - ops: %d\n", analyzer.N, analyzer.N*analyzer.N, analyzer.ops);
    }

    public Analyzer analyzer;

    public SelectionSort() {
        analyzer = new Analyzer();
    }

    public void sort(Comparable[] items) {
        analyzer.N = items.length;

        for (int i = 0; i < items.length - 1; i++) {
            int currentMin = i + 1;
            for (int j = i + 1; j < items.length; j++) {
                analyzer.ops = analyzer.ops + 1; // compare op

                if (items[j].compareTo(items[currentMin]) < 0) {
                    currentMin = j;
                }
            }

            analyzer.ops = analyzer.ops + 1; // compare op
            if (items[i].compareTo(items[currentMin]) > 0) {
                analyzer.ops = analyzer.ops + 1; // exchange op
                exch(items, i, currentMin);
            }
//            System.out.printf("items: %s\n", Arrays.toString(items));
        }
    }

    public void exch(Comparable[] items, int i, int j) {
        Comparable tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    public static void main(String[] args) {
        Integer[] items = new Integer[]{3, 2, 10, 15, 9, 4, 0};
        System.out.printf("[UNsorted] items: %s\n", Arrays.toString(items));

        SelectionSort sorter = new SelectionSort();
        sorter.sort(items);

        System.out.printf("[sorted] items: %s\n", Arrays.toString(items));
        sorter.analyze();

        items = new Integer[]{3, 2, 10, 15, 9, 4, 0, 22, 19, -1, 10, 31, 22};
        sorter.sort(items);
        System.out.printf("[sorted] items: %s\n", Arrays.toString(items));
        sorter.analyze();

        items = new Integer[]{3, 2, 10, 15, 9, 4, -2, 0, 102, -12, 88, 50, 22, 19, -1, 10, 31, 7, 22};
        sorter.sort(items);
        System.out.printf("[sorted] items: %s\n", Arrays.toString(items));
        sorter.analyze();
    }
}
