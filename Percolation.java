import java.util.Arrays;

public class Percolation {
    private final int[] ids;
    private final int[] weights;
    private final int n;
    private int numberOfOpenSites = 0;
    // creates n-by-n grid, with all ids initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive.");
        }

        this.n = n;
        ids = new int[n * n + 2];
        weights = new int[n * n + 2];

        ids[0] = 0;
        ids[n * n + 1] = n * n + 1;

        for (int i = 1; i <= n * n; i++) {
            ids[i] = -1;
            weights[i] = 1;
        }

        weights[0] = 1;
        weights[n * n + 1] = 1;
    }

    public int[] adjacentSites(final int row, final int col) {
        validateRolCol(row, col);

        int[] out = {-1, -1, -1, -1};

        if ((row == 0) || (row == n + 1)) {
            return out; // NOT QUITE "TRUE" BUT IT WORKS
        }

        if (col > 1) {
            out[0] = (row - 1) * n + (col - 1);
        }

        if (col < n) {
            out[2] = (row - 1) * n + col + 1;
        }

        if (row == 1) {
            out[1] = 0;
        } else {
            out[1] = (row - 2) * n + col;
        }

        if (row == n) {
            out[3] = n * n + 1;
        } else {
            out[3] = row * n + col;
        }

//        System.out.printf("[adjacentSites] adjacentSites(%d, %d) is %s\n",
//                row, col, Arrays.toString(out));

        return out;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        numberOfOpenSites++;

        int i = (row - 1) * n + col;
        ids[i] = i; // initial setting

        for (int adj: adjacentSites(row, col)) {
            if (adj == -1) continue;

            int adjRow = (adj - 1) / n + 1, adjCol = adj - (adjRow - 1) * n;
            if (isOpen(adjRow, adjCol)) {
                union(i, adj);
            }
        }

//        System.out.printf("\t UPDATED ids: %s\n", Arrays.toString(ids));
    }

    private void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        if (rootP == rootQ) return;

        if (weights[rootP] == weights[rootQ]) {
            int rootRight = rootP == 0 || rootP == n * n + 1 ? rootP : rootQ;
            int rootLeft = rootRight == rootP ? rootQ : rootP;
            ids[rootLeft] = rootRight;
            weights[rootRight] += weights[rootLeft];
            weights[rootLeft] = 0; // OPTIONAL
        } else if (weights[rootP] > weights[rootQ]) {
            ids[rootQ] = rootP;
            weights[rootP] += weights[rootQ];
            weights[rootQ] = 0; // OPTIONAL
        } else {
            ids[rootP] = rootQ;
            weights[rootQ] += weights[rootP];
            weights[rootP] = 0; // OPTIONAL
        }
//        System.out.printf("\n\n[union] (%d, %d) are CONNECTED!\n", p, q);
    }

    public int root(final int i) {
        while(i != ids[i]) {
            i = ids[i];
        }

        return i;
    }

    public void validateRolCol(final int row, final int col) {
        if (row < 0 || row > (n + 1) || col < 0 || col > n) {
            throw new IllegalArgumentException("Either row or col or both is"
                    + " not valid");
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(final int row, final int col) {
        validateRolCol(row, col);

        if ((row == 0) || (row  == n + 1)) {
            return true;
        }

        return ids[(row - 1) * n + col] != -1;
    }

    // is the site (row, col) full?
    public boolean isFull(final int row, final int col) {
        return connected((row - 1) * n + col, 0);
    }

    // returns the number of open ids
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean connected(final int p, final int q) {
        boolean out = false;
        int pRow = (p - 1) / n + 1;
        int pCol = p - (pRow - 1) * n;
        int qRow = (q - 1) / n + 1;
        int qCol = q - (qRow - 1) * n;
        if (isOpen(pRow, pCol) & isOpen(qRow, qCol)) {
            out = root(p) == root(q);
        }

        return out;
    }

    // does the system percolate?
    public boolean percolates() {
        return connected(n * n + 1, 0);
    }

    public void openSites() {
//        System.out.printf("[openSites] Open site(s): 0, ");
        for (int i = 1; i < n * n + 1; i++) {
            if (ids[i] != -1) {
                System.out.printf("%d, ", i);
            }
        }

        System.out.printf("%d\n", n * n + 1);
    }

    // test client (optional)
    public static void main(final String[] args) {
        Percolation perc = new Percolation(3);
        perc.openSites();
    }
}
