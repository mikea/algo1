/**
 * @author mike.aizatsky@gmail.com
 */
public class Ex21 {

    public static final int SEED = 929648;

    public static void main(String[] args) {
        measure(10);
        measure(50);
        measure(100);
        measure(500);
        measure(1000);
//        measure(10000);
    }

    private static void measure(int n) {
        int k = 10;
        double total = 0;
        for (int i = 0; i < k; ++i) {
            Stopwatch stopwatch = new Stopwatch();
            Timing.trial(n, SEED);
            total += stopwatch.elapsedTime();
        }
        System.out.println(n + " : " + (total / k));
    }
}
