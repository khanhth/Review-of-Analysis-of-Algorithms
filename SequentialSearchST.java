public class SequentialSearchST<Key, Value> {
	private Node first;
	private int n = 0;

	private class Node {
		private Key key;
		private Value val;
		private Node next;

		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	public SequentialSearchST() {
		
	}

	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

		for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }

		Node newNode = new Node(key, val, first);
		first = newNode;
		n++;
	}

	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");

		for(Node x = first; x != null; x = x.next) {
			if (x.key == key) {
				return x.val;
			}
		}

		return null;
	}

	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		
		first = delete(first, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null) return null;

		if (key.equals(x.key)) {
			n--;
			return x.next;
		}

		x.next = delete(x.next, key);

		return x;
	}


	public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }


	public static void main(String[] args) {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		st.put("A", 2);
		st.put("S", 3);
		st.put("T", 6);
		st.put("W", 0);
		st.put("A", 9);
		for (String x:st.keys()) {
			System.out.printf("Keys: %s - Values: %s\n", x, st.get(x));
		}
		
		System.out.println(st.get("A"));
		System.out.println(st.n);
		st.delete("A");
		st.delete("B");
		st.delete("T");
		System.out.println(st.n);
		System.out.println(st.get("A"));

		for (String x:st.keys()) {
			System.out.printf("Keys: %s - Values: %s\n", x, st.get(x));
		}
	}
}




