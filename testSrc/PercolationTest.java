import junit.framework.TestCase;

/**
 * @author mike.aizatsky@gmail.com
 */
public class PercolationTest extends TestCase {
    public void testIsFull1() throws Exception {
        Percolation p = new Percolation(10);

        assertFalse(p.isFull(1, 1));
        p.open(1, 1);
        assertTrue(p.isFull(1, 1));

        assertFalse(p.isFull(1, 2));
        assertFalse(p.isFull(2, 1));
        p.open(2, 1);
        assertTrue(p.isFull(2, 1));
    }

    public void testIsFull2() throws Exception {
        Percolation p = new Percolation(2);

        assertFalse(p.isFull(2, 2));
        p.open(2, 2);
        assertFalse(p.isFull(2, 2));
        p.open(1, 1);
        assertFalse(p.isFull(2, 2));
        p.open(1, 2);
        assertTrue(p.isFull(2, 2));
    }

    public void testBackwash() throws Exception {
        Percolation p = new Percolation(3);

        // . . x
        // . . x
        // x . x

        p.open(3, 1);
        p.open(1, 3);
        p.open(2, 3);
        p.open(3, 3);

        assertTrue(p.isFull(3, 3));
        assertFalse(p.isFull(3, 1));
    }
}
