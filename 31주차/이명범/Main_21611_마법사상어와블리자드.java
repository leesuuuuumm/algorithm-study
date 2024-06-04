import java.util.ArrayList;
import java.util.List;

public class Main {
    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M;
    static int[][] map;
    static Location[] locations;

    public static void main(String[] args) throws Exception {
        input();
        int result = 0;
        for (int i = 0; i < M; i++) {
            int d = read();
            int s = read();
            ice(d, s);
            while (true) {
                fill();
                int score = explode();
                if (score == 0) break;
                result += score;
            }
            change();
        }
        System.out.println(result);
    }

    private static void change() {
        int index = 1;
        boolean[] visit = new boolean[N * N];
        int[][] tmap = new int[N][N];
        for (int i = 0; i < N * N; i++) {
            if (visit[i]) continue;
            Location cur = locations[i];
            if (map[cur.r][cur.c] == 0) continue;

            int count = 0;
            for (int j = i; j < N * N; j++) {
                Location temp = locations[j];
                if (map[cur.r][cur.c] != map[temp.r][temp.c]) break;
                count++;
                visit[j] = true;
            }

            int A = count;
            int B = map[cur.r][cur.c];

            Location aLoc = locations[index];
            tmap[aLoc.r][aLoc.c] = A;
            if (index + 1 >= N * N) break;
            index++;
            Location bLoc = locations[index];
            tmap[bLoc.r][bLoc.c] = B;
            if (index + 1 >= N * N) break;
            index++;
        }
        map = tmap;
    }

    private static int explode() {
        int result = 0;
        boolean[] visit = new boolean[N * N];
        for (int i = 1; i < N * N; i++) {
            if (visit[i]) continue;
            Location cur = locations[i];
            if (map[cur.r][cur.c] == 0) continue;

            List<Location> list = new ArrayList<>();
            for (int j = i; j < N * N; j++) {
                Location temp = locations[j];
                if (map[cur.r][cur.c] != map[temp.r][temp.c]) break;
                list.add(new Location(temp.r, temp.c));
                visit[j] = true;
            }
            if (list.size() >= 4) {
                for (Location loc : list) {
                    result += map[loc.r][loc.c];
                    map[loc.r][loc.c] = 0;
                }
            }
        }
        return result;
    }

    private static void fill() {
        for (int i = 1; i < N * N; i++) {
            Location cur = locations[i];
            if (map[cur.r][cur.c] != 0) continue;
            boolean isOp = false;
            for (int j = i + 1; j < N * N; j++) {
                Location temp = locations[j];
                if (map[temp.r][temp.c] == 0) continue;
                map[cur.r][cur.c] = map[temp.r][temp.c];
                map[temp.r][temp.c] = 0;
                isOp = true;
                break;
            }
            if (!isOp) break;
        }
    }

    private static void ice(int d, int s) {
        int[] dr = {0, -1, 1, 0, 0};
        int[] dc = {0, 0, 0, -1, 1};

        Location shark = locations[0];
        int r = shark.r;
        int c = shark.c;
        for (int i = 0; i < s; i++) {
            r += dr[d];
            c += dc[d];
            if (r < 0 || r >= N || c < 0 || c >= N) break;
            map[r][c] = 0;
        }
    }

    private static void input() throws Exception {
        N = read();
        M = read();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = read();
            }
        }
        locations = new Location[N * N];
        int dir = 3;
        int r = N / 2;
        int c = N / 2;
        int[] dr = {0, 1, 0, -1};
        int[] dc = {-1, 0, 1, 0};
        int index = 0;
        boolean[][] visit = new boolean[N][N];
        while (true) {
            visit[r][c] = true;
            locations[index++] = new Location(r, c);
            if (!visit[r + dr[(dir + 1) % 4]][c + dc[(dir + 1) % 4]]) dir = (dir + 1) % 4;
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if (nc < 0) break;
            r = nr;
            c = nc;
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