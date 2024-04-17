import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Key> implements Iterable<Key> {
		private Node<Key> first;
		private Node<Key> last;
		private int n = 0;

		private static class Node<Item> {
	        private Item item;
	        private Node<Item> next;
	    }


		public boolean isEmpty() {
			return first == null;
		}

		public void enqueue(Key item) {
			Node<Key> newNode = new Node<Key>();
			newNode.item = item;
			if (isEmpty()) first = last = newNode;
			else {
				last.next = newNode;
				last = newNode;
			}
			n++;
		}

		public Key dequeue() {
			if (first == null) return null;

			Key item = first.item;
			first = first.next;

			if (isEmpty()) last = null;   // to avoid loitering
			n--;

			return item;
		}	

		public Iterator<Key> iterator()  {
	        return new LinkedIterator(first);
	    }

	    // an iterator, doesn't implement remove() since it's optional
	    private class LinkedIterator implements Iterator<Key> {
	        private Node<Key> current;

	        public LinkedIterator(Node<Key> first) {
	            current = first;
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
	            current = current.next;
	            return item;
	        }
	    }
	
	}