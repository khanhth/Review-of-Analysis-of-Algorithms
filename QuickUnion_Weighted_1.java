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
    while (p != id[p]) p = id[p];

    return p;
  }

  public void union(int p, int q) {
    int r_p = root(p);
    int r_q= root(q);

    if (r_p == r_q) {return;}

    if (sizes[r_p] >= sizes[r_q]) {
      id[r_q] = r_p;
      sizes[r_p] += sizes[r_q];
      sizes[r_q] = 0;
    } else {
      id[r_p] = r_q;
      sizes[r_q] += sizes[r_p];
      sizes[r_p] = 0;
    }

    System.out.printf("\n[union] %s \n", Arrays.toString(sizes));
    System.out.printf("[union] %d and %d are now connected\n", p, q);
  }

  public boolean Find(int p, int q) {
    boolean out = root(p) == root(q);
    System.out.printf("[find] Are %d and %d connected? %b\n", p, q, out);

    return out;
  }

  public static void main(String[] args) {
    QuickUnion_Weighted_1 map=new QuickUnion_Weighted_1(10);
    int i = 1, j = 2;

    map.union(i, j);
    map.Find(i, j);

    j= 3;
    map.Find(i, j);

    i = 2;
    map.Find(i, j);

    map.union(i, j);
    map.Find(i, j);

    j = 1;
    map.Find(i, j);

    i = 3;
    map.Find(i, j);
  
    i = 5;
    map.Find(i, j);

    i = 8; j = 9;
    map.Find(i, j);

    i = 7; j=8;
    map.union(i, j);
    map.Find(i, j);

    i = 9; j=7;
    map.union(i, j);
    map.Find(i, j);

    i = 6; j=8;
    map.union(i, j);
    map.Find(i, j);

    i = 2; j=8;
    map.union(i, j);
    map.Find(i, j);
  }
}
