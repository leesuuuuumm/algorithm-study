package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_21609_상어중학교 {

    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class BlockGroup implements Comparable<BlockGroup> {
        int rc = 0;
        int mr = Integer.MAX_VALUE;
        int mc = Integer.MAX_VALUE;

        List<Location> locs = new ArrayList<>();

        public void add(Location loc) {
            if (map[loc.r][loc.c] == 0) rc++;
            else if (loc.r < mr) {
                mr = loc.r;
                mc = loc.c;
            } else if (loc.r == mr && loc.c < mc) {
                mc = loc.c;
            }
            locs.add(loc);
        }

        @Override
        public int compareTo(BlockGroup o) {
            if (locs.size() != o.locs.size()) return o.locs.size() - locs.size();
            else if (rc != o.rc) return o.rc - rc;
            else if (mr != o.mr) return o.mr - mr;
            else return o.mc - mc;
        }
    }

    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int result = 0;

    public static void main(String[] args) throws Exception {
        input();
        while (true) {
            int score = find();
            if (score <= 1) break;
            result += score;
            fall();
            rotate();
            fall();
        }
        System.out.println(result);
    }

    private static void rotate() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[N - 1 - j][i] = map[i][j];
            }
        }
        map = temp;
    }

    private static void fall() {
        for (int c = 0; c < N; c++) {
            for (int r = N - 1; r >= 0; r--) {
                if (map[r][c] == -2) {
                    for (int i = r - 1; i >= 0; i--) {
                        if (map[i][c] == -2) continue;
                        if (map[i][c] == -1) break;
                        map[r][c] = map[i][c];
                        map[i][c] = -2;
                        break;
                    }
                }
            }
        }
    }

    private static int find() {
        PriorityQueue<BlockGroup> pq = new PriorityQueue<>();
        Queue<Location> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || map[i][j] == -1 || map[i][j] == -2) continue;
                if (visit[i][j]) continue;
                q.add(new Location(i, j));
                visit[i][j] = true;
                BlockGroup bg = new BlockGroup();
                boolean[][] zeroVisit = new boolean[N][N];

                int color = map[i][j];
                while (!q.isEmpty()) {
                    Location cur = q.poll();

                    bg.add(cur);

                    for (int dir = 0; dir < 4; dir++) {
                        int nr = cur.r + dr[dir];
                        int nc = cur.c + dc[dir];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                        if (visit[nr][nc]) continue;
                        if (zeroVisit[nr][nc]) continue;
                        if (map[nr][nc] != 0 && map[nr][nc] != color) continue;

                        q.add(new Location(nr, nc));
                        if (map[nr][nc] != 0) visit[nr][nc] = true;
                        zeroVisit[nr][nc] = true;
                    }
                }
                pq.add(bg);
            }
        }
        if (pq.isEmpty()) return 0;

        BlockGroup cur = pq.poll();
        for (Location loc : cur.locs) {
            map[loc.r][loc.c] = -2;
        }
        return cur.locs.size() * cur.locs.size();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
