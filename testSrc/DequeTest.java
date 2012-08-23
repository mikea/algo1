import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author mike.aizatsky@gmail.com
 */
public class DequeTest extends TestCase {
    public void testSmoke() throws Exception {
        Deque<String> deque = new Deque<String>();

        assertTrue(deque.isEmpty());

        deque.addFirst("a");
        deque.addFirst("1");
        deque.addLast("b");
        deque.addLast("c");

        Collection<String> data = new ArrayList<String>();
        for (String s : deque) {
            data.add(s);
        }
        assertEquals("[1, a, b, c]", data.toString());

        assertEquals(4, deque.size());
        assertEquals("1", deque.removeFirst());
        assertEquals("a", deque.removeFirst());
        assertEquals("c", deque.removeLast());
        assertEquals("b", deque.removeFirst());
        assertTrue(deque.isEmpty());
    }
}
