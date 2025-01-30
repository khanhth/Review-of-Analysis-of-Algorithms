public class MaxPQ <Key extends Comparable<Key>> {
    private Key[] arr;
    private int size;
    
    public MaxPQ(int maxSize) {
        arr = (Key[]) new Comparable[maxSize + 1];
    }

    public Key max() {
        return arr[1];
    }

    public void delMax() {
        arr[1] = arr[size];
        exch(1, size--);
        arr[size + 1] = null;
        sink(1);
    }

    public void insert(Key key) {
        arr[++size] = key;
        swim(size);
    }

    public void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    public void sink(int k) {
        while (k * 2 <= size) {
            int maxChildIdx = k * 2 == size ? k * 2 : less(k*2, k*2 + 1) ? k*2 + 1 : k*2;
            if (less(k, maxChildIdx)) {
                exch(k, maxChildIdx);
            }
            k = maxChildIdx;
        }
    }

    private boolean less(int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    private void exch(int i, int j) {
        Key tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }
}
