import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    static class GasStation implements Comparable<GasStation> {

        int dist;
        int amount;

        public GasStation(int dist, int amount) {
            this.dist = dist;
            this.amount = amount;
        }

        @Override
        public int compareTo(GasStation o) {
            return o.amount - amount;
        }
    }

    static int N, L, P;
    static GasStation[] gasStations;
    static PriorityQueue<GasStation> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        N = read();
        gasStations = new GasStation[N];
        for (int i = 0; i < N; i++) {
            int a = read();
            int b = read();
            gasStations[i] = new GasStation(a, b);
        }
        Arrays.sort(gasStations, Comparator.comparingInt(o -> o.dist));
        L = read();
        P = read();
        for (GasStation gs : gasStations) {
            if (gs.dist <= P) {
                pq.add(gs);
                continue;
            }
            while (gs.dist > P) {
                if (pq.isEmpty()) {
                    System.out.println(-1);
                    return;
                }
                P += pq.poll().amount;
            }
            pq.add(gs);
        }
        while (L > P) {
            if (pq.isEmpty()) {
                System.out.println(-1);
                return;
            }
            P += pq.poll().amount;
        }
        System.out.println(N - pq.size());
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}