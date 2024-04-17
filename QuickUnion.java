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
    // if (!connected(p, q)) {
    //  id[root(p)] = root(q);
    //}
    // [IMPROVEMENT] No dumplicate tree traversal
    id[root(p)] = root(q);
    System.out.printf("\n[union] %d and %d are now connected\n", p, q);
  }

  public boolean Find(int p, int q) {
    boolean out = root(p) == root(q);
    System.out.printf("[find] Are %d and %d connected? %b\n", p, q, out);

    return out;
  }

  public static void main(String[] args) {
    QuickUnion map=new QuickUnion(10);
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
  }
}
