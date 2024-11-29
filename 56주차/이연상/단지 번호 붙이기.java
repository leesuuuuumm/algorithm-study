// [BOJ] 단지번호 붙이기

import java.io.*;
import java.util.*;

public class ComplexNumbering {
    static int n;
    static int[][] graph;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        List<Integer> num = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count = 0;
                if (DFS(i, j)) {
                    num.add(count);
                    result++;
                }
            }
        }

        Collections.sort(num);
        System.out.println(result);
        for (int number : num) {
            System.out.println(number);
        }
    }

    static boolean DFS(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n) {
            return false;
        }

        if (graph[x][y] == 1) {
            count++;
            graph[x][y] = 0; // 방문 처리
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                DFS(nx, ny);
            }
            return true;
        }
        return false;
    }
}