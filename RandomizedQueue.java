import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;
    private int capacity = 1;

    // construct an empty randomized queue
    public RandomizedQueue() {
//        System.out.printf("capacity: %d\n", capacity);
        items = (Item[]) new Object[capacity];
        size = 0;
    }

    private void resize(int capacity) {
//        System.out.printf("[resize] from %d to %d\n", size, capacity);
        this.capacity = capacity;
        Item[] copy = (Item[]) new Object[capacity];

        int i = 0;
        for (Item item: items) {
            if (item != null) copy[i++] = item;
        }

        items = copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
//        System.out.printf("items: %s\n", Arrays.toString(items));
        items[size++] = item;
        if (size == capacity) resize(size*2);
    }

    // remove and return a random item
    public Item dequeue() {
        int i = StdRandom.uniformInt(0, capacity);
//        System.out.printf("*** items: %s, \ti: %d\n", Arrays.toString(items), i);
        while (items[i] == null) {
//            System.out.printf("new i: %d\n", i);
            i = StdRandom.uniformInt(0, capacity);
        }
        Item item = items[i];
        items[i] = null;
        size--;
        if (size > 0 && size == capacity/4) resize(capacity/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return items[StdRandom.uniformInt(0, size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator(items);
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;
        private Item[] items;

        public ReverseArrayIterator(Item[] items) {
            this.items = (Item[]) new Object[size];
            for (Item item : items) {
                if (item != null) {
                    int j = StdRandom.uniformInt(0, size);
                    while (this.items[j] != null) {
                        j = StdRandom.uniformInt(0, size);
                    }
                    this.items[j] = item;
                }
            }
            i = 0;
        }

        public boolean hasNext() {
            return i < items.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[i++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<>();
        q.enqueue("test");
        q.enqueue("some");
        q.enqueue("awesome");
        q.enqueue("string");
        q.enqueue("!");
        q.enqueue("1");
        q.enqueue("2");
        q.enqueue("3");
        q.enqueue("4");
        q.enqueue("5");
        q.enqueue("6");
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        for (String s: q) {
            System.out.printf("*** s is: %s\n", s);
        }
        System.out.println("[-------]");
        for (String s: q) {
            System.out.printf("*** s is: %s\n", s);
        }
        System.out.println("[-------]");
        for (String s: q) {
            System.out.printf("*** s is: %s\n", s);
        }
    }

}
