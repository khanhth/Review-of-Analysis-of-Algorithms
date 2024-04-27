import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class MergeSortAnalytics {
    private static Comparable[] items;
    private static MergeSort2024 sorter;

    private static boolean newSort;
    private static boolean sorted;
    private static SortAnalyzer analyzer;
    private static int inputType;

    public static void sort(MergeSort2024 ms, Comparable[] items, int inType) {
        newSort = true;
        inputType = inType;
        sorter = ms;
        sorter.sort(items);
        sorted = true;
    }
    public static void sort(MergeSort2024 ms, Comparable[] items) { // random input
        sort(ms, items, 1);
    }


    private static Integer[] sample(int n) {
        Integer[] out = new Integer[n];
        for (int i = 0; i < n; i++) {
            out[i] = StdRandom.uniformInt(-1000, 1000);
        }

        return out;
    }

    private static void resort() {
        if (!sorted) {
            throw new UnsupportedOperationException("Can only call resort() after items have been sorted.");
        }
        analyzer.refresh();
        inputType = 0;
        sort(sorter, items, inputType);
        newSort = false;
    }

    private static float growthConstant(int statType) {
        float r;
        int N = items.length;
        float nLogN = (float) (N * Math.log(N) / Math.log(2));
        switch (statType) {
            case 1: // compares
                r = (float) analyzer.getCompares() / nLogN;
                break;
            case 2: // accesses
                r = (float) analyzer.getAccesses() / (6*nLogN);
                break;
            default: // total
                r = (float) analyzer.total() / (7*nLogN);
                break;
        }

        return r;
    }

    private static void analyze() {
        if (!sorted) System.out.println("[!] items have NOT been sorted.");
        String[] exps;;
        String caseName;
        switch (inputType) {
            case 0: // best
                caseName = "[BEST] Total / (7*N*lg(N)) \t Compares / (N*lg(N)) \t Accesses / (6*N*log(N))";
                exps = new String[]{"~1", "~1", "~1"};
                break;
            case 2: // worst
                caseName = "[WORST] Total / (7*N*lg(N)) \t Compares / (N*log(N)) \t Accesses / (6*N*log(N))";
                exps = new String[]{"~1", "~1", "~1"};
                break;
            default: // random
                caseName = "[RAND] Total / (7*N*lg(N)) \t Compares / (N*log(N)) \t Accesses / (6*N*log(N))";
                exps = new String[]{"~1", "~1", "~1"};
                break;
        }

        int N = items.length;
        System.out.printf("[*] N: %d, N*log(N): %f, comp.: %d, acc.: %d, total: %d\n" +
                        "\t%s\n\t\t(Exp. %s): %f" +
                        "\t(Exp. %s): %f" +
                        "\t(Exp. %s): %f\n\n",
                N, N*Math.log(N)/Math.log(2), analyzer.getCompares(),
                analyzer.getAccesses(), analyzer.total(), caseName,
                exps[0], growthConstant(0),
                exps[1], growthConstant(1),
                exps[2], growthConstant(2));
    }

    public static void main(String[] args) {
        analyzer = new SortAnalyzer();
        MergeSort2024 sorter = new MergeSort2024(analyzer);
        items = new Integer[]{3, 2, 10, 15, 9, 4, -2, 0, 102, -12, 88, 50,
                22, 19, -1, 10, 31, 7, 22};
        sort(sorter, items);
        System.out.printf("[sorted] items: %s\n", Arrays.toString(items));
        analyze();

        // worst case
        int N = 200;
        items = new Integer[N];
        for (int i = 0; i < N; i++) {
            items[N-1-i] = i;
        }
        sort(sorter, items, 2);
        analyze();

        items = sample(200);
        sort(sorter, items);
        analyze();

        // best case
        resort();
        analyze();
    }
}
