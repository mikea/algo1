/**
 * @author mike.aizatsky@gmail.com
 */
public class PercolationStats {
    private final double mean;
    private final double stddev;

    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) throw new IllegalArgumentException();
        double[] measurements = new double[t];
        for (int i = 0; i < t; ++i) {
            Percolation percolation = new Percolation(n);

            int k;
            // k will increment after body, start with 0.
            for (k = 0; !percolation.percolates(); ++k) {
                int x;
                int y;

                do {
                    x = StdRandom.uniform(n) + 1;
                    y = StdRandom.uniform(n) + 1;
                } while (percolation.isOpen(x, y));

                percolation.open(x, y);
            }

            measurements[i] = (double) k / (n * n);
        }

        mean = StdStats.mean(measurements);
        stddev = StdStats.stddev(measurements);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);

        double mean = stats.mean();
        double stddev = stats.stddev();

        double conf1 = mean - 1.96 * stddev / Math.sqrt(t);
        double conf2 = mean + 1.96 * stddev / Math.sqrt(t);

        StdOut.printf("mean                    = %f\n", mean);
        StdOut.printf("stddev                  = %f\n", stddev);
        StdOut.printf("95%% confidence interval = %f, %f\n", conf1, conf2);

    }
}
