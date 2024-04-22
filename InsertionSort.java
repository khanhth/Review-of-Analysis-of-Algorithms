import java.util.Arrays;

public class InsertionSort {
    public void sort(Comparable[] items) {
        for (int i = 1; i < items.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (items[i].compareTo(items[j]) < 0) {
                    insert(items, j, i);
                    i = j; // resort the array from j-th index
                    // since the (sorted) left array's been updated.
                }
            }
        }
    }

    public void insert(Comparable[] items, int start, int end) {
        Comparable startOld = items[start];
        while (end > start) items[start] = items[++start];
        items[end] = startOld;
        System.out.printf("items: %s\n", Arrays.toString(items));
    }

//    public void exch(Comparable[] items, int i, int j) {
//        Comparable tmp = items[i];
//        items[i] = items[j];
//        items[j] = tmp;
//    }

    public static void main(String[] args) {
        Integer[] items = new Integer[]{3, 2, 10, 9, 0};

        InsertionSort sorter = new InsertionSort();
        sorter.sort(items);

        System.out.printf("items: %s\n", Arrays.toString(items));
    }
}
