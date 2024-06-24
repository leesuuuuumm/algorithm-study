import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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
    static int N, M;
    static int[][] map;
    static Queue<Location> clouds = new ArrayDeque<>();
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        input();
        for (int i = 0; i < M; i++) {
            List<Location> rainyLocations = raindrop();
            copy(rainyLocations);
            makeCloud(rainyLocations);
        }
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += map[i][j];
            }
        }
        System.out.println(result);
    }

    private static void makeCloud(List<Location> rainyLocations) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] < 2) continue;
                if (isRaindrop(rainyLocations, i, j)) continue;
                clouds.add(new Location(i, j));
                map[i][j] -= 2;
            }
        }
    }

    private static boolean isRaindrop(List<Location> rainyLocations, int r, int c) {
        for (Location loc : rainyLocations) {
            if (loc.r == r && loc.c == c) return true;
        }
        return false;
    }

    private static void copy(List<Location> rainyLocations) {
        int[][] temp = new int[N][N];
        for (Location loc : rainyLocations) {
            int count = 0;
            for (int i = 2; i <= 8; i += 2) {
                int nr = loc.r + dr[i];
                int nc = loc.c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (map[nr][nc] == 0) continue;
                count++;
            }
            temp[loc.r][loc.c] += count;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += temp[i][j];
            }
        }
    }

    private static List<Location> raindrop() throws Exception {
        int d = read();
        int s = read();
        List<Location> result = new ArrayList<>();
        while (!clouds.isEmpty()) {
            Location cur = clouds.poll();
            int nr = (cur.r + s * N + dr[d] * s) % N;
            int nc = (cur.c + s * N + dc[d] * s) % N;
            map[nr][nc]++;
            result.add(new Location(nr, nc));
        }
        return result;
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
        clouds.add(new Location(N - 1, 0));
        clouds.add(new Location(N - 1, 1));
        clouds.add(new Location(N - 2, 0));
        clouds.add(new Location(N - 2, 1));
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}