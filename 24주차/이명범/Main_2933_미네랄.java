import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Location implements Comparable<Location> {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Location o) {
            return o.r - r;
        }
    }

    static int R, C, N;
    static char[][] map;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static List<Location> separates = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int H = R - Integer.parseInt(st.nextToken());
            int W = throwStick(H, i % 2);

            if (W == -1) continue;

            for (int dir = 0; dir < 4; dir++) {
                int nr = H + dr[dir];
                int nc = W + dc[dir];

                if (isOutOfBounds(nr, nc)) continue;
                if (map[nr][nc] == '.') continue;
                if (!isSeparated(nr, nc)) continue;

                fall(calculateFloatingHeight());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isOutOfBounds(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

    private static int throwStick(int H, int mode) {
        if (mode == 0) {
            for (int i = 0; i < C; i++) {
                if (map[H][i] == '.') continue;
                map[H][i] = '.';
                return i;
            }
        } else {
            for (int i = C - 1; i >= 0; i--) {
                if (map[H][i] == '.') continue;
                map[H][i] = '.';
                return i;
            }
        }
        return -1;
    }

    private static boolean isSeparated(int r, int c) {
        Queue<Location> q = new LinkedList<>();
        boolean[][] visit = new boolean[R][C];
        q.add(new Location(r, c));
        visit[r][c] = true;

        int max = Integer.MIN_VALUE;

        while(!q.isEmpty()) {
            Location cur = q.poll();

            max = Math.max(max, cur.r);
            separates.add(new Location(cur.r, cur.c));

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + dr[dir];
                int nc = cur.c + dc[dir];

                if (isOutOfBounds(nr, nc)) continue;
                if (visit[nr][nc]) continue;
                if (map[nr][nc] == '.') continue;

                q.add(new Location(nr, nc));
                visit[nr][nc] = true;
            }
        }

        if (max < R - 1) {
            Collections.sort(separates);
            return true;
        } else {
            separates.clear();
            return false;
        }
    }

    private static int calculateFloatingHeight() {
        boolean[] visit = new boolean[C];
        int min = Integer.MAX_VALUE;

        for (Location cur : separates) {
            if (visit[cur.c]) continue;
            visit[cur.c] = true;
            for (int i = cur.r + 1; i < R; i++) {
                if (map[i][cur.c] == 'x') min = Math.min(min, i - cur.r - 1);
            }
            min = Math.min(min, R - cur.r - 1);
        }

        return min;
    }

    private static void fall(int h) {
        for (Location cur : separates) {
            map[cur.r][cur.c] = '.';
            map[cur.r + h][cur.c] = 'x';
        }
        separates.clear();
    }
}