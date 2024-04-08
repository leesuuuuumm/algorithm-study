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

    static int N, M, max;
    static int[][] map, copy;
    static List<Location> viruses = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = Integer.MIN_VALUE;

        map = new int[N][M];
        copy = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // 바이러스의 위치 저장
                if (map[i][j] == 2) {
                    viruses.add(new Location(i, j));
                }
            }
        }

        combination(0, 0);

        System.out.println(max);
    }

    private static void combination(int start, int cnt) {
        if (cnt == 3) {
            // bfs에서는 copy 배열을 사용해서 돌린다
            copy(map, copy);

            int result = bfs();

            max = Math.max(max, result);

            return;
        }

        for (int i = start; i < N * M; i++) {
            int r = i / M;
            int c = i % M;

            if (map[r][c] == 0) {
                map[r][c] = 1;
                combination(i + 1, cnt + 1);
                map[r][c] = 0;
            }
        }
    }

    private static void copy(int[][] map, int[][] copy) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    private static int bfs() {
        Queue<Location> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];

        for (Location virus : viruses) {
            queue.offer(virus);
            visit[virus.r][virus.c] = true;
        }

        while (!queue.isEmpty()) {
            Location cur = queue.poll();
            copy[cur.r][cur.c] = 2;

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visit[nr][nc]) continue;
                if (copy[nr][nc] == 1) continue;

                queue.offer(new Location(nr, nc));
                visit[nr][nc] = true;
            }
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 0) result++;
            }
        }

        return result;
    }
}