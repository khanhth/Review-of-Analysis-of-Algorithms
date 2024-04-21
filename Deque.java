import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    public Node first;
    public Node last;
    public int size;

    public class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node oldFirst = first;
        first = new Node(item);
        if (isEmpty()) last = first;
        first.next = oldFirst;
        if (oldFirst != null) {
            oldFirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node oldLast = last;
        last = new Node(item);
        if (isEmpty()) first = last;
        last.prev = oldLast;
        if (oldLast != null) {
            oldLast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node oldFirst = first;
        first = oldFirst.next;
        if (first == null)
            last = null;
        else
            first.prev = null;
        size--;

        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node oldLast = last;
        last = oldLast.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;

        return oldLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    public class ItemIterator implements  Iterator<Item> {
        private Node current;

        public ItemIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;

            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deq = new Deque<>();

        deq.addFirst("10");
        deq.addFirst("9");
        deq.addFirst("8");
        deq.addFirst("7");
        deq.addFirst("6");
//        System.out.printf("size is: %d\n", deq.size());
//        for (String item : deq) System.out.printf("item: %s\n", item);
        deq.addLast("11");
        deq.removeFirst();
        deq.removeLast();
        deq.removeLast();
        deq.removeLast();
        deq.removeLast();
        deq.removeLast();
//        deq.removeLast();
//        deq.removeLast();
//        System.out.printf("size is: %d\n", deq.size());
        for (String item : deq) System.out.printf("item: %s\n", item);
//        Iterator<String> it = deq.iterator();
//        while (it.hasNext()) System.out.printf("item: %s\n", it.next());
    }

}
