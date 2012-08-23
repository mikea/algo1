/**
 * @author mike.aizatsky@gmail.com
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        for (String s : StdIn.readStrings()) {
            queue.enqueue(s);
        }

        for (int i = 0; i < k && !queue.isEmpty(); ++i) {
            StdOut.println(queue.dequeue());
        }
    }
}
