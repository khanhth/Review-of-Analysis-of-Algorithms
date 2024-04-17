import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Key> implements Iterable<Key> {
		private Node<Key> first;
		private Node<Key> last;
		private int n = 0;

		private static class Node<Item> {
	        private Item item;
	        private Node<Item> prev;
	    }


		public boolean isEmpty() {
			return first == null;
		}

		public void push(Key item) {
			Node<Key> oldLast = last;
			last = new Node<Key>();
			last.item = item;
			if (isEmpty()) first = last;
			else {
				last.prev = oldLast;
			}
			n++;
		}

		public Key pop() {
			if (last == null) return null;

			Key item = last.item;
			last = last.prev;
			n--;

			return item;
		}	

		public Iterator<Key> iterator()  {
	        return new LinkedIterator(last);
	    }

	    // an iterator, doesn't implement remove() since it's optional
	    private class LinkedIterator implements Iterator<Key> {
	        private Node<Key> current;

	        public LinkedIterator(Node<Key> last) {
	            current = last;
	        }

	        public boolean hasNext()  {
	        	return current != null;
	        }
	        public void remove()      {
	        	throw new UnsupportedOperationException();
	        }

	        public Key next() {
	            if (!hasNext()) throw new NoSuchElementException();
	            Key item = current.item;
	            current = current.prev;
	            return item;
	        }
	    }

	    public String toString() {
	    	String out = "";
	    	Iterator itr = iterator();
	    	while (itr.hasNext()) {
	    		out += ", " + itr.next();
	    	}
			return "[" + out.substring(2, out.length()) + "]";
	    }

	    public static void main(String[] args) {
	    	System.out.println("***");
	    	Stack<Integer> stack = new Stack<>();
	    	stack.push(3);
	    	stack.push(5);
	    	stack.push(7);
	    	stack.push(10);
	    	stack.pop();
	    	stack.pop();
	    	stack.push(100);

	    	for (int x: stack) {
	    		System.out.println(x);
	    	}

	    	System.out.println(stack.toString());
	    }
	
	}