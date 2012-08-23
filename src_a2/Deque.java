import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author mike.aizatsky@gmail.com
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head = null;
    private Node<Item> tail = null;
    private int size = 0;

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node<Item> oldHead = head;
        head = new Node<Item>(item, head, null);
        if (oldHead != null) oldHead.prev = head;
        if (tail == null) tail = head;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node<Item> oldTail = tail;
        tail = new Node<Item>(item, null, oldTail);
        if (head == null) head = tail;
        if (oldTail != null) oldTail.next = tail;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        size--;

        if (size == 0) {
            assert head == tail;
            Item result = head.t;
            head = null;
            tail = null;
            return result;
        } else {
            Item result = head.t;
            head = head.next;
            head.prev = null;
            return result;
        }
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        size--;
        if (size == 0) {
            assert head == tail;
            Item result = head.t;
            head = null;
            tail = null;
            return result;
        } else {
            Item result = tail.t;
            tail = tail.prev;
            tail.next = null;
            return result;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyIterator<Item>(head);
    }

    private static class Node<T> {
        private final T t;
        private Node<T> next;
        private Node<T> prev;

        private Node(T t, Node<T> next, Node<T> prev) {
            this.t = t;
            this.next = next;
            this.prev = prev;
        }
    }

    private static class MyIterator<Item> implements Iterator<Item> {
        private Node<Item> node;

        private MyIterator(Node<Item> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item result = node.t;
            node = node.next;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
