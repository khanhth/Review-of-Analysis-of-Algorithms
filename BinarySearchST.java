import java.security.Key;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Comparable[] keys;
    private Object[] values;
    private int N;

    public BinarySearchST(int capacity)
    {   // See Algorithm 1.1 for standard array-resizing code.
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size()
    {  return N;  }

    public Value get(Key key)
    {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return (Value) values[i];
        else                                      return null;
    }

    public boolean contains(Key key)
    {  return get(key) != null;  }

    public void put(Key key, Value val)
    {
        if (val == null) { delete(key); return; }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = val;
        N++;
    }
    public void delete(Key key)
    {
        if (!contains(key)) return;
        int i = rank(key);
        for (int j = i; j < N-1; j++) {
            keys[j] = keys[j+1];
            values[j] = values[j+1];
        }
        keys[N-1] = null;
        values[N-1] = null;
        N--;
    }
    public Key min()
    {
        if (isEmpty()) return null;
        return (Key) keys[0];
    }
    public Key max()
    {
        if (isEmpty()) return null;
        return (Key) keys[N-1];
    }
    public Key select(int k)
    {
        if (k < 0 || k >= N) return null;
        return (Key) keys[k];
    }
    public Key floor(Key key)
    {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return (Key) keys[i];
        if (i == 0) return null;
        else        return (Key) keys[i-1];
    }
    public Key ceiling(Key key)
    {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return (Key) keys[i];
        if (i == N) return null;
        else        return (Key) keys[i];
    }
    public int size(Key lo, Key hi)
    {
        if (lo == null || hi == null) throw new IllegalArgumentException("argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }
    public Iterable<Key> keys(Key lo, Key hi)
    {
        if (lo == null || hi == null) throw new IllegalArgumentException("argument to keys() is null");
        if (lo.compareTo(hi) > 0) return new Queue<Key>();
        Queue<Key> queue = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue((Key) keys[i]);
        if (contains(hi)) queue.enqueue((Key) keys[rank(hi)]);
        return queue;
    }
    public Iterable<Key> keys()
    {
        return keys(min(), max());
    }
    private int rank(Key key)
    {
        int mid;
        int lo = 0, hi = N-1;
        while (lo <= hi) {
            mid = (hi + lo) / 2;
            if (keys[mid].compareTo(key) < 0) lo = mid + 1;
            else if (keys[mid].compareTo(key) > 0) hi = mid - 1;
            else return mid; // found
        }
        return lo;
    }
    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>(10);
        st.put("G", 100);
        st.put("C", 15);
        st.put("K", 1);

        System.out.printf("Size: %d\n", st.size());
        System.out.printf("G: %d\n", st.get("G"));
        System.out.printf("C: %d\n", st.get("C"));
        System.out.printf("K: %d\n", st.get("K"));
        System.out.printf("Min: %s\n", st.min());
        System.out.printf("Max: %s\n", st.max());
    }
    private boolean isEmpty() {
        return N == 0;
    }

}
