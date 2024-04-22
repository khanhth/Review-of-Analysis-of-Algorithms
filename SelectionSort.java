import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class SelectionSort {
    private class Analyzer {
        int N;
        int exchanges;
        int compares;
        boolean sorted;
        int ops() {
            return exchanges + compares;
        }
        float relativeChange(int type) {
            float r;
            switch (type) {
                case 1:
                    r = (float) compares / (N*N);
                    break;
                case 2:
                    r = (float) exchanges / N;
                    break;
                default:
                    r = (float) ops() / (N*N);
                    break;
            }

            return r;
        }
    }

    public void analyze() {
        int n = analyzer.N;
        if (n == 0) System.out.println("Sorting's not been started.");
        else System.out.printf("[%s] N: %d, N^2: %d, ops: %d\n" +
                        "\trel. change: (total) %f [Exp. ~1/2]" +
                        "\t(comp.): %f [Exp. ~1/2]" +
                        "\t(exch.): %f [Exp. ~1]\n",
                analyzer.sorted ? "-" : "+", n, n*n, analyzer.ops(),
                analyzer.relativeChange(0),
                analyzer.relativeChange(1),
                analyzer.relativeChange(2));
    }

    public Analyzer analyzer;

    public SelectionSort() {
    }

    public void sort(Comparable[] items, boolean sorted) {
        analyzer = new Analyzer();
        analyzer.N = items.length;
        analyzer.sorted = sorted;

//        System.out.printf("Sorted: %b\n", sorted);

        for (int i = 0; i < items.length - 1; i++) {
            int currentMin = i + 1;
            for (int j = i + 2; j < items.length; j++) {
                analyzer.compares = analyzer.compares + 1; // compare op
                if (items[j].compareTo(items[currentMin]) < 0) {
                    currentMin = j;
                }
            }

            analyzer.compares = analyzer.compares + 1; // compare op
            if (items[i].compareTo(items[currentMin]) > 0) {
                analyzer.exchanges = analyzer.exchanges + 1; // exchange op
                exch(items, i, currentMin);
            }
        }
    }

    public void exch(Comparable[] items, int i, int j) {
        Comparable tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    public static void main(String[] args) {
        Integer[] items = new Integer[]{3, 2, 10, 15, 9, 4, 0};

        SelectionSort sorter = new SelectionSort();
        sorter.sort(items, false);

        System.out.printf("[sorted] items: %s\n", Arrays.toString(items));
        sorter.analyze();

        items = new Integer[]{3, 2, 10, 15, 9, 4, 0, 22, 19, -1, 10, 31, 22};
        sorter.sort(items, false);
        System.out.printf("[sorted] items: %s\n", Arrays.toString(items));
        sorter.analyze();

        items = new Integer[]{3, 2, 10, 15, 9, 4, -2, 0, 102, -12, 88, 50,
                22, 19, -1, 10, 31, 7, 22};
        sorter.sort(items, false);
        System.out.printf("[sorted] items: %s\n", Arrays.toString(items));
        sorter.analyze();

        items = sample(100);
        sorter.sort(items, false);
        sorter.analyze();

        items = sample(200);
        sorter.sort(items, false);
        sorter.analyze();
        sorter.sort(items, true);
        sorter.analyze();

        items = sample(500);
        sorter.sort(items, false);
        sorter.analyze();

        sorter.sort(items, true);
        sorter.analyze();
    }

    private static Integer[] sample(int n) {
        Integer[] out = new Integer[n];
        for (int i = 0; i < n; i++) {
            out[i] = StdRandom.uniformInt(-1000, 1000);
        }

        return out;
    }
}
