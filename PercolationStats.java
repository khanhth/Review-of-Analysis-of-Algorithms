import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] props;
    private double z = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        props = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            double totalOpen = 0;
            int j;
            int jRow;
            int jCol;
            while (!percolation.percolates()) {
                j = StdRandom.uniformInt(1, n * n + 1);
                jRow = (j - 1) / n + 1;
                jCol = j - (jRow - 1) * n;
                if (percolation.isOpen(jRow, jCol)) {
                    continue;
                }

                percolation.open(jRow, jCol);

                totalOpen++;
            }

            props[i] = totalOpen / (n * n);
        }

//        System.out.printf("props: %s\n", Arrays.toString(props));
//        System.out.printf("mean: %f\n", mean());
//        System.out.printf("stddev: %f\n", stddev());
//        System.out.printf("confidenceLo: %f\n", confidenceLo());
//        System.out.printf("confidenceHi: %f\n", confidenceHi());
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("n and T are required!");
        }

        new PercolationStats(
                Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(props);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(props);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - z * stddev() / Math.sqrt(props.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + z * stddev() / Math.sqrt(props.length);
    }

}
