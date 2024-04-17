import java.util.Arrays;

public class QuickUnion_Weighted_1 {
  private int[] id;
  private int[] sizes;

  public QuickUnion_Weighted_1(int N) {
    System.out.println("Number of sites: " + N);

    id = new int[N];
    sizes = new int[N];

    for (int i = 0; i < N; i++) {
      id[i] = i;
      sizes[i] = 1;
    }
  }

  public int root(int p) {
    while (p != id[p]) {
//      System.out.printf("[root]*** id, p, id[p], id[id[p]]: %s, %s, %s, %s\n", Arrays.toString(id), p, id[p], id[id[p]]);
      id[p] = id[id[p]]; // Gradually flatten out the tree, the tree MAY NOT completely flat
      p = id[p];
    }
//    System.out.printf("[root] id: %s\n", Arrays.toString(id));

    return p;
  }

  public void union(int p, int q) {
    int r_p = root(p);
    int r_q= root(q);

    if (r_p == r_q) return;

    if (sizes[r_p] >= sizes[r_q]) {
      id[r_q] = r_p;
      sizes[r_p] += sizes[r_q];
      sizes[r_q] = 0;
    } else {
      id[r_p] = r_q;
      sizes[r_q] += sizes[r_p];
      sizes[r_p] = 0;
    }

    System.out.printf("\n[union] %d and %d are now connected\n", p, q);
    System.out.printf("[union] sizes: %s \n", Arrays.toString(sizes));
  }

  public boolean connected(int p, int q) {
    boolean out = root(p) == root(q);
    System.out.printf("[connected] Are %d and %d connected? %b\n", p, q, out);

    return out;
  }

  public static void main(String[] args) {
    QuickUnion_Weighted_1 map=new QuickUnion_Weighted_1(12);
    int i = 1, j = 2;

    map.union(i, j);
//    map.connected(i, j);

    j= 3;
//    map.connected(i, j);

    i = 2;
//    map.connected(i, j);

    map.union(i, j);
//    map.connected(i, j);

    j = 1;
//    map.connected(i, j);

    i = 3;
//    map.connected(i, j);
  
    i = 5;
//    map.connected(i, j);

//    i = 8; j = 9;
//    map.connected(i, j);

    i = 7; j=8;
    map.union(i, j);

    i = 9; j=7;
    map.union(i, j);

    i = 4; j=6;
    map.union(i, j);

    i = 5; j=6;
    map.union(i, j);

    i = 5; j=2;
    map.union(i, j);
//    map.connected(i, j);
//    map.connected(3, 8);

    i = 0; j = 10;
    map.union(i, j);

    i = 10; j = 11;
    map.union(i, j);
    i = 7; j = 10;
    map.union(i, j);

    System.out.printf("[main] id: %s\n", Arrays.toString(map.id));
    map.union(7, 4);
    System.out.printf("[main] id: %s\n", Arrays.toString(map.id));
    System.out.printf("[main] root of 10: %d\n", map.root(10));
    System.out.printf("[main] root of 11: %d\n", map.root(11));
  }
}
