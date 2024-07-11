import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = read(), M = read();
        int[] counts = new int[N + 1];
        List<Integer>[] list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int first = read();
            int later = read();
            counts[later]++;
            list[first].add(later);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (counts[i] > 0) continue;
            pq.add(i);
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Integer cur = pq.poll();
            sb.append(cur).append(" ");
            for (Integer next : list[cur]) {
                if (--counts[next] == 0) {
                    pq.add(next);
                }
            }
        }
        System.out.println(sb);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}