import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author mike.aizatsky@gmail.com
 */
public class RandomizedQueueTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        StdRandom.setSeed(1234);
    }

    public void testSmoke() throws Exception {
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        assertTrue(q.isEmpty());
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        q.enqueue("d");
        assertEquals(4, q.size());

        Collection<String> data = new ArrayList<String>();
        for (String s : q) {
            data.add(s);
        }
        assertEquals("[c, d, b, a]", data.toString());

        data = new ArrayList<String>();
        for (String s : q) {
            data.add(s);
        }
        assertEquals("[d, b, c, a]", data.toString());

        assertEquals("b", q.sample());
        assertEquals("a", q.sample());

        assertEquals("a", q.dequeue());
        assertEquals("c", q.dequeue());
        assertEquals("d", q.dequeue());
        assertEquals("b", q.dequeue());
        assertTrue(q.isEmpty());
    }

    public void testAddRemove() throws Exception {
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        for (int i = 0; i < 10000; ++i) {
            q.enqueue("a");
        }
        for (int i = 0; i < 10000; ++i) {
            q.dequeue();
        }
    }
}
