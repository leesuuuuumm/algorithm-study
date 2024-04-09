import java.util.*;

public class Main {

    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Tower implements Comparable<Tower> {
        int r;
        int c;
        int power;
        int recent = 0;

        public Tower(int r, int c, int power) {
            this.r = r;
            this.c = c;
            this.power = power;
        }

        public boolean getPath() {
            limit = minDistance();
            if (limit == -1) return false;
            dfs(r, c, 0, new boolean[N][M]);
            return true;
        }

        private boolean dfs(int r, int c, int count, boolean[][] visit) {
            if (count > limit) return false;
            if (r == receiver.r && c == receiver.c) return true;

            for (int i = 0; i < 4; i++) {
                int nr = (r + N + dr[i]) % N;
                int nc = (c + M + dc[i]) % M;

                if (visit[nr][nc]) continue;
                if (map[nr][nc] == 0) continue;

                visit[nr][nc] = true;
                Location next = new Location(nr, nc);
                path.add(next);
                if (dfs(nr, nc, count + 1, visit)) return true;
                visit[nr][nc] = false;
                path.remove(next);
            }
            return false;
        }

        private int minDistance() {
            Queue<Location> q = new ArrayDeque<>();
            boolean[][] visit = new boolean[N + 1][M + 1];
            q.add(new Location(r, c));
            visit[r][c] = true;

            int result = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    Location cur = q.poll();

                    if (cur.r == receiver.r && cur.c == receiver.c) return result;

                    for (int dir = 0; dir < 4; dir++) {
                        int nr = (cur.r + N + dr[dir]) % N;
                        int nc = (cur.c + M + dc[dir]) % M;

                        if (visit[nr][nc]) continue;
                        if (map[nr][nc] == 0) continue;

                        q.add(new Location(nr, nc));
                        visit[nr][nc] = true;
                    }
                }
                result++;
            }
            return -1;
        }

        // 약한 타워가 앞으로 오는
        @Override
        public int compareTo(Tower o) {
            if (power != o.power) return power - o.power;
            else if (recent != o.recent) return o.recent - recent;
            else if (r + c != o.r + o.c) return (o.r + o.c) - (r + c);
            else return o.c - c;
        }
    }

    static int N, M, K;
    static int[][] map;
    static Tower[][] towerMap;
    static LinkedList<Tower> minQueue = new LinkedList<>();
    static Tower attacker;
    static Tower receiver;
    static List<Location> path;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int limit;

    public static void main(String[] args) throws Exception {
        N = read();
        M = read();
        K = read();
        map = new int[N][M];
        towerMap = new Tower[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = read();
                if (map[i][j] == 0) continue;
                Tower tower = new Tower(i, j, map[i][j]);
                minQueue.add(tower);
                towerMap[i][j] = tower;
            }
        }

        for (int i = 0; i < K; i++) {
            if (minQueue.size() <= 1) break;

            Collections.sort(minQueue);

            attacker = minQueue.getFirst();
            receiver = minQueue.getLast();
            path = new ArrayList<>();

            attacker.power += N + M;
            map[attacker.r][attacker.c] += N + M;
            attacker.recent = i + 1;

            if (attacker.getPath()) {
                for (Location loc : path) {
                    if (loc.r == receiver.r && loc.c == receiver.c) continue;
                    destroy(loc, attacker.power / 2);
                }
                destroy(new Location(receiver.r, receiver.c), attacker.power);
            } else {
                bomb();
                for (Location loc : path) {
                    if (loc.r == receiver.r && loc.c == receiver.c) continue;
                    destroy(loc, attacker.power / 2);
                }
                destroy(new Location(receiver.r, receiver.c), attacker.power);
            }
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[j][k] == 0) continue;
                    map[j][k]++;
                    towerMap[j][k].power++;
                }
            }
            for (Location loc : path) {
                if (map[loc.r][loc.c] == 0) continue;
                map[loc.r][loc.c]--;
                towerMap[loc.r][loc.c].power--;
            }
            map[attacker.r][attacker.c]--;
            attacker.power--;
        }
        Collections.sort(minQueue);
        System.out.println(minQueue.getLast().power);
    }

    private static void bomb() {
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nr = (receiver.r + N + dr[i]) % N;
            int nc = (receiver.c + M + dc[i]) % M;

            if (map[nr][nc] == 0) continue;
            if (nr == attacker.r && nc == attacker.c) continue;

            path.add(new Location(nr, nc));
        }
        path.add(new Location(receiver.r, receiver.c));
    }

    private static void destroy(Location loc, int power) {
        towerMap[loc.r][loc.c].power = Math.max(towerMap[loc.r][loc.c].power - power, 0);
        map[loc.r][loc.c] = Math.max(map[loc.r][loc.c] - power, 0);
        if (towerMap[loc.r][loc.c].power == 0) {
            minQueue.remove(towerMap[loc.r][loc.c]);
            towerMap[loc.r][loc.c] = null;
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
