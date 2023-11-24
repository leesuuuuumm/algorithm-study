import java.util.*;

public class Main {
    /*
    회전시킬 라인 갯수 계산
    회전, 시작점은 마지막에 저장
     */
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};
    static int[][] a;
    static int n, m, r;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        r = sc.nextInt();
        a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = sc.nextInt();
                a[i][j] = num;
            }
        }
        while (r-- > 0) {
            rotate();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(a[i][j] + " ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void rotate() {
        int line = Math.min(n, m) / 2;

        for (int i = 0; i < line; i++) {
            int curx = i;
            int cury = i;

            int start = a[i][i];

            int idx = 0;
            while (idx < 4) {
                int nx = curx + dx[idx];
                int ny = cury + dy[idx];

                if (i <= nx && nx < n - i && i <= ny && ny < m - i) {
                    a[curx][cury] = a[nx][ny];
                    curx = nx;
                    cury = ny;
                } else {
                    idx++;
                }
            }
            a[i + 1][i] = start;
        }
    }
}