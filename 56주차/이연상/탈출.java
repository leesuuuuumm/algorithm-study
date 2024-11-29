// [BOJ] 탈출

import java.io.*;
import java.util.*;

public class Escape {
    static int n, m;
    static char[][] graph;
    static int[][] distance;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new char[n][m];
        distance = new int[n][m];

        int Dx = 0, Dy = 0;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j);
                if (graph[i][j] == 'S') {
                    queue.add(new int[]{i, j, 0}); // {x, y, type(0: S, 1: *)}
                } else if (graph[i][j] == 'D') {
                    Dx = i;
                    Dy = j;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == '*') {
                    queue.add(new int[]{i, j, 1}); // Flood from water first
                }
            }
        }

        System.out.println(bfs(Dx, Dy));
    }

    static String bfs(int Dx, int Dy) {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int type = current[2];

            if (graph[Dx][Dy] == 'S') {
                return String.valueOf(distance[Dx][Dy]);
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if ((graph[nx][ny] == '.' || graph[nx][ny] == 'D') && type == 0 && graph[x][y] == 'S') {
                        graph[nx][ny] = 'S';
                        distance[nx][ny] = distance[x][y] + 1;
                        queue.add(new int[]{nx, ny, 0});
                    } else if ((graph[nx][ny] == '.' || graph[nx][ny] == 'S') && type == 1 && graph[x][y] == '*') {
                        graph[nx][ny] = '*';
                        queue.add(new int[]{nx, ny, 1});
                    }
                }
            }
        }
        return "KAKTUS";
    }
}