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

    System.out.printf("\n[union] %d and %d are now connected\n", p, q);
  }

  public boolean Find(int p, int q) {
    
    boolean out = id[p] == id[q];
    System.out.printf("[find] Are %d and %d connected? %b\n", p, q, out);

    return out;
  }

  public static void main(String[] args) {
    QuickFind map=new QuickFind(10);
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
