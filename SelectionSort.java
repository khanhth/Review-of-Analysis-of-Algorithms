import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class SelectionSort {
    private SortAnalyzer analyzer;
    private boolean sorted;
    private Comparable[] items;
    private int inputType; // 0: best, 2: worst

    public SelectionSort() {
    }

    public void sort(Comparable[] items, int type) {
        analyzer = new SortAnalyzer();
        this.items = items;
        inputType = type;

        for (int i = 0; i < items.length - 1; i++) {
            int currentMin = i + 1;
            for (int j = i + 2; j < items.length; j++) {
                analyzer.compare(); // compare op
                if (items[j].compareTo(items[currentMin]) < 0) {
                    currentMin = j;
                }
            }

            analyzer.compare(); // compare op
            if (items[i].compareTo(items[currentMin]) > 0) {
                analyzer.exchange(); // exchange op
                exch(items, i, currentMin);
            }
        }
        sorted = true;
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

    public float growthConstant(int statType) {
        float r;
        int N = items.length;
        switch (statType) {
            case 1: // compares
                r = (float) analyzer.getCompares() / (N*N);
                break;
            case 2: // exchanges
                r = (float) analyzer.getExchanges() / N;
                break;
            default: // total
                r = (float) analyzer.ops() / (N*N);
                break;
        }

        return r;
    }

    private void analyze() {
        if (!sorted) System.out.println("[!] items have NOT been sorted.");
        String[] exps;;
        String caseName;
        switch (inputType) {
            case 0:
                caseName = "[BEST] Compares = N^2 | Exchanges = 0 (Or N)";
                exps = new String[]{"1/2", "1/2", "0"};
                break;
            case 2:
                caseName = "[WORST] Compares = N^2 | Exchanges = N";
                exps = new String[]{"1/2", "1/2", "1"};
                break;
            default: // random
                caseName = "[RAND] Compares = N^2 | Exchanges / N";
                exps = new String[]{"1/2", "1/2", "< 1"};
                break;
        }

        int N = items.length;
        System.out.printf("[*] N: %d, N^2: %d, ops: %d\n" +
                        "\t%s\n\tTotal: %f [Exp. %s]" +
                        "\tcomp.: %f [Exp. %s]" +
                        "\texch.: %f [Exp. %s]\n\n",
                N, N*N, analyzer.ops(), caseName,
                growthConstant(0), exps[0],
                growthConstant(1), exps[1],
                growthConstant(2), exps[2]);
    }

    public static void main(String[] args) {
        Integer[] items = new Integer[]{3, 2, 10, 15, 9, 4, 0};

        SelectionSort sorter = new SelectionSort();
        items = new Integer[]{3, 2, 10, 15, 9, 4, -2, 0, 102, -12, 88, 50,
                22, 19, -1, 10, 31, 7, 22};
        sorter.sort(items, 1);
        System.out.printf("[sorted] items: %s\n", Arrays.toString(items));
        sorter.analyze();

        items = sample(200);
        sorter.sort(items, 1);
        sorter.analyze();
        sorter.resort();
        sorter.analyze();

        items = sample(500);
        sorter.sort(items, 1);
        sorter.analyze();


        items = sample(1000);
        sorter.sort(items, 1);
        sorter.analyze();

        sorter.resort();
        sorter.analyze();

        // best case
        int N = 500;
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

    private static Integer[] sample(int n) {
        Integer[] out = new Integer[n];
        for (int i = 0; i < n; i++) {
            out[i] = StdRandom.uniformInt(-1000, 1000);
        }

        return out;
    }
}
