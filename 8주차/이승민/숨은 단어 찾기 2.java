import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1, -1, 1, 1, -1};
    static int[] dy = {1, 0, -1, 0, 1, 1, -1, -1};

    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String tmp = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }
        
        int ans = 0;
        // 각 위치 별로 8방향에 대해 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 'L') {
                        continue;
                }
                for (int k = 0; k < 8; k++) {
                    int curx = i;
                    int cury = j;
                    String tmp = "L";
                    while (true) {
                        int nx = curx + dx[k];
                        int ny = cury + dy[k];
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                            break;
                        }
                        if (map[nx][ny] != 'E') {
                            break;
                        }
                        tmp += map[nx][ny];
                        curx = nx;
                        cury = ny;
                        if (tmp.equals("LEE")) {
                            ans++;
                            break;
                        }
                    }
                }
            }
        }

        System.out.print(ans);
    }
}
