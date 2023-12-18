import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0}; // 남 동 북 서
    static int[] dy = {0, 1, 0, -1};
    static int x = 0;
    static int y = 0;
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        int num = 1;
        int dir = 0;
        map[0][0] = 1;
        while (true) {
            if (num >= m * n) {
                break;
            }
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if ((nx < 0 || nx >= n || ny < 0 || ny >= m) || map[nx][ny] != 0) {
                dir = (dir + 1) % 4;
            }
            x += dx[dir];
            y += dy[dir];
            map[x][y] = ++num;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}