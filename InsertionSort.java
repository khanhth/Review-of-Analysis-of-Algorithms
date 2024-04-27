import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class InsertionSort {
    private SortAnalyzer analyzer;
    private boolean sorted;
    private Comparable[] items;
    private int inputType; // 0: best, 2: worst

    public InsertionSort() {
    }

    public void sort(Comparable[] items, int type) {
        analyzer = new SortAnalyzer();
        this.items = items;
        inputType = type;

        for (int i = 1; i < items.length; i++) {
            int dest = i;
            for (int j = i - 1; j >= 0; j--) {
                analyzer.compare();
                if (items[i].compareTo(items[j]) >= 0) {
                    break;
                } else {
                    dest = j;
                }
            }

            insert(items, i, dest);
        }
        sorted = true;
    }

    private void insert(Comparable[] items, int end, int start) {
        if (end == start) return;
        Comparable endOld = items[end];
        while (end > start) {
            items[end] = items[--end];
            analyzer.exchange();
        }
        items[start] = endOld;
    }

    public void exch(Comparable[] items, int i, int j) {
        Comparable tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    public void resort() {
        if (!sorted) {
            throw new UnsupportedOperationException("Can only call resort() after items have been sorted.");
        }
        analyzer.refresh();
        sort(items, 0);
    }

    private static Integer[] sample(int n) {
        Integer[] out = new Integer[n];
        for (int i = 0; i < n; i++) {
            out[i] = StdRandom.uniformInt(-1000, 1000);
        }

        return out;
    }

    public float growthConstant(int statType) {
        float r;
        int N = items.length;
        int denom = inputType == 0 ? N : N*N;
        switch (statType) {
            case 1:
                r = (float) analyzer.getCompares() / denom;
                break;
            case 2:
                r = (float) analyzer.getExchanges() / denom;
                break;
            default:
                r = (float) analyzer.total() / denom;
                break;
        }

        return r;
    }

    private void analyze() {
        if (!sorted) System.out.println("[!] items have NOT been sorted.");
        String[] exps;
        String caseName;
        switch (inputType) {
            case 0:
                caseName = "[BEST] Compares = N - 1 | Exchanges = 0";
                exps  = new String[]{"1", "1", "0"};
                break;
            case 2:
                caseName = "[WORST] Compares / N^2 | Exchanges / N^2";
                exps  = new String[]{"1", "1/2", "1/2"};
                break;
            // Todo: check partially sorted array
            default: // random
                caseName = "[RAND] Compares / N^2 | Exchanges / N^2";
                exps  = new String[]{"1/2", "1/4", "1/4"};
                break;
        }

        int N = items.length;
        System.out.printf("[*] N: %d, N^2: %d, ops: %d\n" +
                        "\t%s\n\tTotal: %f [Exp. %s]" +
                        "\tcomp.: %f [Exp. %s]" +
                        "\texch.: %f [Exp. %s]\n",
                N, N*N, analyzer.total(), caseName,
                growthConstant(0), exps[0],
                growthConstant(1), exps[1],
                growthConstant(2), exps[2]);
    }

    public static void main(String[] args) {
        Integer[] items = new Integer[]{3, 2, 6, -5, 8, 23, 10, 9, 0, 32, 18};

        InsertionSort sorter = new InsertionSort();
//        System.out.printf("[Unsorted] items: %s\n", Arrays.toString(items));
        sorter.sort(items, 1);
        System.out.printf("[sorted] items: %s\n", Arrays.toString(items));
        sorter.analyze();

        items = sample(100);
        sorter.sort(items, 1);
        sorter.analyze();

        sorter.resort();
        sorter.analyze();

        items = sample(100);
        sorter.sort(items, 1);
        sorter.analyze();


        // best case
        int N = 100;
        items = new Integer[N];
        for (int i = 0; i < N; i++) {
            items[i] = i;
        }
        sorter.sort(items, 0);
        sorter.analyze();


        // worst case
        items = new Integer[N];
        for (int i = 0; i < N; i++) {
            items[N-1-i] = i;
        }
        sorter.sort(items, 2);
        sorter.analyze();
    }
}
