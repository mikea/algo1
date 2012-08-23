import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author mike.aizatsky@gmail.com
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Item[] array = (Item[]) new Object[10];

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (array.length == size) {
            resize(array.length * 2);
        }

        array[size] = item;
        size++;
    }

    private void resize(int newSize) {
        assert newSize >= size;

        Item[] newArray = (Item[]) new Object[newSize];
        for (int i = 0; i < size; ++i) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    /**
     * delete and return a random item
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int k = StdRandom.uniform(size);
        Item result = array[k];
        array[k] = array[size - 1];
        size--;
        array[size] = null;

        if (array.length > 10 && size * 2 < array.length) {
            resize(array.length / 2);
        }

        return result;
    }

    /**
     * return (but do not delete) a random item
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return array[StdRandom.uniform(size)];
    }

    /**
     * return an independent iterator over items in random order
     */
    @Override
    public Iterator<Item> iterator() {
        final Item[] randomArray = (Item[]) new Object[size];
        for (int i = 0; i < size; ++i) {
            randomArray[i] = array[i];
        }
        StdRandom.shuffle(randomArray);

        return new Iterator<Item>() {
            private int idx = 0;
            @Override
            public boolean hasNext() {
                return idx < randomArray.length;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item result = randomArray[idx];
                idx++;
                return result;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
