import java.util.ArrayDeque;
import java.util.PriorityQueue;
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

    static class Client implements Comparable<Client> {
        Location s;
        Location e;

        public Client(int sr, int sc, int er, int ec) {
            this.s = new Location(sr, sc);
            this.e = new Location(er, ec);
        }

        public void remove() {
            s = new Location(Integer.MAX_VALUE, Integer.MAX_VALUE);
            e = new Location(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        @Override
        public int compareTo(Client o) {
            return s.r == o.s.r ? s.c - o.s.c : s.r - o.s.r;
        }
    }


    static int N, M, fuel;
    static int[][] map;
    static Client[] clients;
    static Location taxi;
    public static void main(String[] args) throws Exception {
        input();

        for (int i = 0; i < M; i++) {
            Client client = find(taxi);
            if (client == null) {
                System.out.println(-1);
                return;
            }
            int result = drive(client.s, client.e);
            if (result == -1) {
                System.out.println(-1);
                return;
            }
            client.remove();
            fuel += 2 * result;
        }
        System.out.println(fuel);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    private static Client find(Location s) {
        PriorityQueue<Client> pq = new PriorityQueue<>();
        Queue<Location> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];
        q.add(s);
        visit[s.r][s.c] = true;

        while (pq.isEmpty() && !q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Location cur = q.poll();

                for (Client client : clients) {
                    if (client.s.r == cur.r && client.s.c == cur.c) {
                        pq.add(client);
                        break;
                    }
                }

                for (int dir = 0; dir < 4; dir++) {
                    int nr = cur.r + dr[dir];
                    int nc = cur.c + dc[dir];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if (visit[nr][nc]) continue;
                    if (map[nr][nc] == 1) continue;
                    q.add(new Location(nr, nc));
                    visit[nr][nc] = true;
                }
            }
            if (fuel == 0) break;
            if (!pq.isEmpty()) break;
            fuel--;
        }
        return pq.isEmpty() ? null : pq.poll();
    }

    private static int drive(Location s, Location e) {
        Queue<Location> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];
        q.add(s);
        visit[s.r][s.c] = true;

        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Location cur = q.poll();

                if (cur.r == e.r && cur.c == e.c) {
                    taxi = e;
                    return count;
                }

                for (int dir = 0; dir < 4; dir++) {
                    int nr = cur.r + dr[dir];
                    int nc = cur.c + dc[dir];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if (visit[nr][nc]) continue;
                    if (map[nr][nc] == 1) continue;
                    q.add(new Location(nr, nc));
                    visit[nr][nc] = true;
                }
            }
            count++;
            if (fuel == 0) break;
            fuel--;
        }
        return -1;
    }

    private static void input() throws Exception {
        N = read();
        M = read();
        fuel = read();
        map = new int[N][N];
        clients = new Client[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = read();
            }
        }
        int tr = read();
        int tc = read();
        taxi = new Location(tr - 1, tc - 1);
        for (int i = 0; i < M; i++) {
            int sr = read();
            int sc = read();
            int er = read();
            int ec = read();
            clients[i] = new Client(sr - 1, sc - 1, er - 1, ec - 1);
        }
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}