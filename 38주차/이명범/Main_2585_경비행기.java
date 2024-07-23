import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int n, k;
    static Location[] locs;

    public static void main(String[] args) throws Exception {
        n = read(); k = read();
        locs = new Location[n + 1];

        for (int i = 0; i < n; i++) {
            locs[i] = new Location(read(), read());
        }
        locs[n] = new Location(10000, 10000);

        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int l = 0;
        int r = 1500;

        while (l <= r) {
            int m = (l + r) / 2;

            if (bfs(m)) r = m - 1;
            else l = m + 1;
        }

        return l;
    }

    private static boolean bfs(int m) {
        Queue<Location> q = new LinkedList<>();
        boolean[] visit = new boolean[n + 1];

        q.add(new Location(0, 0));

        int count = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Location cur = q.poll();

                if (cur.r == 10000 && cur.c == 10000) return true;

                for (int next = 0; next <= n; next++) {
                    if (visit[next]) continue;
                    if ((int) Math.ceil(calculateDistance(cur, locs[next]) / 10) > m) continue;

                    q.add(locs[next]);
                    visit[next] = true;
                }
            }

            if (count++ > k) break;
        }
        return false;
    }

    private static double calculateDistance(Location a, Location b) {
        return Math.ceil(Math.sqrt(Math.pow(a.r - b.r, 2) + Math.pow(a.c - b.c, 2)));
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}