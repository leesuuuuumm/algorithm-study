import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static class Hero {
        int r;
        int c;
        boolean hasGram;

        public Hero(int r, int c, boolean hasGram) {
            this.r = r;
            this.c = c;
            this.hasGram = hasGram;
        }
    }
    static int N, M, T;
    static int[][] map;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        N = read();
        M = read();
        T = read();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = read();
            }
        }
        int result = bfs();

        System.out.println(result > T ? "Fail" : result);
    }

    private static int bfs() {
        Queue<Hero> q = new ArrayDeque<>();
        boolean[][][] visit = new boolean[N][M][2];
        q.add(new Hero(0, 0, false));
        visit[0][0][0] = true;
        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Hero cur = q.poll();

                if (map[cur.r][cur.c] == 2)
                    cur.hasGram = true;

                if (cur.r == N - 1 && cur.c == M - 1)
                    return time;

                for (int j = 0; j < 4; j++) {
                    int nr = cur.r + dr[j];
                    int nc = cur.c + dc[j];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        continue;
                    if (visit[nr][nc][cur.hasGram ? 1 : 0])
                        continue;
                    if (!cur.hasGram && map[nr][nc] == 1)
                        continue;

                    q.add(new Hero(nr, nc, cur.hasGram));
                    visit[nr][nc][cur.hasGram ? 1 : 0] = true;
                }
            }
            time++;
        }

        return T + 1;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}