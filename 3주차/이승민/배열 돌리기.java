import java.util.*;

public class Main {
    /*
    반시계 45 = 시계 315(=360 - 45)
     */
    static int[][] x;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        StringBuilder ans = new StringBuilder();
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int d = sc.nextInt();
            x = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    x[i][j] = sc.nextInt();
                }
            }
            if (d > 0) {
                for (int i = 0; i < d / 45; i++) {
                    rotate();
                }
            } else {
                for (int i = 0; i < (360 + d) / 45; i++) {
                    rotate();
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans.append(x[i][j] + " ");
                }
                ans.append('\n');
            }
        }
        System.out.println(ans);
    }

    // 시계방향 45
    public static void rotate() {
        int[] tmp = new int[x.length];
        int[] tmp2 = new int[x.length];
        int[] tmp3 = new int[x.length];
        int[] tmp4 = new int[x.length];
        // X의 주 대각선을 ((1,1), (2,2), …, (n, n)) 가운데 열 ((n+1)/2 번째 열)로 옮긴다.
        for (int i = 0; i < x.length; i++) {
            tmp[i] = x[i][i];
        }
        // X의 가운데 열을 X의 부 대각선으로 ((n, 1), (n-1, 2), …, (1, n)) 옮긴다.
        for (int i = 0; i < x.length; i++) {
            tmp2[i] = x[i][x.length / 2];
        }
        // X의 부 대각선을 X의 가운데 행 ((n+1)/2번째 행)으로 옮긴다.
        for (int i = 0; i < x.length; i++) {
            tmp3[i] = x[x.length - (i + 1)][i];
        }
        // X의 가운데 행을 X의 주 대각선으로 옮긴다.
        for (int i = 0; i < x.length; i++) {
            tmp4[i] = x[x.length / 2][i];
        }

        // 주대각선 -> 가운데열 ㅇ
        for (int i = 0; i < x.length; i++) {
            x[i][x.length / 2] = tmp[i];
        }
        // 가운데열 -> 부대각선 ㅌ
        for (int i = 0; i < x.length; i++) {
            x[i][x.length - (i + 1)] = tmp2[i];
        }
        // 부대각선 -> 가운데행
        for (int i = 0; i < x.length; i++) {
            x[x.length / 2][i] = tmp3[i];
        }
        // 가운데행 -> 주대각선
        for (int i = 0; i < x.length; i++) {
            x[i][i] = tmp4[i];
        }
    }

}