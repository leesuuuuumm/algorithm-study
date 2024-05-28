import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static int N, M;
    static char[][] map;
    static Location jihun;
    static List<Location> fire = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        input();
        int result = bfs();
        System.out.println(result == -1 ? "IMPOSSIBLE" : result);

    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static int bfs() {
        Queue<Location> q = new ArrayDeque<>();
        Queue<Location> fq = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];
        boolean[][] fVisit = new boolean[N][M];

        q.add(jihun);
        visit[jihun.r][jihun.c] = true;
        for (Location f : fire) {
            fq.add(f);
            fVisit[f.r][f.c] = true;
        }
        int time = 1;
        while (!q.isEmpty()) {
            int fs = fq.size();
            for (int i = 0; i < fs; i++) {
                Location cur = fq.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nr = cur.r + dr[dir];
                    int nc = cur.c + dc[dir];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (fVisit[nr][nc]) continue;
                    if (map[nr][nc] == '#') continue;

                    fq.add(new Location(nr, nc));
                    fVisit[nr][nc] = true;
                }
            }
            int s = q.size();
            for (int i = 0; i < s; i++) {
                Location cur = q.poll();

                if (cur.r == 0 || cur.r == N - 1 || cur.c == 0 || cur.c == M - 1) return time;

                for (int dir = 0; dir < 4; dir++) {
                    int nr = cur.r + dr[dir];
                    int nc = cur.c + dc[dir];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (visit[nr][nc]) continue;
                    if (fVisit[nr][nc]) continue;
                    if (map[nr][nc] == '#') continue;

                    q.add(new Location(nr, nc));
                    visit[nr][nc] = true;
                }
            }
            time++;
        }
        return -1;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') jihun = new Location(i, j);
                else if (map[i][j] == 'F') fire.add(new Location(i, j));
            }
        }
    }
}