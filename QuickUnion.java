public class QuickUnion {
  private int[] id;

  public QuickUnion(int N) {
    System.out.println("Number of vertexes: " + N);

    id = new int[N];
    for (int i = 0; i < N; i++) id[i] = i; 
  }

//  public int root(int p) {
//    return id[p]==p ? p : root(id[p]);
//  }

  public int root(int p) {
    while (p != id[p]) p = id[p];

    return p;
  }

  public void union(int p, int q) {
    if (!connected(p, q)) {
      id[root(p)] = root(q);
    }
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public static void main(String[] args) {
    QuickUnion site=new QuickUnion(10);
    int i = 1, j = 2;

    site.union(i, j);
    System.out.printf("\n[Union] %d and %d are now connected\n", i, j);

    System.out.printf("[Find] Are %d and %d connected? %b\n", i, j, site.connected(i, j));

    j= 3;
    System.out.printf("[Find] Are %d and %d connected? %b\n", i, j, site.connected(i, j));

    i = 2;
    System.out.printf("[Find] Are %d and %d connected? %b\n", i, j, site.connected(i, j));

    site.union(i, j);

    System.out.printf("\n[Union] %d and %d are now connected\n", i, j);
    System.out.printf("[Find] Are %d and %d connected? %b\n", i, j, site.connected(i, j));

    j = 1;
    System.out.printf("[Find] Are %d and %d connected? %b\n", i, j, site.connected(i, j));

    i = 3;
    System.out.printf("[Find] Are %d and %d connected? %b\n", i, j, site.connected(i, j));
  
    i = 5;
    System.out.printf("[Find] Are %d and %d connected? %b\n", i, j, site.connected(i, j));

    i = 8; j = 9;
    System.out.printf("[Find] Are %d and %d connected? %b\n", i, j, site.connected(i, j));
  }
}
