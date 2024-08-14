
import java.io.*;
import java.util.*;

public class n과m9 {

    static int n, m;
    static boolean[] visited, start;
    static int[] area, board;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        board = new int[n];
        start = new boolean[n];
        area = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(board);

        dfs(0);
        System.out.println(sb);
    }
    static void dfs(int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(area[i] + " ");
            }
            sb.append("\n");
            return;
        }

        int prev = -1; // 이전에 사용된 숫자를 저장
        for (int i = 0; i < n; i++) {
            if (!visited[i] && board[i] != prev) {
                int current = board[i];
                visited[i] = true;
                area[depth] = current;
                dfs(depth + 1);
                visited[i] = false;
                prev = current; // 현재 숫자를 이전 숫자로 저장
            }
        }
    }
}
