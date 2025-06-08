import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DFS {
    private final boolean[] marked;
    private int count;

    public  DFS(Graph G, int v) {
        marked = new boolean[G.V()];
        dfs(G, v);
    }

    public void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for(int w: G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w)
    {
        return marked[w];
    }

    public int count()
    {
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int v = 2;
        DFS dfs = new DFS(G, v);
        StdOut.printf("Is %s connected to:\n", v);
        for (int i = 0; i < G.V(); i++) {
            StdOut.printf("- %s => %b\n", i, dfs.marked(i));
        }
    }
}
