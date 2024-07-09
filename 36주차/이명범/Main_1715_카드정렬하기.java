import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = read();

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(read());
        }

        long result = 0;

        while (pq.size() > 1) {
            int n1 = pq.poll();
            int n2 = pq.poll();

            int sum = n1 + n2;

            result += sum;
            pq.add(sum);
        }

        System.out.println(result);
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}