import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Marble {
        Location red;
        Location blue;

        public Marble(Location red, Location blue) {
            this.red = red;
            this.blue = blue;
        }

        public Marble move(int dir) {
            Location nrl = null;
            Location nbl = null;
            switch (dir) {
                case 0:
                    if (this.red.r <= this.blue.r) {
                        nrl = movedLocation(red, blue, dir);
                        nbl = movedLocation(blue, nrl, dir);
                    } else {
                        nbl = movedLocation(blue, red, dir);
                        nrl = movedLocation(red, nbl, dir);
                    }
                    break;
                case 1:
                    if (this.red.r >= this.blue.r) {
                        nrl = movedLocation(red, blue, dir);
                        nbl = movedLocation(blue, nrl, dir);
                    } else {
                        nbl = movedLocation(blue, red, dir);
                        nrl = movedLocation(red, nbl, dir);
                    }
                    break;
                case 2:
                    if (this.red.c <= this.blue.c) {
                        nrl = movedLocation(red, blue, dir);
                        nbl = movedLocation(blue, nrl, dir);
                    } else {
                        nbl = movedLocation(blue, red, dir);
                        nrl = movedLocation(red, nbl, dir);
                    }
                    break;
                case 3:
                    if (this.red.c >= this.blue.c) {
                        nrl = movedLocation(red, blue, dir);
                        nbl = movedLocation(blue, nrl, dir);
                    } else {
                        nbl = movedLocation(blue, red, dir);
                        nrl = movedLocation(red, nbl, dir);
                    }
                    break;
            }
            return new Marble(nrl, nbl);
        }

        private Location movedLocation(Location t, Location o, int dir) {
            int nr = t.r;
            int nc = t.c;

            while (map[nr + dr[dir]][nc + dc[dir]] != '#' && !(nr + dr[dir] == o.r && nc + dc[dir] == o.c)) {
                nr += dr[dir];
                nc += dc[dir];
                if (map[nr][nc] == 'O') return new Location(0, 0);
            }

            return new Location(nr, nc);
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N, M;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        Location red = null;
        Location blue = null;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = str.charAt(j);
                if (ch == 'R') {
                    red = new Location(i, j);
                    map[i][j] = '.';
                } else if (ch == 'B') {
                    blue = new Location(i, j);
                    map[i][j] = '.';
                }
                map[i][j] = ch;
            }
        }
        System.out.println(bfs(new Marble(red, blue)));
    }

    private static int bfs(Marble marble) {
        Queue<Marble> q = new ArrayDeque<>();
        boolean[][][][] visit = new boolean[N][M][N][M];
        q.add(marble);
        visit[marble.red.r][marble.red.c][marble.blue.r][marble.blue.c] = true;

        for (int count = 0; count <= 10; count++) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Marble cur = q.poll();

                if (cur.blue.r == 0 && cur.blue.c == 0) continue;
                if (cur.red.r == 0 && cur.red.c == 0) return count;

                for (int j = 0; j < 4; j++) {
                    Marble next = cur.move(j);
                    if (visit[next.red.r][next.red.c][next.blue.r][next.blue.c]) continue;
                    q.add(next);
                    visit[next.red.r][next.red.c][next.blue.r][next.blue.c] = true;
                }
            }
        }

        return -1;
    }
}