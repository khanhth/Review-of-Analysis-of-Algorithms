import java.util.Arrays;

public class QuickUnion_Weighted {
  private int[] id;
  private int[] weights;

  public QuickUnion_Weighted(int N) {
    System.out.println("Number of sites: " + N);

    id = new int[N];
    weights = new int[N];

    for (int i = 0; i < N; i++) id[i] = i; 
  }

  public int root(int p) {
    while (p != id[p]) p = id[p];

    return p;
  }

  public void union(int p, int q) {
    int r_p = root(p);
    int r_q= root(q);

    if (r_p == r_q) return;

    if (weights[r_p] == weights[r_q]) {
      id[r_p] = r_q;
      weights[r_q]++;
      weights[r_p] = -1;
    } else if (weights[r_p] > weights[r_q]) {
      id[r_q] = r_p;
      weights[r_q] = -1;
    } else {
      id[r_p] = r_q;
      weights[r_p] = -1;
    }

    System.out.printf("\n[union] %s \n", Arrays.toString(weights));
    System.out.printf("[union] %d and %d are now connected\n", p, q);
  }

  public boolean connected(int p, int q) {
    boolean out = root(p) == root(q);
    System.out.printf("[connected] Are %d and %d connected? %b\n", p, q, out);

    return out;
  }

  public static void main(String[] args) {
    QuickUnion_Weighted map=new QuickUnion_Weighted(10);
    int i = 1, j = 2;

    map.union(i, j);
    map.connected(i, j);

    j= 3;
    map.connected(i, j);

    i = 2;
    map.connected(i, j);

    map.union(i, j);
    map.connected(i, j);

    j = 1;
    map.connected(i, j);

    i = 3;
    map.connected(i, j);
  
    i = 5;
    map.connected(i, j);

    i = 8; j = 9;
    map.connected(i, j);

    i = 7; j=8;
    map.union(i, j);
    map.connected(i, j);

    i = 9; j=7;
    map.union(i, j);
    map.connected(i, j);

    i = 6; j=8;
    map.union(i, j);
    map.connected(i, j);

    i = 2; j=8;
    map.union(i, j);
    map.connected(i, j);
  }
}
