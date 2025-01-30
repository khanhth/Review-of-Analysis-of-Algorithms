public class MaxPQClient {
    public static void main(String[] args) {
        MaxPQ pq = new MaxPQ<>(10);
        pq.insert(10);
        pq.insert(15);
        pq.insert(2);
        pq.insert(6);
        pq.insert(26);
        pq.delMax();
        System.out.printf("max is: %d\n", pq.max());
    }
}
