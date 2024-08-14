package 김민우;

import java.io.*;
import java.util.*;

public class n과m10 {
    static int n, m;
    static boolean[] visited;
    static int[] area, board;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n];
        visited = new boolean[n];
        area = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board);

        dfs(0, 0);
        System.out.println(sb);
    }

    public static void dfs(int depth, int num) {
        if (depth == m) {
            for (int i = 0 ; i < m; i++) {
                sb.append(area[i] + " ");
            }
            sb.append("\n");
            return;
        }

        int prev = -1; // 같은 뎁스의 이전 원소를 담고 있음
        for (int i = 0; i < n; i++) {
            int current = board[i];
            if (!visited[i] && num <= current && prev != current) {
                visited[i] = true;
                area[depth] = current;
                prev = current;
                dfs(depth + 1, current);
                visited[i] = false;
            }
        }
    }
}
