import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static class Robot {
        int r;
        int c;
        int dir;

        public Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    static int N, M;
    static int[][] map;
    static Robot start, end;

    static int[] dr = {0, 0, 0, 1, -1};
    static int[] dc = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        N = read();
        M = read();
        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = read();
            }
        }

        int sr = read();
        int sc = read();
        int sdir = read();
        start = new Robot(sr, sc, sdir);

        int er = read();
        int ec = read();
        int edir = read();
        end = new Robot(er, ec, edir);

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Robot> q = new ArrayDeque<>();
        boolean[][][] visit = new boolean[N + 1][M + 1][5];
        q.add(start);
        visit[start.r][start.c][start.dir] = true;

        int result = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Robot cur = q.poll();

                if (cur.r == end.r && cur.c == end.c && cur.dir == end.dir)
                    return result;

                for (int j = 1; j <= 3; j++) {
                    int nr = cur.r + dr[cur.dir] * j;
                    int nc = cur.c + dc[cur.dir] * j;

                    if (nr < 1 || nr > N || nc < 1 || nc > M)
                        continue;
                    if (map[nr][nc] == 1)
                        break;
                    if (visit[nr][nc][cur.dir])
                        continue;
                    q.add(new Robot(nr, nc, cur.dir));
                    visit[nr][nc][cur.dir] = true;
                }

                for (int j = 0; j < 2; j++) {
                    int ndir = turn(cur.dir, j);

                    if (visit[cur.r][cur.c][ndir])
                        continue;
                    q.add(new Robot(cur.r, cur.c, ndir));
                    visit[cur.r][cur.c][ndir] = true;
                }
            }
            result++;
        }

        return -1;
    }

    private static int turn(int cur, int dir) {
        if (dir == 0) {
            switch (cur) {
                case 1:
                    return 3;
                case 2:
                    return 4;
                case 3:
                    return 2;
                case 4:
                    return 1;
            }
        } else {
            switch (cur) {
                case 1:
                    return 4;
                case 2:
                    return 3;
                case 3:
                    return 1;
                case 4:
                    return 2;
            }
        }
        return -1;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}