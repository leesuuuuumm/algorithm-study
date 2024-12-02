// [BOJ] 인구 이동

import java.io.*;
import java.util.*;

public class PopulationMovement {
    static int n, l, r;
    static int[][] A;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        A = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;

        while (true) {
            visited = new int[n][n];
            boolean flag = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) {
                        visited[i][j] = 1;
                        List<int[]> union = bfs(i, j);

                        if (union.size() > 1) {
                            flag = true;
                            int people = 0;
                            for (int[] country : union) {
                                people += A[country[0]][country[1]];
                            }
                            people /= union.size();

                            for (int[] country : union) {
                                A[country[0]][country[1]] = people;
                            }
                        }
                    }
                }
            }

            if (!flag) {
                System.out.println(result);
                break;
            }

            result++;
        }
    }

    static List<int[]> bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> union = new ArrayList<>();
        queue.add(new int[]{i, j});
        union.add(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && visited[nx][ny] == 0) {
                    if (l <= Math.abs(A[nx][ny] - A[x][y]) && Math.abs(A[nx][ny] - A[x][y]) <= r) {
                        visited[nx][ny] = 1;
                        queue.add(new int[]{nx, ny});
                        union.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return union;
    }
}