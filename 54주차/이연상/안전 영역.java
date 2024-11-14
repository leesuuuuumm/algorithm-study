// [BOJ] 2468_안전 영역

import java.util.*;

public class SafeArea {
    static int n;
    static int[][] graph;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        int[][] tmpG = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(graph[i], 0, tmpG[i], 0, n);
        }

        int count;
        List<Integer> res = new ArrayList<>();

        for (int h = 0; h <= 100; h++) {
            count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] > h) {
                        bfs(i, j, h);
                        count++;
                    }
                }
            }

            res.add(count);

            for (int i = 0; i < n; i++) {
                System.arraycopy(tmpG[i], 0, graph[i], 0, n);
            }
        }

        System.out.println(Collections.max(res));
    }

    static void bfs(int x, int y, int h) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            x = position[0];
            y = position[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (graph[nx][ny] <= h) continue;

                graph[nx][ny] = 0;
                queue.add(new int[]{nx, ny});
            }
        }
    }
}