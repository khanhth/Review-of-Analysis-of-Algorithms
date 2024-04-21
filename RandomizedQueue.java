import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;
    private int capacity;
    // construct an empty randomized queue
    public RandomizedQueue(int capacity) {
        this.capacity = capacity;
//        System.out.printf("capacity: %d\n", capacity);
        items = (Item[]) new Object[capacity];
        size = 0;
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

    public class ReverseArrayIterator implements Iterator<Item> {
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
            return items[i++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<>(5);
        q.enqueue("test");
        q.enqueue("some");
        q.enqueue("awesome");
        q.enqueue("string");
        q.enqueue("!");
        q.dequeue();
        q.dequeue();
        for (String s: q) {
            System.out.printf("*** s is: %s\n", s);
        }
        System.out.println("-------");
        for (String s: q) {
            System.out.printf("*** s is: %s\n", s);
        }
    }

}
