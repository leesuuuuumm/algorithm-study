import java.util.*;

public class Main {
    /**
    1~4N까지의 방향을 정한다.
    각 모양에 해당 방향에서의 레이저가 부딪힐 때 어느 방향으로 휘는지 정한다.
    휠 때 무조건 휘는 방향으로 한 칸 이동한다.
    아래쪽
    /: 왼쪽, \: 오른쪽
    왼쪽
    /: 아래쪽, \: 위쪽
    위쪽
    /: 오른쪽, \: 왼쪽
    오른쪽
    /: 위쪽, \: 아래쪽
    */
    static int[] dx = {1, 0, -1, 0}; // 레이저가 오는 방향 순서대로
    static int[] dy = {0, -1, 0, 1};
    static int x = 0;
    static int y = 0;
    static int dir = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String tmp = sc.next();
            for (int j = 0; j < n; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }
        int k = sc.nextInt();
        initialize(n, k);
        int ans = 0;
        while (true) {
            changeDir(map[x][y]);
            ans++;

            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                break;
            }
            x = nx;
            y = ny;
        }

        System.out.print(ans);
    }

    public static void initialize(int n, int k) {
        if (k <= n) {
            x = 0; y = k - 1; dir = 0;
        } else if (k <= 2 * n) {
            x = k - n - 1; y = n - 1; dir = 1;
        } else if (k <= 3 * n) {
            x = n - 1; y = n - (k - 2 * n); dir = 2;
        } else {
            x = n - (k - 3 * n); y = 0; dir = 3;
        }
    }

    public static void changeDir(char ch) {
        if (dir == 0) {
            if (ch == '/') {
                dir = 1;
            } else {
                dir = 3;
            }
        } else if (dir == 1) {
            if (ch == '/') {
                dir = 0;
            } else {
                dir = 2;
            }
        } else if (dir == 2) {
            if (ch == '/') {
                dir = 3;
            } else {
                dir = 1;
            }
        } else {
            if (ch == '/') {
                dir = 2;
            } else {
                dir = 0;
            }
        }
    }
}