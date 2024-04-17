public class QuickFind {
  private int[] id;

  public QuickFind(int N) {
    System.out.println("[QuickFind] Number of vertexes: " + N);

    id = new int[N];
    for (int i = 0; i < N; i++) id[i] = i; 
  }

  public void union(int p, int q) {
    if (id[p] != id[q]) {
      for (int i = 0; i < id.length; i++) {
        if (id[i] == id[p]) id[i] = id[q];
      }
    }

    System.out.printf("\n[Union] %d and %d are now connected\n", p, q);
  }

  public boolean Find(int p, int q) {
    
    boolean out = id[p] == id[q];
    System.out.printf("[Find] Are %d and %d connected? %b\n", p, q, out);

    return out;
  }

  public static void main(String[] args) {
    QuickFind site=new QuickFind(10);
    int i = 1, j = 2;

    site.union(i, j);

    site.Find(i, j);

    j= 3;
    site.Find(i, j);

    i = 2;
    site.Find(i, j);

    site.union(i, j);
    site.Find(i, j);

    j = 1;
    site.Find(i, j);

    i = 3;
    site.Find(i, j);
  
    i = 5;
    site.Find(i, j);

    i = 8; j = 9;
    site.Find(i, j);
  }
}
