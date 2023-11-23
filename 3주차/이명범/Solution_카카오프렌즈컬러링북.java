import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int numberOfArea;
    static int maxSizeOfOneArea;
    static boolean[][] visit;

    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        visit = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j]) continue;
                if (picture[i][j] == 0) continue;

                bfs(i, j, m, n, picture);
            }
        }

        return new int[]{numberOfArea, maxSizeOfOneArea};
    }

    private static void bfs(int r, int c, int m, int n, int[][] picture) {
        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(r, c));
        visit[r][c] = true;

        int sizeOfOneArea = 0;

        while (!queue.isEmpty()) {
            Location cur = queue.poll();
            sizeOfOneArea++;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (visit[nr][nc]) continue;
                if (picture[nr][nc] != picture[cur.r][cur.c]) continue;

                queue.offer(new Location(nr, nc));
                visit[nr][nc] = true;
            }
        }

        numberOfArea++;
        maxSizeOfOneArea = Math.max(sizeOfOneArea, maxSizeOfOneArea);
    }
}