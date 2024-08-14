import java.io.*;
import java.util.*;

public class 배열돌리기1 {
    static int n, m, r;
    static int[][] board;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0 ;i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < r; i++) {
            rotate();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void rotate() {
        for (int i = 0; i < Math.min(n, m) / 2; i++) {
            int d = 0; // 방향
            // 시작 점
            int temp = board[i][i];
            int x = i;
            int y = i;


            while (d < 4) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= i && ny >= i && nx < n - i && ny < m - i) {
                    board[x][y] = board[nx][ny];
                    x = nx;
                    y = ny;
                } else {
                    d++;
                }
            }
            // temp의 위치는 한칸 아래
            board[i + 1][i] = temp;
        }
    }
}

