// [BOJ] 안전 영역

import java.util.*;

public class Main {
    static int n;
    static int[][] graph;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        graph = new int[n][n];
        
        // 그래프 입력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        int[][] tmpG = new int[n][n];
        copyGraph(graph, tmpG);

        List<Integer> res = new ArrayList<>();
        for (int h = 0; h <= 100; h++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] > h) {
                        bfs(i, j, h);
                        count++;
                    }
                }
            }
            res.add(count);
            copyGraph(tmpG, graph); // 그래프 초기화
        }

        System.out.println(Collections.max(res));
        sc.close();
    }

    static void bfs(int x, int y, int h) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            x = pos[0];
            y = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                if (graph[nx][ny] <= h) continue;

                graph[nx][ny] = 0; // 방문 처리
                q.offer(new int[]{nx, ny});
            }
        }
    }

    static void copyGraph(int[][] src, int[][] dest) {
        for (int i = 0; i < n; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, n);
        }
    }
}