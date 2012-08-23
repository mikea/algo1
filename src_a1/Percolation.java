/**
 * @author mike.aizatsky@gmail.com
 *
 * 0 - source, 1 - sink. Index: i * n + j + 2
 */
public class Percolation {
    private final int n;
    private final int source;
    private final int sink;
    private final WeightedQuickUnionUF uf1;
    private final WeightedQuickUnionUF uf2;
    private final boolean[] open;

    public Percolation(int n)  {
        this.n = n;
        uf1 = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 2);
        open = new boolean[n * n + 2];
        source = 0;
        sink = 1;
    }

    public void open(int i, int j)  {
        checkBounds(i);
        checkBounds(j);

        int idx = idx(i, j);
        open[idx] = true;

        connectIfOpen(i, j, i - 1, j);
        connectIfOpen(i, j, i + 1, j);
        connectIfOpen(i, j, i, j - 1);
        connectIfOpen(i, j, i, j + 1);

        if (i == 1) {
            // connect to top
            uf1.union(idx, source);
            uf2.union(idx, source);
        }

        if (i == n) {
            // connect to bottom
            uf1.union(idx, sink);
        }
    }

    private void checkBounds(int i) {
        if (!isInBounds(i)) {
            throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds.", i));
        }
    }

    private boolean isInBounds(int i) {
        return i > 0 && i <= n;
    }

    private void connectIfOpen(int i, int j, int i1, int j1) {
        if (!isInBounds(i) || !isInBounds(j) || !isInBounds(i1) || !isInBounds(j1)) {
            return;
        }

        if (isOpen(i1, j1)) {
            uf1.union(idx(i, j), idx(i1, j1));
            uf2.union(idx(i, j), idx(i1, j1));
        }
    }

    public boolean isOpen(int i, int j)  {
        checkBounds(i);
        checkBounds(j);
        return open[idx(i, j)];
    }

    public boolean isFull(int i, int j)  {
        checkBounds(i);
        checkBounds(j);
        return isOpen(i, j) && uf2.connected(idx(i, j), 0);
    }

    public boolean percolates()  {
        return uf1.connected(0, 1);
    }

    private int idx(int i, int j) {
        checkBounds(i);
        checkBounds(j);
        return (i - 1) * n + j - 1 + 2;
    }
}